package com.planet_of_the_tapes.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.planet_of_the_tapes.repository.UserAuthenticationRepository;

@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserAuthenticationRepository authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// PUBLIC PAGES
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**").permitAll();
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/contact").permitAll();
		http.authorizeRequests().antMatchers("/plist").permitAll();
		http.authorizeRequests().antMatchers("/product").permitAll();
		http.authorizeRequests().antMatchers("/cart").permitAll();
		http.authorizeRequests().antMatchers("/selectpay").permitAll();

		// LOGIN
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("name");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginError");

		// LOGOUT
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		// DISABLE CSRF
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

}
