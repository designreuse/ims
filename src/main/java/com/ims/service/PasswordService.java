package com.ims.service;

import com.ims.domain.entity.User;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public interface PasswordService {

	public String getSalt() throws NoSuchAlgorithmException;

	public String generateHashPassword(String password, String salt) throws NoSuchAlgorithmException, IOException;

	Boolean authenticate(String plainTextPassword, User user) throws Exception;

}
