package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.ChoiceDAO;
import com.foodConsensus.dto.ChoiceDTO;
import com.foodConsensus.model.Choice;

@Service
public class ChoiceService {
	@Autowired
	private ChoiceDAO choiceDao; 
	
	
	public List<Choice> getChoices() {
		return choiceDao.findAll();
	}
	public Choice addChoice(ChoiceDTO dto) {
		Choice choice = new Choice(dto.getProposal(), dto.getApproval_status());
		return (Choice) choiceDao.save(choice);
	}
}
