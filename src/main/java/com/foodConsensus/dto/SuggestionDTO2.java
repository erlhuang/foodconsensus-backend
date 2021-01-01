package com.foodConsensus.dto;

import com.foodConsensus.model.Status;

public class SuggestionDTO2 {
	private Status status;
//	private int suggestionId;
	
	public SuggestionDTO2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SuggestionDTO2(Status status, int suggestionId) {
		super();
		this.status = status;
//		this.suggestionId = suggestionId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	

//	public int getSuggestionId() {
//		return suggestionId;
//	}
//	public void setSuggestionId(int suggestionId) {
//		this.suggestionId = suggestionId;
//	}
	
	
}
