package com.se.ecofruits.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="Roles")
public class Role implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleID;
	private String title;
	private boolean active;
	private String description;
	
	@OneToMany(mappedBy = "role" ,fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	List<User> users;
	
	public Role(int roleID, String title, boolean active, String description) {
		super();
		this.roleID = roleID;
		this.title = title;
		this.active = active;
		this.description = description;
	}

	public Role() {
		super();
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", title=" + title + ", active=" + active + ", desciption=" + description
				+ "]";
	}

	
	
}
