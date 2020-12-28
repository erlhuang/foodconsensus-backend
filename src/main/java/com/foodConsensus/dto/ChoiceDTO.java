package com.foodConsensus.dto;

import com.foodConsensus.model.Status;

public class ChoiceDTO {
	private String proposal;
	private int ownerId;
	private Status approval_status;
	
	public ChoiceDTO (String proposal, int ownerId, Status approval_status) {
		super();
		this.proposal = proposal;
		this.ownerId = ownerId;
		this.approval_status = approval_status;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	
	
	public int getOwnerId() {
		return ownerId;
	}

	public void setownerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public Status getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(Status approval_status) {
		this.approval_status = approval_status;
	}

	@Override
	public String toString() {
		return "ChoiceDTO [proposal=" + proposal + ", ownerId=" + ownerId + ", approval_status=" + approval_status + "]";
	}

	

	
	
}
