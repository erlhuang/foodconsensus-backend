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
	private User userid;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "motion_id")
	private Motion motion;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "choice_id")
	private Choice voteid;
	
	public MotionUser() {
		super();
	}
	
	public MotionUser(int id, User user_id, Motion motion, Choice vote_id) {
		super();
		this.id = id;
		this.userid = user_id;
		this.motion = motion;
		this.voteid = vote_id;
	}

	public MotionUser(User user_id, Motion motion, Choice vote_id) {
		this.id = id;
		this.userid = user_id;
		this.motion = motion;
		this.voteid = vote_id;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserid() {
		return userid;
	}

	public void setUserid(User user_id) {
		this.userid = user_id;
	}

	public Motion getMotion() {
		return motion;
	}

	public void setMotion(Motion motion) {
		this.motion = motion;
	}

	public Choice getVoteid() {
		return voteid;
	}

	public void setVoteid(Choice vote_id) {
		this.voteid = vote_id;
	}
	
	
	
	
}
