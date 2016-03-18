package com.ims.service.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.domain.entity.Password;
import com.ims.domain.entity.Role;
import com.ims.domain.entity.User;
import com.ims.domain.entity.UserImage;
import com.ims.dto.RoleTO;
import com.ims.dto.UserTO;
import com.ims.repository.RoleRepository;
import com.ims.repository.UserRepository;
import com.ims.service.PasswordService;
import com.ims.service.UserService;
import com.ims.util.ErrorCodes;
import com.ims.util.ImsError;
import com.ims.util.ImsException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserTO saveOrUpdateUser(UserTO userTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserInfoByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public UserTO getUserInfo(Long pkey) {
		User user = userRepository.findByPkey(pkey);
		return convertToUserTO(user);
	}

	private UserTO convertToUserTO(User user) {
		if (user != null) {
			return mapper.map(user, UserTO.class);
		}
		return null;
	}

	@Override
	public UserTO getUserInfo(String name) {
		User user = userRepository.findByName(name);
		UserTO userTO = convertToUserTO(user);
		if (user.getPhoto() != null) {
			userTO.setThumbnailView(user.getPhoto().getThumbnailImage());
		}
		return userTO;
	}

	@Override
	public UserTO registerUser(UserTO userTO) throws ImsException {
		User user = null;
		if (userTO.getPkey() == null)
			user = mapper.map(userTO, User.class);
		else {
			user = userRepository.findByPkey(userTO.getPkey());
			if (user == null)
				return null;

			mapper.map(userTO, user);
		}
		if (userTO.getRole() != null) {
			Role role = roleRepository.findByPkey(userTO.getRole().getPkey());
			user.setRole(role);
		}
		if (userTO.getPassword() != null) {
			Password password = new Password();
			try {
				password.setSalt(passwordService.getSalt());
				password.setHash(passwordService.generateHashPassword(userTO.getPassword(), password.getSalt()));
				user.setPassword(password);
			} catch (NoSuchAlgorithmException | IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			// TODO throw exception
		}
		if (userTO.getActualImage() != null && userTO.getThumbnailView() != null) {
			UserImage userImage = new UserImage();
			userImage.setAcutualImage(userTO.getActualImage());
			userImage.setThumbnailImage(userTO.getThumbnailView());
			user.setPhoto(userImage);
		}

		User dbUser = userRepository.findByName(userTO.getName());
		if (dbUser != null) {
			if (userTO.getPkey() == null || (dbUser.getPkey() != userTO.getPkey())) {
				ImsError error = new ImsError(ErrorCodes.DUPLICATE_USER_NAME, "duplicate.username");
				throw new ImsException(error);
			}
		}
		userRepository.save(user);
		return null;
	}

	@Override
	public Page<UserTO> dataTableSearch(String name, @PageableDefault(page = 0, value = 10) Pageable pageable) {
		Page<User> users = userRepository.findByDatatableFilter(name, pageable);
		// Page<UserTO> usersTO = new Page<UserTO>;
		List<UserTO> userTOs = new ArrayList<UserTO>();
		System.out.println(users.getTotalPages());
		for (User user : users.getContent()) {
			userTOs.add(mapper.map(user, UserTO.class));
		}
		return new PageImpl<>(userTOs, new PageRequest(users.getNumber(), users.getSize(), users.getSort()),
				users.getTotalElements());
	}

	@Override
	public Page<UserTO> getAllActiveUsers(@PageableDefault(page = 0, value = 10) Pageable pageable) {
		Page<User> users = userRepository.findAllActive(pageable);
		// Page<UserTO> usersTO = new Page<UserTO>;
		List<UserTO> userTOs = new ArrayList<UserTO>();
		System.out.println(users.getTotalPages());
		for (User user : users.getContent()) {
			userTOs.add(mapper.map(user, UserTO.class));
		}
		return new PageImpl<>(userTOs, new PageRequest(users.getNumber(), users.getSize(), users.getSort()),
				users.getTotalElements());
	}

	@Override
	@Transactional
	public void deleteUser(Long pkey) {
		userRepository.deleteSoftByPkey(pkey);
	}
	
	@Override
	public String getUserRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UserTO user = (UserTO)authentication.getPrincipal();
	    RoleTO role=user.getRole();
		return role.getName();
	}
}
