package com.foodConsensus.dto;

public class MotionUserDTO {
	private int motionId;
	private int userId;
	
	public MotionUserDTO() {
		super();
	}

	public MotionUserDTO(int motionId, int userId) {
		super();
		this.motionId = motionId;
		this.userId = userId;
	}

	public int getMotionId() {
		return motionId;
	}

	public void setMotionId(int motionId) {
		this.motionId = motionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
