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

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.entity.Product.Basic;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Pack{
	
	public interface Basic{}
	public interface ProductRelation{}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private Integer id;
	
	@JsonView(Basic.class)
	private String name;
	@JsonView(Basic.class)
	private Integer price;
	
	@ManyToMany
	@JsonView(ProductRelation.class)
	private List<Product> products;
	
	@JsonView(Basic.class)
	private String img;
	
	
	protected Pack() {
		
	}
	
	public Pack(String name, Integer price,List<Product> products, String img) {
		this.name = name;
		this.price = price;
		this.products = products;
		this.img = img;
	}
	
	public Pack(String name, Integer price) {
		this.name = name;
		this.price = price;
	}
	
	public Pack(String name, Integer price,List<Product> products) {
		this.name = name;
		this.price = price;
		this.products = products;
	}
	
	public Pack(Integer id, String name, Integer price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	
	public Pack(String name, Integer price, String img) {
		this.name = name;
		this.price = price;
		this.img = img;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setProducts(List<Product> products){
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Pack [id=" + id + ", name" + name + ", price" + price + ", products=" + products + ", img=" + img + "]";
	}

}