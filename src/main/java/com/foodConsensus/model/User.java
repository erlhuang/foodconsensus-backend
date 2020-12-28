package com.foodConsensus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	private String password;
	private boolean isAdmin;
	
	public User() {
		super();
	}
	
	public User(String name, String password, boolean isAdmin) {
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public User(int id, String name, String password, boolean isAdmin) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}
	
	
}