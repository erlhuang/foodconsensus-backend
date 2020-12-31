package com.foodConsensus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.ChoiceDTO;
import com.foodConsensus.model.Choice;
import com.foodConsensus.security.services.UserDetailsImpl;
import com.foodConsensus.service.ChoiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ChoiceController {
	@Autowired
	private ChoiceService choiceService;
	
	//test endpoint that gets all choices in the database
	@GetMapping("/choices")
	public List<Choice> getChoices() {
		return choiceService.getChoices();
	}
	
	@PostMapping("/choices")
	public Choice addChoice(@RequestBody ChoiceDTO choice, @AuthenticationPrincipal UserDetailsImpl user ) {
		String username = user.getName();
		return choiceService.addChoice(choice, username);
	}

}
