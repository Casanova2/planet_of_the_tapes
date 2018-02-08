package com.planet_of_the_tapes.entity;


import java.util.ArrayList;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planet_of_the_tapes.repository.*;

import com.planet_of_the_tapes.entity.User;

@Component
public class DataExamples {

	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	private void initDatabase() {

		// Data declaration

		User user1, user2;

	// Users creation
			user1 = new User("carlos", "1234", "4567891", "elmio@gmail.com",
					"656565066", "ROLE_USER");
			userRepository.save(user1);
			
			
			user2 = new User("raul", "4321", "7894561", "eltuyo@gmail.com",
					"606000000", "ROLE_ADMIN");
			userRepository.save(user2);
	}
	
}
