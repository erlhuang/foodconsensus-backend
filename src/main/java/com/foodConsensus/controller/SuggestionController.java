package com.foodConsensus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.MotionDTO;
import com.foodConsensus.dto.SuggestionDTO;
import com.foodConsensus.dto.SuggestionDTO2;
import com.foodConsensus.model.MotionChoice;
import com.foodConsensus.model.Suggestion;
import com.foodConsensus.security.services.UserDetailsImpl;
import com.foodConsensus.service.SuggestionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SuggestionController {
	@Autowired
	private SuggestionService suggestionService; 
	
	@GetMapping(value="/suggestions/{motionId}")
	public List<Suggestion> getSuggestionById(@PathVariable int motionId) {
		return suggestionService.getSuggestionById(motionId);
	}
	
	@GetMapping(value="/suggestions")
	public List<Suggestion> getSuggestions(){
		return suggestionService.getSuggestions();
	}
	
	@PostMapping(value= "/suggestions")
	public Suggestion addSuggestion(@RequestBody SuggestionDTO suggestion,@AuthenticationPrincipal UserDetailsImpl user){
		String username = user.getName();
		return suggestionService.addSuggestion(suggestion, username);
	}
	
	@PostMapping(value="/suggestions/{suggestionId}")
	public MotionChoice updateSuggestion(@RequestBody SuggestionDTO2 suggestion, @PathVariable int suggestionId) {
		return suggestionService.updateSuggestion(suggestion, suggestionId);
	}
	
	@DeleteMapping(value="/suggestions/{suggestionId}")
	public String deleteSuggestion(@PathVariable int suggestionId) {
		return suggestionService.deleteSuggestion(suggestionId);
	}
	
}
