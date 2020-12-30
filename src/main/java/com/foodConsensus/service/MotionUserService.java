package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.ChoiceDAO;
import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dao.MotionUserDAO;
import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.MotionUserDTO;
import com.foodConsensus.dto.MotionUserDTO2;
import com.foodConsensus.model.Choice;
import com.foodConsensus.model.Motion;
import com.foodConsensus.model.MotionChoice;
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
	
	@Autowired
	private ChoiceDAO choiceDao;
	
	public MotionUser addMotionUser(MotionUserDTO dto, String username) {
		User user = userDao.findUserById(dto.getUserId()).get(0);
		Motion motion = motionDao.findMotionById(dto.getMotionId()).get(0);
		MotionUser motionUser = new MotionUser(user, motion, null);
		return (MotionUser) motionUserDao.save(motionUser);
	}
	
	public MotionUser updateMotionUser(MotionUserDTO2 dto2, String username) {
		User user = userDao.findByName(username).get();
		List<MotionUser> motionUserList = motionUserDao.findByUserid(user);
		MotionUser motionUser = null;
		Motion motion = motionDao.findMotionById(dto2.getMotionId()).get(0);
		for (MotionUser temp: motionUserList) {
			if (temp.getMotion().getId() == motion.getId()) {
				motionUser = temp;
				break; 
			}
		}
		if(motionUser == null) {
			return null;
		}
		Choice voteid = choiceDao.findChoiceById(dto2.getvoteid()).get(0); 
		motionUser.setVoteid(voteid);
		return (MotionUser) motionUserDao.save(motionUser);
	}
}
