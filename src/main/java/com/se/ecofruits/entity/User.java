package com.se.ecofruits.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Users")
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;
	private String fullName;
	private String address;
	private String email;
	private String phone;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "roleID")
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Cart> carts;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	public User(int userID, String fullName, String address, String email, String phone, String password, Role role) {
		super();
		this.userID = userID;
		this.fullName = fullName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	public User() {
		super();
	}

	

	public User(int userID, String phone) {
		super();
		this.userID = userID;
		this.phone = phone;
	}
	
	

	public User(int userID, String fullName, String phone) {
		super();
		this.userID = userID;
		this.fullName = fullName;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", fullName=" + fullName + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ", password=" + password + ", role=" + role + "]";
	}
}
