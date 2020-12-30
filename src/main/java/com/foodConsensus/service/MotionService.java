package com.foodConsensus.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dao.MotionUserDAO;
import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.MotionDTO;
import com.foodConsensus.model.Motion;
import com.foodConsensus.model.MotionUser;
import com.foodConsensus.model.User;

@Service
public class MotionService {
	@Autowired
	private MotionDAO motionDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private MotionUserDAO motionUserDao;
	
	public List<Motion> getMotions() {
		return motionDao.findAll();
	}
	
	public Motion addMotion(MotionDTO dto, String username) {
		User owner = userDao.findByName(username).get();
		Motion motion = new Motion(dto.getTitle(), owner);
		return (Motion) motionDao.save(motion);
	}
	
	public List<Motion> getMotionsByUserId(String username) {
		//Only return motions that the current user is invited to
		User user =  userDao.findByName(username).get();
		
		ArrayList<Motion> motions = new ArrayList<Motion>(); 
		List<MotionUser> motionUsers = motionUserDao.findByUserid(user);
		for (MotionUser temp: motionUsers) {
			motions.add(temp.getMotion());
		}
		return motions;
	}
}
