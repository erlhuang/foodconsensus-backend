package com.foodConsensus.dto;

import com.foodConsensus.model.Status;

public class ChoiceDTO {
	private String name;
	
	public ChoiceDTO () {
		super(); 
	}
	
	public ChoiceDTO (String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "ChoiceDTO [name=" + name + "]";
	}

	

	
	
}
