package com.foodConsensus.dto;

import java.util.List;

public class MotionDTO {
	private String title;
	private List<String> invitedUsers;
	private List<String> choices;
	public MotionDTO(String title, List<String> invitedUsers, List<String> choices) {
		super();
		this.title = title;
		this.invitedUsers = invitedUsers;
		this.choices = choices;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getInvitedUsers() {
		return invitedUsers;
	}
	public void setInvitedUsers(List<String> invitedUsers) {
		this.invitedUsers = invitedUsers;
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}
	
	
}
