package com.planetofthetapes.repository;

import java.util.List;

//import com.planet_of_the_tapes.entity.Genre;
//import com.planet_of_the_tapes.entity.Product;
//import com.planet_of_the_tapes.entity.ProductType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.planetofthetapes.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findByName(String name);
	
	Product findById(Integer id);
	
	Product findByNameAndType(String name, String type);

	Product findByNameLikeIgnoreCase(String name);
	
	Page<Product> findByGenreAndType (String genre,String type,Pageable page);
	
	List<Product> findByGenreAndType (String genre,String type);
	
	Product findByType (String type);
	
	List<Product> findGroupByGenre(String genre);
	
	List<Product> findGroupByType(String type);
	
	//List<Product> findGroupByTypeAndByGenre(String type, String genre);
	
	Page<Product> findGroupByType(String type, Pageable page);
	
	Page<Product> findByGenre(String genre, Pageable page);

	Page<Product> findByNameLikeIgnoreCase(String name, Pageable page);
	
}