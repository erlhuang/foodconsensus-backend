package com.foodConsensus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.MotionDTO;
import com.foodConsensus.model.Motion;
import com.foodConsensus.service.MotionService;

@RestController 
public class MotionChoiceController {
	@Autowired
	private MotionService motionChoiceService;
	
	@GetMapping("/motionchoices")
	public List<Motion> getMotionChoices() {
		return motionChoiceService.getMotions();
	}
	
	@PostMapping(value= "/motionchoices")
	public Motion addMotionChoice(@RequestBody MotionDTO motion) {
		return motionChoiceService.addMotion(motion);
	}
	
}
