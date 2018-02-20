package com.planet_of_the_tapes.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToMany(mappedBy="products")
	private List<Pedido> pedidos;

	@Column(unique = true)
	private String name;
	private String description;
	private String type;
	private String genre;
	private int stock;
	private double pbuy ;
	private double prent;
	private int score;
	private String trailer;
	private String director;
	private String cast;
	private int year;
	private String urlimg;
	


	protected Product() {
	}



	public Product(String name, String description, String type, String genre, int stock, double pbuy,
		double prent, int score, String trailer, String director, String cast, int year) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.genre = genre;
		this.stock = stock;
		this.pbuy = pbuy;
		this.prent = prent;
		this.score = score;
		this.trailer = trailer;
		this.director = director;
		this.cast = cast;
		this.year = year;
	}
	
	public Product(String name, String description, String type, String genre, int stock, double pbuy,
			double prent, int score, String trailer, String director, String cast, int year, String urlimg) {
			this.name = name;
			this.description = description;
			this.type = type;
			this.genre = genre;
			this.stock = stock;
			this.pbuy = pbuy;
			this.prent = prent;
			this.score = score;
			this.trailer = trailer;
			this.director = director;
			this.cast = cast;
			this.year = year;
			this.urlimg = urlimg;
		}
	

	public Integer getId() {
		return id;
	}



	public Integer getAndIncrement() {
		return id++;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public double getPbuy() {
		return pbuy;
	}



	public void setPbuy(float pbuy) {
		this.pbuy = pbuy;
	}



	public double getPrent() {
		return prent;
	}



	public void setPrent(float prent) {
		this.prent = prent;
	}



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public String getTrailer() {
		return trailer;
	}



	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}



	public String getDirector() {
		return director;
	}



	public void setDirector(String director) {
		this.director = director;
	}



	public String getCast() {
		return cast;
	}



	public void setCast(String cast) {
		this.cast = cast;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public String getUrlimg() {
		return urlimg;
	}



	public void setUrlimg(String urlimg) {
		this.urlimg = urlimg;
	}
	
	
	
}

	