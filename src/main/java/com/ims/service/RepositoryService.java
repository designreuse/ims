package com.ims.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ims.dto.RoleTO;

import java.util.List;

@Service
@EnableTransactionManagement
@Transactional(rollbackFor = Exception.class)
public interface RepositoryService {

	public List<RoleTO> getRolesTO();

}
