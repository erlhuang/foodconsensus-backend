package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dao.MotionUserDAO;
import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.MotionUserDTO;
import com.foodConsensus.model.Motion;
import com.foodConsensus.model.MotionUser;
import com.foodConsensus.model.User;


@Service
public class MotionUserService {

	@Autowired
	private MotionDAO motionDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired 
	private MotionUserDAO motionUserDao;
	
	public MotionUser addMotionUser(MotionUserDTO dto) {
		//CHANGE THIS WHEN AUTHENTICATION WORKS!
		User user = userDao.findByName("testuser").get(0);
		Motion motion = motionDao.findMotionById(dto.getMotionId()).get(0);
		
		MotionUser motionUser = new MotionUser(user, motion, null);
		return (MotionUser) motionUserDao.save(motionUser);
	}
}
