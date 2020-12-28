package com.foodConsensus.service;

import java.util.ArrayList;
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
		List<User> invitedUsers = new ArrayList<User>(); 
		for (int i = 0; i < dto.getInvitedUsers().size(); i++) {
			invitedUsers.add(userDao.findByName(dto.getInvitedUsers().get(i)).get(0));
		}
		User owner = userDao.findByName("testuser").get(0);
		Motion motion = new Motion(dto.getTitle(), owner, invitedUsers);
		return (Motion) motionDao.save(motion);
	}
}
