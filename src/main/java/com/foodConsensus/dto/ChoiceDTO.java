package com.foodConsensus.dto;

import com.foodConsensus.model.Status;

public class ChoiceDTO {
	private String name;
	private String imageurl; 
	
	public ChoiceDTO () {
		super(); 
	}
	
	public ChoiceDTO (String name) {
		super();
		this.name = name;
	}
	
	public ChoiceDTO (String name, String imageurl) {
		super();
		this.name = name;
		this.imageurl = imageurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "ChoiceDTO [name=" + name + "]";
	}

	

	
	
}
