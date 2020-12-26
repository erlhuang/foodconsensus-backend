package com.foodConsensus.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="motion")
public class Motion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "motion_id")
	private int id;
	private String title;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "id")
	private User owner; 
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<User> invitedUsers;

	public Motion() {
		super();
	}
	
	public Motion(String title, User owner) {
		super();
		this.title = title;
		this.owner = owner;
		this.invitedUsers = null;
	} 
	
	public Motion(String title, User owner, List<User> invitedUsers) {
		super();
		this.title = title;
		this.owner = owner;
		this.invitedUsers = invitedUsers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getInvitedUsers() {
		return invitedUsers;
	}
	
	public void setInvitedUsers(List<User> users) {
		this.invitedUsers = users;
	}
	
	public void addInvitedUsers(User user) {
		if (this.invitedUsers == null) {
			this.invitedUsers = new ArrayList<>();
		}
		
		this.invitedUsers.add(user);
	}
}
