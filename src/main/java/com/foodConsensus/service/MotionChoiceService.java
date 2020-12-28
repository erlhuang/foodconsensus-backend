package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.ChoiceDAO;
import com.foodConsensus.dao.MotionChoiceDAO;
import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dto.MotionChoiceDTO;
import com.foodConsensus.model.Choice;
import com.foodConsensus.model.Motion;
import com.foodConsensus.model.MotionChoice;

@Service
public class MotionChoiceService {

	@Autowired
	private MotionChoiceDAO motionChoiceDao;
	
	@Autowired
	private MotionDAO motionDao; 
	
	@Autowired
	private ChoiceDAO choiceDao;
	
	
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
}
