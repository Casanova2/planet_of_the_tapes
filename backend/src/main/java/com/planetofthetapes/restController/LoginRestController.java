package com.planetofthetapes.restController;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planetofthetapes.entity.POrder;
import com.planetofthetapes.entity.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.component.*;

@RestController
public class LoginRestController{

	public interface UserDetails extends User.Basic, User.OrderRelationUser, POrder.Basic {}
	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);

	@Autowired
	private UserComponent userComponent;

	@JsonView(UserDetails.class)
	@RequestMapping("/api/logIn")
	public ResponseEntity<User> logIn() {
		userComponent.getLoggedUser();
		if (userComponent.getLoggedUser() == null) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User loggedUser = userComponent.getLoggedUser();
			log.info("Logged as " + loggedUser.getName());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}

	@RequestMapping("/api/logOut")
	public ResponseEntity<Boolean> logOut(HttpSession session) {
		
		if (userComponent.getLoggedUser() == null) {
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

}