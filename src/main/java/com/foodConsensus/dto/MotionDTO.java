package com.foodConsensus.dto;


public class MotionDTO {
	private String title;

	public MotionDTO() {
		super();
	}

	public MotionDTO(String title) {
		super();
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "MotionDTO [title=" + title + "]";
	}
	
	
}
