package com.foodConsensus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.model.Choice;
import com.foodConsensus.service.ChoiceService;

@RestController
public class ChoiceController {
	@Autowired
	private ChoiceService choiceService;
	
	//test endpoint that gets all choices in the database
	@GetMapping("/choices")
	public List<Choice> getChoices() {
		return choiceService.getChoices();
	}
	
	@PostMapping("/addChoices")
	public Choice addChoice(@RequestBody Choice choice) {
		return choiceService.addChoice(choice);
	}

}
