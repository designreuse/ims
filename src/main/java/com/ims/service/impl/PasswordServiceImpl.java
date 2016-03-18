package com.ims.service.impl;

import com.ims.domain.entity.User;
import com.ims.service.PasswordService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class PasswordServiceImpl implements PasswordService {

	private static final Logger log = LoggerFactory.getLogger(PasswordServiceImpl.class);
	@Override
	public String getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt.toString();
	}

	@Override
	public String generateHashPassword(String password, String salt) throws NoSuchAlgorithmException, IOException {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	@Override
	@Transactional
	public Boolean authenticate(String plainTextPassword, User user) throws Exception {
		if (user.getPassword().getSalt() != null) {
			try {
				String generateHashFromInput = generateHashPassword(plainTextPassword, user.getPassword().getSalt());
				if (generateHashFromInput.equals(user.getPassword().getHash()))
					return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		} else {
			log.error("No password associated");
			// TODO need to throw exception
		}
		return false;
	}

}
