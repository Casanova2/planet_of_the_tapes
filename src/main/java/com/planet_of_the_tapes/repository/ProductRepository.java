package com.planet_of_the_tapes.repository;

import java.util.List;

//import com.planet_of_the_tapes.entity.Genre;
//import com.planet_of_the_tapes.entity.Product;
//import com.planet_of_the_tapes.entity.ProductType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository /*extends JpaRepository<Product, Integer>*/{
	/*
	Product findByName(String name);
	
	Product findByNameLikeIgnoreCase(String name);

	List<Product> findByDirector(String director);

	List<Product> findByCast(String cast);

	List<Product> findByType(ProductType productType);
	
	List<Product> findByGenre(Genre genre);
	
	Page<Product> findByProductType(ProductType productType, Pageable page);

	Page<Product> findByTitleLikeIgnoreCase(String title, Pageable page);
	
	Page<Product> findByTitleLikeIgnoreCaseOrGenreNameLikeIgnoreCaseOrDirectorLikeIgnoreCaseOrCastLikeIgnoreCase(String title, String genre_name, String director, String cast, Pageable page);	
*/
}