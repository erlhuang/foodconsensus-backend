package com.foodConsensus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.ChoiceDAO;
import com.foodConsensus.dao.MotionChoiceDAO;
import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dao.SuggestionDAO;
import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.SuggestionDTO;
import com.foodConsensus.dto.SuggestionDTO2;
import com.foodConsensus.model.Choice;
import com.foodConsensus.model.Motion;
import com.foodConsensus.model.MotionChoice;
import com.foodConsensus.model.Status;
import com.foodConsensus.model.Suggestion;
import com.foodConsensus.model.User;

@Service
public class SuggestionService {
	@Autowired
	private SuggestionDAO suggestionDao;
	
	@Autowired
	private MotionChoiceDAO motionChoiceDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private ChoiceDAO choiceDao;
	
	@Autowired
	private MotionDAO motionDao;
	
	Logger logger = LoggerFactory.getLogger(SuggestionService.class);
	
	public List<Suggestion> getSuggestions() {
		return suggestionDao.findAll();
	}
	
	public List<Suggestion> getSuggestionById(int motionId) {
		Motion motion =  motionDao.findMotionById(motionId).get(0);
		return suggestionDao.findSuggestionByMotionid(motion);
	}
	
	public Suggestion addSuggestion(SuggestionDTO dto, String username) {
		User user = userDao.findByName(username).get();
		Motion motion = motionDao.findMotionById(dto.getMotionId()).get(0);
		Choice choice = choiceDao.findChoiceById(dto.getChoiceId()).get(0);
		Suggestion suggestion = new Suggestion(motion, choice, user, Status.PENDING);
		return (Suggestion) suggestionDao.save(suggestion);
	}
	
	public MotionChoice updateSuggestion(SuggestionDTO2 dto, int suggestionId) {
		Suggestion suggestion = suggestionDao.findSuggestionBySuggestionId(suggestionId).get(0);
		//if suggestion is approved, we should add it to the motion choices
		if (dto.getStatus() == Status.APPROVED) {
			suggestion.setApproval_status(Status.APPROVED);
			MotionChoice motionChoice = new MotionChoice(suggestion.getMotionid(), suggestion.getChoice_id());
			suggestionDao.save(suggestion);
			return (MotionChoice) motionChoiceDao.save(motionChoice);
		}
		else { //otherwise, we update suggestion to declined and return nothing
			suggestion.setApproval_status(Status.DECLINED);
			suggestionDao.save(suggestion);
			return null;
		}
	}
	
	public String deleteSuggestion(int suggestionId) {
		Suggestion suggestion = suggestionDao.findSuggestionBySuggestionId(suggestionId).get(0);
		if(suggestion == null) {
			logger.info("Suggestion: " + suggestionId + " in DELETE was not found");
			return "Suggestion was not found";
		}
		suggestionDao.delete(suggestion);
		return("Suggestion id " + suggestionId + " succesfully deleted.");
		
	}
}
