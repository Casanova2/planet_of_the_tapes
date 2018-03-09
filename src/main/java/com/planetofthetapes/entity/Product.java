package com.planetofthetapes.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.entity.Pack.ProductRelation;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Product {
	
	public interface Basic{}
	public interface OrderRelation{}
	public interface PackRelation{}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private Integer id;
	
	@ManyToMany(mappedBy = "products")
	@JsonView(OrderRelation.class)
	private List<POrder> porders = new ArrayList<POrder>();
	
	@ManyToMany(mappedBy="products")
	@JsonView(PackRelation.class)
	private List<Pack> packs = new ArrayList<Pack>();
	
	@JsonView(Basic.class)
	private String name;
	@JsonView(Basic.class)
	private String description;
	@JsonView(Basic.class)
	private String type;
	@JsonView(Basic.class)
	private String genre;
	@JsonView(Basic.class)
	private int stock;
	@JsonView(Basic.class)
	private double pbuy ;
	@JsonView(Basic.class)
	private double prent;
	@JsonView(Basic.class)
	private int score;
	@JsonView(Basic.class)
	private String trailer;
	@JsonView(Basic.class)
	private String director;
	@JsonView(Basic.class)
	private String cast;
	@JsonView(Basic.class)
	private int year;
	@JsonView(Basic.class)
	private String urlimg;
	@JsonView(Basic.class)
	public String selected;
	


	protected Product() {
	}



	public Product(String name, String description, String type, String genre, int stock, double  pbuy,
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
			double prent, int score, String trailer, String director, String cast, int year, String urlimg, String selected) {
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
			this.selected = selected;
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
	
	public String getSelected() {
		return selected;
	}
	public List<POrder> getPedidos() {
		return porders;
	}

	public List<Pack> getPacks() {
		return packs;
	}



	public void setPacks(List<Pack> packs) {
		this.packs = packs;
	}



	public List<POrder> getPorders() {
		return porders;
	}

	
	public void setSelected(String selected) {
		this.selected = selected;
	}



	public void Option(String name, boolean seleted){
        this.name = name;
        this.selected = seleted ? "selected" : "";
    }
	
	public void setPorders(List<POrder> pedidos) {
		this.porders = pedidos;
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



	public void setPbuy(double pbuy) {
		this.pbuy = pbuy;
	}



	public double getPrent() {
		return prent;
	}



	public void setPrent(double prent) {
		this.prent = prent;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", pedidos=" + porders + ", name=" + name + ", description=" + description
				+ ", type=" + type + ", genre=" + genre + ", stock=" + stock + ", pbuy=" + pbuy + ", prent=" + prent
				+ ", score=" + score + ", trailer=" + trailer + ", director=" + director + ", cast=" + cast + ", year="
				+ year + ", urlimg=" + urlimg + "]";
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

	