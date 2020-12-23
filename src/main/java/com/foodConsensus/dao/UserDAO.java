package com.foodConsensus.dao;

import java.util.List;

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
	
	
//	@Autowired
//	SessionFactory sessionFactory; 
//	
//	public User addUser(User u) {
//		Session session = sessionFactory.getCurrentSession();
//		int id = (int) session.save(u);
//		
//		User user = new User(u.getName(), u.getPassword(), false);
//		user.setId(id);
//		
//		return user;
//	}
//	
//	public List<User> getUsers() {
//		Session session = sessionFactory.getCurrentSession();
//		
//		return session.createQuery("from User u").getResultList();
//	}
	
}
