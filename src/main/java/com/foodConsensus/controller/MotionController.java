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
public class MotionController {
	
	@Autowired
	private MotionService motionService;
	
	@GetMapping("/motions")
	public List<Motion> getMotions() {
//		return motionService.getMotions();
		return motionService.getMotionsByUserId();
	}
	
	@PostMapping(value= "/motions")
	public Motion addMotion(@RequestBody MotionDTO motion) {
		return motionService.addMotion(motion);
	}
	
}
