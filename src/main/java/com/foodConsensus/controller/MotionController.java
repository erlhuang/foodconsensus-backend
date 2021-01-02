package com.foodConsensus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.MotionDTO;
import com.foodConsensus.model.Motion;
import com.foodConsensus.security.services.UserDetailsImpl;
import com.foodConsensus.service.MotionService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController 
public class MotionController {
	
	@Autowired
	private MotionService motionService;
	
	Logger logger = LoggerFactory.getLogger(MotionController.class);
	
	@GetMapping("/motions")
	public List<Motion> getMotions(@AuthenticationPrincipal UserDetailsImpl user) {
//		return motionService.getMotions();
		String username = user.getName();
		logger.info("username test: " + username);
		return motionService.getMotionsByUserId(username);
	}
	
	
	@PostMapping(value= "/motions")
	public Motion addMotion(@RequestBody MotionDTO motion, @AuthenticationPrincipal UserDetailsImpl user) {
		String username = user.getName();
		return motionService.addMotion(motion, username);
	}
	
	@GetMapping(value = "/motions/{motionId}")
	public Motion getMotionById(@PathVariable int motionId) {
		return motionService.getMotionById(motionId);
	}
	
	@PutMapping(value= "/motions/{motionId}")
	public Motion updateMotion(@PathVariable int motionId, @AuthenticationPrincipal UserDetailsImpl user) {
		String username = user.getName();
		return motionService.updateMotion(motionId, username);
	}
}
