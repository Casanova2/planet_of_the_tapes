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

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@ManyToMany
	private List<Product> products;
	
	@Column(unique = true)
	private String state;
	private String pay;
	private String type;
	

	protected Pedido() {
	}

public Pedido(Integer id, User user, List<Product> products, String state, String pay, String type) {
	this.id = id;
	this.state = state;
	this.pay = pay;
	this.type = type;
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


}
