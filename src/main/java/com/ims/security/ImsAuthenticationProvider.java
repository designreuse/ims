package com.ims.security;

import com.ims.domain.entity.Permission;
import com.ims.domain.entity.User;
import com.ims.service.PasswordService;
import com.ims.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImsAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordService passwordService;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		if (name != null && password != null) {
			User user = userService.getUserInfoByName(name.trim());
			if (user != null) {
				try {
					Boolean authenticated = passwordService.authenticate(password.trim(), user);
					if (authenticated) {
						List<GrantedAuthority> grantedAuths = new ArrayList<>();
						if (user.getRole() != null) {
							for (Permission permission : user.getRole().getPermission()) {
								grantedAuths.add(new SimpleGrantedAuthority(permission.getName()));
							}
						}
						Authentication auth = new UsernamePasswordAuthenticationToken(userService.getUserInfo(name), null,
								grantedAuths);
						return auth;
					} else {
						return null;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
