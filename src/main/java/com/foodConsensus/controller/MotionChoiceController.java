package com.foodConsensus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.MotionChoiceDTO;
import com.foodConsensus.model.MotionChoice;
import com.foodConsensus.service.MotionChoiceService;

@RestController 
public class MotionChoiceController {
	@Autowired
	private MotionChoiceService motionChoiceService;
	
	@GetMapping("/motionchoices")
	public List<MotionChoice> getMotionChoices() {
		return motionChoiceService.getMotionChoices();
	}
	
	@PostMapping(value= "/motionchoices")
	public MotionChoice addMotionChoice(@RequestBody MotionChoiceDTO motionChoice) {
		return motionChoiceService.addMotionChoice(motionChoice);
	}
	
}
