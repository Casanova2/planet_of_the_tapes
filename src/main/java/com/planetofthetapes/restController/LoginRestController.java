package com.planetofthetapes.restController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planetofthetapes.entity.User;
import com.planetofthetapes.component.*;
import com.planetofthetapes.controller.MasterService;


@RestController
@RequestMapping("/api")
public class LoginRestController extends MasterService {

	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);

	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/logIn")
	public ResponseEntity<User> logIn(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.session(model, request, redirectAttrs);

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

	@RequestMapping("/logOut")
	public ResponseEntity<Boolean> logOut(Model model,HttpServletRequest request, RedirectAttributes redirectAttrs,HttpSession session) {
		this.session(model, request, redirectAttrs);

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