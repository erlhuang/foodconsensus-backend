package com.foodConsensus.dto;

public class SuggestionDTO {
	private int motionId;
	private int choiceId;
	
	public SuggestionDTO() {
		super();

	}

	public SuggestionDTO(int motionId, int choiceId) {
		super();
		this.motionId = motionId;
		this.choiceId = choiceId;
	}

	public int getMotionId() {
		return motionId;
	}

	public void setMotionId(int motionId) {
		this.motionId = motionId;
	}

	public int getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}


	@Override
	public String toString() {
		return "SuggestionDTO [motionId=" + motionId + ", choiceId=" + choiceId + "]";
	} 
	
	
}
