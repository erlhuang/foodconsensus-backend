package com.foodConsensus.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//This table represents one possible choice in a motion.
@Entity
@Table(name="choice")
public class Choice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "choice_id")
	private int id;
	
	//The name in the choice
	@Column(nullable=false, unique=true)
	private String name;
	
	//The user who suggested this choice
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="id")
	private User owner_id;
	
	//optional image url
	private String imageurl;

	
	public Choice() {
		super();
	}
	
	public Choice(String name, User owner_id) {
		super();
		this.name = name;
		this.owner_id = owner_id;
	}

	public Choice(String name, User owner_id, String imageurl) {
		super();
		this.name = name;
		this.owner_id = owner_id;
		this.imageurl = imageurl; 
	}

	public Choice(int id, String name, User owner_id) {
		super();
		this.id = id;
		this.name = name;
		this.owner_id = owner_id;
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

	public User getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(User owner_id) {
		this.owner_id = owner_id;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "Choice [id=" + id + ", name=" + name + ", owner_id=" + owner_id + ", imageurl=" + imageurl + "]";
	}
	
}
