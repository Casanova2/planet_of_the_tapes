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
		
		// URLs that need authentication to access to it
		// URLs that need authentication to access to it in API REST

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/api/checkout").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/selectpay").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-userList").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user-orderlist").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-products").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/admin-products-add").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/admin-add-user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin-remove-product-action").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin-remove-user-action").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/admin-add-pack-action").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-orderlist").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-packlist").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/admin-add-pack").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin-remove-pack").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-pack/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin/pack/modify/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin/user/editProfile").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-user").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin/pack/remove/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-user").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-user").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin/user/editProfile").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-product").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-product-action").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin/user/remove/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin/product/remove/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-product/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin/product/modify/").hasAnyRole("ADMIN");
	
		
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
