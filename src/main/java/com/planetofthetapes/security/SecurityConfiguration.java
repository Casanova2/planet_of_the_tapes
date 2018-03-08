package com.planetofthetapes.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.planetofthetapes.repository.UserRepositoryAuthenticationProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;

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
		http.authorizeRequests().antMatchers("/checkout").permitAll();
		http.authorizeRequests().antMatchers("/buy").permitAll();
		
		// Private pages (all other pages)
        http.authorizeRequests().antMatchers("/home").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/checkout").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/selectpay").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin-userList").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/user-orderlist").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/user-ordertable").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin-products").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-products-add").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-add-user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-remove-product-action").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-remove-user-action").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-userTable").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers("/admin-add-pack-action").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-orderlist").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-packlist").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-add-pack").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-remove-pack").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-modify-pack/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin/pack/modify/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin/user/editProfile").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-user").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-modify-user").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers("/admin/pack/remove/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-user").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin-modify-user").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin/user/editProfile").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin-modify-product").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers("/admin-modify-product-action").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin/user/remove/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin/product/remove/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin-modify-product/").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin/product/modify/").hasAnyRole("ADMIN");
        
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

		// LOGIN
		http.formLogin().loginPage("/signIn");
		http.formLogin().usernameParameter("name");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/signIn");

		// LOGOUT
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
		http.csrf().disable();
		
		// Use Http Basic Authentication
		http.httpBasic();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

}
