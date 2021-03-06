package com.foodConsensus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.MotionUserDTO;
import com.foodConsensus.dto.MotionUserDTO2;
import com.foodConsensus.model.MotionUser;
import com.foodConsensus.security.services.UserDetailsImpl;
import com.foodConsensus.service.MotionUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController						
public class MotionUserController {

	@Autowired 
	private MotionUserService motionUserService; 
	
	@PostMapping(value="/motionuser")
	public MotionUser addMotionUser(@RequestBody MotionUserDTO motionUser, @AuthenticationPrincipal UserDetailsImpl user) {
		String username = user.getName(); 
		return motionUserService.addMotionUser(motionUser, username);
	}
	
	@PutMapping(value="/motionuser")
	public MotionUser updateMotionUser(@RequestBody MotionUserDTO2 motionUser, @AuthenticationPrincipal UserDetailsImpl user) {
		String username = user.getName(); 
		return motionUserService.updateMotionUser(motionUser, username);
	}
}
