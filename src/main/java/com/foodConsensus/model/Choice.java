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
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="id")
	private User owner;
	
	//approval status, whether this choice is approved by the owner or not
//	@Enumerated(EnumType.STRING)
//	private Status approval_status;
	
	public Choice() {
		super();
	}

	public Choice(int id, String proposal, User owner /*, Status approval_status*/) {
		super();
		this.id = id;
		this.proposal = proposal;
		this.owner = owner;
//		this.approval_status = approval_status;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

//	public Status getApproval_status() {
//		return approval_status;
//	}
//
//	public void setApproval_status(Status approval_status) {
//		this.approval_status = approval_status;
//	}
	
	
	
}
