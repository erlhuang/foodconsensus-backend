package com.foodConsensus.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.controller.MotionController;
import com.foodConsensus.dao.ChoiceDAO;
import com.foodConsensus.dao.MotionChoiceDAO;
import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dao.MotionUserDAO;
import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.MotionDTO;
import com.foodConsensus.model.Choice;
import com.foodConsensus.model.Motion;
import com.foodConsensus.model.MotionChoice;
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
	
	@Autowired
	private MotionChoiceDAO motionChoiceDao;
	
	@Autowired
	private ChoiceDAO choiceDao;
	
	Logger logger = LoggerFactory.getLogger(MotionService.class);
	
	public List<Motion> getMotions() {
		return motionDao.findAll();
	}
	
	public Motion getMotionById(int motionId) {
		return motionDao.findMotionById(motionId).get(0);
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
	
	//TODO: FIGURE OUT HOW TO RESOLVE TIES
	public Motion updateMotion(int motionId, String username) {
		User user =  userDao.findByName(username).get();
		Motion motion = motionDao.findMotionById(motionId).get(0);
		if (motion.getOwner_id() != user) {
			logger.info("Attempted to update motion by a user that isn't the owner. Owner: " + motion.getOwner_id() + " User attempting update: + " + username);
			return null;
		}
		motion.setStatus(true);
		List<MotionChoice> motionChoices = motionChoiceDao.findByMotionId(motion.getId());
		//create a hash map to start keeping track of votes for each possible choice for the motion
		HashMap<String, Integer> motionVotes = new HashMap<String, Integer>();
		for (MotionChoice temp: motionChoices) {
			motionVotes.put(temp.getChoice().getName(), 0);
		}
		
		//now, we find the invited users and tally their votes 
		List<MotionUser> motionUsers = motionUserDao.findByMotion(motion);
		int currentVote;
		for (MotionUser temp: motionUsers) {
			//add 1 to the respective motion vote 
			if (temp.getVoteid() != null) {
				currentVote = motionVotes.get(temp.getVoteid().getName());
				currentVote++; 
				motionVotes.put(temp.getVoteid().getName(), currentVote);
			}
		}
		logger.info("Tallied votes: " + motionVotes);
		int max = Collections.max(motionVotes.values());
		String motionWinner = null;
		for (Entry<String, Integer> entry: motionVotes.entrySet()) {
			if (entry.getValue() == max) {
				motionWinner = entry.getKey();
				break;
			}
		}
		Choice winner = choiceDao.findChoiceByName(motionWinner).get(0);
		motion.setWinner(winner);
		return (Motion) motionDao.save(motion);
		
	}
}
