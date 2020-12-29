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

//this table maps the motions with its respective possible choices
@Entity
@Table(name="motion_choice")
public class MotionChoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "motion_choice_id")
	private int id;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "motion_id")
	private Motion motion;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "choice_id")
	private Choice choice;
	
	public MotionChoice() {
		super();
	}

	public MotionChoice(int id, Motion motion, Choice choice) {
		super();
		this.id = id;
		this.motion = motion;
		this.choice = choice;
	}
	
	public MotionChoice(Motion motion, Choice choice) {
		super();
		this.motion = motion;
		this.choice = choice;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Motion getMotion() {
		return motion;
	}

	public void setMotion(Motion motion) {
		this.motion = motion;
	}

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}
	
	
	
}
