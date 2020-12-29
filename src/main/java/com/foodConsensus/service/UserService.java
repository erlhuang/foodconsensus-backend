package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.UserDTO;
import com.foodConsensus.model.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userDao; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<User> getUsers() {
		return userDao.findAll();
	}
	
	public User addUser(UserDTO dto) {
		User user = new User(dto.getName(), passwordEncoder.encode(dto.getPassword()), false);
		return (User) userDao.save(user);
	}
	
	public List<User> getUserById (int id) {
		return userDao.findUserById(id);
	}
	
}
