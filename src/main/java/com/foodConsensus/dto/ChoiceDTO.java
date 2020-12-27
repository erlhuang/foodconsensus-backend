package com.foodConsensus.dto;

import com.foodConsensus.model.Status;

public class ChoiceDTO {
	private String proposal;
	private Status approval_status;
	
	public ChoiceDTO (String proposal, Status approval_status) {
		super();
		this.proposal = proposal;
		this.approval_status = approval_status;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	
	public Status getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(Status approval_status) {
		this.approval_status = approval_status;
	}

	@Override
	public String toString() {
		return "ChoiceDTO [proposal=" + proposal + ", approval_status=" + approval_status + "]";
	}

	
	
}
