package com.foodConsensus.dto;

import java.util.List;

public class MotionDTO {
	private String title;
	private List<String> invitedUsers;
	
	public MotionDTO(String title, List<String> invitedUsers) {
		super();
		this.title = title;
		this.invitedUsers = invitedUsers;
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
	
	
}
