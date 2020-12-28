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
	
	//The proposal in the choice
	private String proposal;
	
	//The user who suggested this choice
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="id")
	private User owner_id;
	
	public Choice() {
		super();
	}
	public Choice(String proposal, User owner_id) {
		super();
		this.proposal = proposal;
		this.owner_id = owner_id;
	}

	public Choice(int id, String proposal, User owner_id) {
		super();
		this.id = id;
		this.proposal = proposal;
		this.owner_id = owner_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public User getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(User owner_id) {
		this.owner_id = owner_id;
	}

	@Override
	public String toString() {
		return "Choice [id=" + id + ", proposal=" + proposal + ", owner_id=" + owner_id + "]";
	}
	
	
	
}
