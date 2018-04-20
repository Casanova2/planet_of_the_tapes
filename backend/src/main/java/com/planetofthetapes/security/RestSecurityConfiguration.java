package com.planetofthetapes.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.planetofthetapes.repository.UserRepositoryAuthenticationProvider;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public UserRepositoryAuthenticationProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logIn").authenticated();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/product/{id}").permitAll();
		// URLs that need authentication to access to it in API REST

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/users").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/orders").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/products").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/packs").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/orders").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user").hasAnyRole("USER");
        
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/product").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/pack/{id1}/{id2}/{id3}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/order/{id2}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/pack/{id2}/product").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/order/{id2}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/pack/{id2}/product").hasAnyRole("USER");
        
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/product/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id1}-{id2}-{id3}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/checkout/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/user/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/{id}/{id2}/product").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id2}/product").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/{id}/{id1}/r").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id1}/r").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-user/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/checkout/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/user/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/{id}/{id2}/product").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id2}/product").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/{id}/{id1}/r").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id1}/r").hasAnyRole("USER");
        
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/pack/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/user/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/product/{id}").hasAnyRole("ADMIN");
       
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userRepoAuthProvider);
	}

}
