package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.ChoiceDAO;
import com.foodConsensus.dao.MotionChoiceDAO;
import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dao.MotionUserDAO;
import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.MotionChoiceDTO;
import com.foodConsensus.model.Choice;
import com.foodConsensus.model.Motion;
import com.foodConsensus.model.MotionChoice;
import com.foodConsensus.model.MotionUser;
import com.foodConsensus.model.User;

@Service
public class MotionChoiceService {

	@Autowired
	private MotionChoiceDAO motionChoiceDao;
	
	@Autowired
	private MotionDAO motionDao; 
	
	@Autowired
	private ChoiceDAO choiceDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private MotionUserDAO motionUserDao;
	
	public List<MotionChoice> getMotionChoices() {
		return motionChoiceDao.findAll();
	}
	
	public MotionChoice addMotionChoice(MotionChoiceDTO dto) {
		//first we must find the corresponding motions and choices
		Motion motion = motionDao.findMotionById(dto.getMotionId()).get(0);
		Choice choice = choiceDao.findChoiceById(dto.getChoiceId()).get(0);
		MotionChoice motionChoice = new MotionChoice(motion, choice);
		
		//then we add it in the database
		return (MotionChoice) motionChoiceDao.save(motionChoice);
	}
	
	public List<MotionChoice> getMotionChoicesById(int motionId, String username) {
		User user = userDao.findByName(username).get();
		Motion motion = motionDao.findMotionById(motionId).get(0);
		//here we check if the user is inside the list (essentially if hes invited to this motion)
		List<MotionUser> listOfMotionUsers = motionUserDao.findByMotion(motion);
		boolean proceed = false; 
		for (MotionUser temp : listOfMotionUsers) {
			if (temp.getUserid().getId() == user.getId()) {
				proceed = true;
				break;
			}
		}
		
	    if(proceed) {
	    	return motionChoiceDao.findByMotionId(motionId); 
	    }
	    else {
	    	return null;
	    }
	}
}
