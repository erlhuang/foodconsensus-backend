package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.UserDTO;
import com.foodConsensus.model.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userDao; 
	
	
	public List<User> getUsers() {
		return userDao.findAll();
	}
	
	public User addUser(UserDTO dto) {
		User user = new User(dto.getName(), dto.getPassword(), false);
		return (User) userDao.save(user);
	}
	
	public User findUser(String username) {
		return userDao.findByName(username).get(0);
	}
	
}
