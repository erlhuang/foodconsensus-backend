package com.foodConsensus.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.MotionChoiceDTO;
import com.foodConsensus.model.MotionChoice;
import com.foodConsensus.security.services.UserDetailsImpl;
import com.foodConsensus.service.MotionChoiceService;

@RestController 
public class MotionChoiceController {
	@Autowired
	private MotionChoiceService motionChoiceService;
	
	Logger logger = LoggerFactory.getLogger(MotionChoiceController.class);
	
	@GetMapping("/motionchoices")
	public List<MotionChoice> getMotionChoices() {
		return motionChoiceService.getMotionChoices();
	}
	
	/*
	Only displays motion choices if user is invited to the specified motion!
	*/ 
	@GetMapping("/motionchoices/{motionId}")
	public List<MotionChoice> getMotionChoicesById(@PathVariable int motionId, @AuthenticationPrincipal UserDetailsImpl user, HttpServletResponse servletResponse) {
		String username = user.getName(); 
		List<MotionChoice> motionChoices = motionChoiceService.getMotionChoicesById(motionId, username);
		if(motionChoices == null) {
			logger.info("Motion choices returned null");
			servletResponse.setStatus(400);
		}
		return motionChoices;
	}
	
	@PostMapping(value= "/motionchoices")
	public MotionChoice addMotionChoice(@RequestBody MotionChoiceDTO motionChoice) {
		return motionChoiceService.addMotionChoice(motionChoice);
	}
	
}
