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
import javax.persistence.OneToMany;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(unique = true)
	private String name;
	private String password;
	private String dni;
	private String email;
	private String telephone;
	private boolean viewTelephone;
	private String address;
	private String role;
	private String avatar;
	
	//@OneToMany
	//private List<Order> orders;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//	private List<Fine> penalties;

//	@ElementCollection(fetch = FetchType.EAGER)
//	private List<String> roles;

	protected User() {
	}

	public User(String name, String password, String dni, String email, String telephone, String address, String role, String avatar) {

		this.name = name;
		this.password= password;
		this.dni = dni;
		this.email = email;
		this.telephone = telephone;
		this.setViewTelephone(false);
		this.address = address;
		this.role = role;
		this.avatar = avatar;
	}

	public Integer getId() {
		return id;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isViewTelephone() {
		return viewTelephone;
	}

	public void setViewTelephone(boolean viewTelephone) {
		this.viewTelephone = viewTelephone;
	}

	
	public String toString() {
		return "User id: " + this.getId() +
				"\n name: " + this.name +
				"\n email: " + this.email +
				"\n telephone: " + this.telephone +
				"\n view telephone: " + this.isViewTelephone() +
				"\n role: " + this.role;
	}

}

