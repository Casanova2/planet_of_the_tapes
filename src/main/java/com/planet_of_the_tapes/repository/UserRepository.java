package com.planet_of_the_tapes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.planet_of_the_tapes.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByName(String name);
	
	User findById(int id);

}
