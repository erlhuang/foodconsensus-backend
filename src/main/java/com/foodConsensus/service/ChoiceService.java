package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.ChoiceDAO;
import com.foodConsensus.model.Choice;

@Service
public class ChoiceService {
	@Autowired
	private ChoiceDAO choiceDao; 
	
	public List<Choice> getChoices() {
		return choiceDao.findAll();
	}
	public Choice addChoice(Choice choice) {
		return choiceDao.save(choice);
		
//		User user = (User) userDAO.findUserById(dto.getOwnerId());
//		Choice choice = new Choice(dto.getProposal(), user, dto.getApproval_status());

	}
}
