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

@Entity
@Table(name="suggestion")
public class Suggestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="suggestion_id")
	private int suggestionId;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "motion_id")
	private Motion motion_id;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "choice_id")
	private Choice choice_id; 
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="id")
	private User user_id;
	
	//approval status, whether this choice is approved by the owner or not
	@Enumerated(EnumType.STRING)
	private Status approval_status;

	public Suggestion() {
		super();
	}
	
	public Suggestion(Motion motion_id, Choice choice_id, User user_id, Status approval_status) {
		super();
		this.motion_id = motion_id;
		this.choice_id = choice_id;
		this.user_id = user_id;
		this.approval_status = approval_status;
	}
	public Suggestion(int suggestionId, Motion motion_id, Choice choice_id, User user_id, Status approval_status) {
		super();
		this.suggestionId = suggestionId;
		this.motion_id = motion_id;
		this.choice_id = choice_id;
		this.user_id = user_id;
		this.approval_status = approval_status;
	}
	

	public int getSuggestionId() {
		return suggestionId;
	}

	public void setSuggestionId(int suggestionId) {
		this.suggestionId = suggestionId;
	}

	public Motion getMotion_id() {
		return motion_id;
	}
	public void setMotion_id(Motion motion_id) {
		this.motion_id = motion_id;
	}
	public Choice getChoice_id() {
		return choice_id;
	}
	public void setChoice_id(Choice choice_id) {
		this.choice_id = choice_id;
	}
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	public Status getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(Status approval_status) {
		this.approval_status = approval_status;
	}
	@Override
	public String toString() {
		return "Suggestion [suggestion_id=" + suggestionId + ", motion_id=" + motion_id + ", choice_id=" + choice_id
				+ ", user_id=" + user_id + ", approval_status=" + approval_status + "]";
	}
	

}
