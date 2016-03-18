package com.ims.config;

import com.ims.security.ImsAuthenticationProvider;
import com.ims.security.ImsAuthenticationSuccessHandler;
import com.ims.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new ImsAuthenticationProvider();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	      http.authorizeRequests().antMatchers("/excel/*").permitAll().antMatchers("/admin/volunteer/printPdf/*").permitAll().antMatchers("/admin/user/**").authenticated()
	     		.and().formLogin().loginPage("/login").successHandler(new ImsAuthenticationSuccessHandler()).and()
	     		.exceptionHandling().accessDeniedPage("/403").and().logout().invalidateHttpSession(true)
	     		.logoutSuccessUrl("/login").and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/sample/**").antMatchers("/resources/**").antMatchers("/webjars/**")
				.antMatchers("/sample2/**");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
