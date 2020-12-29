package com.foodConsensus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.MotionUserDTO;
import com.foodConsensus.model.MotionUser;
import com.foodConsensus.service.MotionUserService;

@RestController						
public class MotionUserController {

	@Autowired 
	private MotionUserService motionUserService; 
	
	@PostMapping(value="/motionuser")
	public MotionUser addMotionUser(@RequestBody MotionUserDTO motionUser) {
		return motionUserService.addMotionUser(motionUser);
	}
}
