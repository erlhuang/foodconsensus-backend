package com.foodConsensus.dto;

public class MotionUserDTO2 {
	private int motionId;
	private int voteid; 
	
	public MotionUserDTO2() {
		super();
	}

	public MotionUserDTO2(int motionId, int voteid) {
		super();
		this.motionId = motionId;
		this.voteid = voteid;
	}

	public int getMotionId() {
		return motionId;
	}

	public void setMotionId(int motionId) {
		this.motionId = motionId;
	}

	public int getvoteid() {
		return voteid;
	}

	public void setvoteid(int voteid) {
		this.voteid = voteid;
	}
	

}
