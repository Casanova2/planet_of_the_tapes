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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(unique = true)
	private String name;
	private String passwordHash;
	private String dni;
	private String email;
	private String telephone;
	private boolean viewTelephone;
	private String address;
	private String avatar;
	
	@OneToOne
	private Pedido pedidoActual; 

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@OneToMany
	private List<Pedido> orders;

	protected User() {
	}

	public User(String name, String passwordHash, String dni, String email, String telephone, String address, String avatar, String... roles) {

		this.name = name;
		String pass = passwordHash;
		this.passwordHash = new BCryptPasswordEncoder().encode(passwordHash) ;
		this.dni = dni;
		this.email = email;
		this.telephone = telephone;
		this.setViewTelephone(false);
		this.address = address;
		this.avatar = avatar;
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.pedidoActual = null;
		
	}
	
	public String changeAvatarName(String name) {
		
		return this.avatar = "img/admin/avatar/" + name + ".png";
	}
	
	public Pedido getPedidoActual() {
		return pedidoActual;
	}

	public void setPedidoActual(Pedido pedidoActual) {
		this.pedidoActual = pedidoActual;
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


	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Pedido> getOrders() {
		return orders;
	}

	public void setOrders(List<Pedido> orders) {
		this.orders = orders;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPassword(String passwordHash) {
		this.passwordHash = passwordHash;
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
				"\n view telephone: " + this.isViewTelephone();
	}

}

