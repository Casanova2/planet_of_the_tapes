package com.planetofthetapes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.planetofthetapes.entity.Pack;


public interface PackRepository extends JpaRepository<Pack, Integer>{
	
	Pack findByName(String name);

}
