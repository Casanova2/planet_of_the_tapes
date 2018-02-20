package com.planetofthetapes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.planetofthetapes.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByName(String name);
	
	User findById(Integer id);
	
	User findByEmail(String email);
	
	User findByNameAndEmail(String name, String email);

}
