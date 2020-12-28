package com.foodConsensus.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.MotionDTO;
import com.foodConsensus.model.Motion;
import com.foodConsensus.model.User;

@Service
public class MotionService {
	@Autowired
	private MotionDAO motionDao;
	
	@Autowired
	private UserDAO userDao;
	
	public List<Motion> getMotions() {
		return motionDao.findAll();
	}
	
	public Motion addMotion(MotionDTO dto) {
		User owner = userDao.findByName("testuser").get(0);
		Motion motion = new Motion(dto.getTitle(), owner);
		return (Motion) motionDao.save(motion);
	}
}
