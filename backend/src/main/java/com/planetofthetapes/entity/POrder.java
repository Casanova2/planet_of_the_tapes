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

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;
import com.planetofthetapes.entity.Product.Basic;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class POrder {

	public interface Basic {}
	public interface ProductRelationOrder{}
	public interface PackRelation{}
	public interface UserRelation{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private Integer id;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonView(ProductRelationOrder.class)
	private List<Product> products = new ArrayList<Product>();
	
	@OneToMany
	@JsonView(PackRelation.class)
	private List<Pack> packs = new ArrayList<Pack>();

	@ManyToOne
	@JsonView(UserRelation.class)
	private User user;
	
	@JsonView(Basic.class)
	private String state;
	@JsonView(Basic.class)
	private String pay;
	@JsonView(Basic.class)
	private String type;
	@JsonView(Basic.class)
	private double total;
	
	protected POrder() {
	}
	
	
public POrder(String state, String pay, String type, double total) {
	this.state = state;
	this.pay = pay;
	this.type = type;
	this.total = total;
}

public List<Product> getProducts() {
	return products;
}

public void setProducts(List<Product> products) {
	this.products = products;
}

public double getTotal() {
	return total;
}

public List<Pack> getPacks() {
	return packs;
}

public void setPacks(List<Pack> packs) {
	this.packs = packs;
}

public void setTotal(double total) {
	this.total = total;
}

public void addProduct(Product p) {
	this.products.add(p);
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}


public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getPay() {
	return pay;
}

public void setPay(String pay) {
	this.pay = pay;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public void addPack(Pack p) {
	this.packs.add(p);
	
}

@Override
public String toString() {
	return "POrder [id=" + id + ", products=" + products + ", packs=" + packs + ", state=" + state
			+ ", type=" + type + ", pay=" + pay + ", total=" + total + "]";
}


}
