package com.foodConsensus.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userDAO.findByName(name)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + name));
		return UserDetailsImpl.build(user);
	}

}
