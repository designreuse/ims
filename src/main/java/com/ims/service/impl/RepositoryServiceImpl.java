package com.ims.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.domain.entity.Role;
import com.ims.dto.RoleTO;
import com.ims.repository.RoleRepository;
import com.ims.service.RepositoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	public RoleRepository roleRepository;

	@Override
	public List<RoleTO> getRolesTO() {
		List<Role> roles = roleRepository.findAll();
		List<RoleTO> roleTOs = new ArrayList<RoleTO>();
		for (Role role : roles) {
			roleTOs.add(mapper.map(role, RoleTO.class));
		}
		return roleTOs;
	}

}
