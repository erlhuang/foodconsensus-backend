package com.foodConsensus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodConsensus.dto.UserDTO;
import com.foodConsensus.model.User;
import com.foodConsensus.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	//test endpoint that gets all users in the database
	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	//request body assumes json is sent in body of the request
	@PostMapping(value = "/users")
	public User addUser(@RequestBody UserDTO user) {
		return userService.addUser(user);
	}
}