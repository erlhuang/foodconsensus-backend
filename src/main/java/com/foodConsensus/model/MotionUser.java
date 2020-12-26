package com.foodConsensus.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="motion_user")
public class MotionUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "motion_user_id")
	private int id;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "id")
	private User user;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "motion_id")
	private Motion motion;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "choice_id")
	private Choice vote;
	
	public MotionUser() {
		super();
	}
	
	public MotionUser(int id, User user, Motion motion, Choice vote) {
		super();
		this.id = id;
		this.user = user;
		this.motion = motion;
		this.vote = vote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Motion getMotion() {
		return motion;
	}

	public void setMotion(Motion motion) {
		this.motion = motion;
	}

	public Choice getVote() {
		return vote;
	}

	public void setVote(Choice vote) {
		this.vote = vote;
	}
	
	
	
	
}
