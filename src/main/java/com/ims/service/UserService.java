package com.ims.service;

import java.util.Calendar;

import com.ims.domain.entity.User;
import com.ims.dto.UserTO;
import com.ims.util.ImsException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	public UserTO saveOrUpdateUser(UserTO userTO);

	public UserTO getUserInfo(Long pkey);

	public UserTO getUserInfo(String name);

	public UserTO registerUser(UserTO userTO) throws ImsException;

	public Page<UserTO> getAllActiveUsers(Pageable pageable);

	public Page<UserTO> dataTableSearch(String name, Pageable pageable);

	User getUserInfoByName(String name);

	public void deleteUser(Long pkey);
	
	public String getUserRole();
}
