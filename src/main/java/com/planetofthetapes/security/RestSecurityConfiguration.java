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

		// URLs that need authentication to access to it in API REST

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-userList").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-orderlist").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-products").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-packlist").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user-orderlist").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/admin-user").hasAnyRole("USER");
        
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/admin-add-product").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/admin-add-user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/admin-add-pack-action/{id1}/{id2}/{id3}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/{id2}/buy").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/pack/{id2}/buy").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/{id2}/buy").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/pack/{id2}/buy").hasAnyRole("USER");
        
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin/product/modify/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin/pack/modify/pack-{id}/{id1}-{id2}-{id3}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin/user/editProfile").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/checkout/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-user/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/{id}/{id2}/buy").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id2}/buy").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/{id}/{id1}/remove").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id1}/remove").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin-modify-user/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/checkout/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/admin/user/editProfile").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/{id}/{id2}/buy").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id2}/buy").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/{id}/{id1}/remove").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/pack/{id}/{id1}/remove").hasAnyRole("USER");
        
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin/pack/remove/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin/user/remove/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/admin/product/remove/{id}").hasAnyRole("ADMIN");
       
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
