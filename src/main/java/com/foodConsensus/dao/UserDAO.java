package com.foodConsensus.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.foodConsensus.model.User;



@Transactional
public interface UserDAO extends JpaRepository<User, Integer>{
	//returns all users found
	public List<User> findAll();

	public List<User> findUserById(int ownerId);
  
	public Optional<User> findByName(String name);
	public Boolean existsByName(String name);
	
}
