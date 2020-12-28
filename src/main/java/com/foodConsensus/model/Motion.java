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
	private User owner_id; 
	
	public Motion() {
		super();
	}
	
	public Motion(String title, User owner_id) {
		super();
		this.title = title;
		this.owner_id = owner_id;
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

	public User getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(User owner_id) {
		this.owner_id = owner_id;
	}

}
