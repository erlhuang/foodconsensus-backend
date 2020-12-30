package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.ChoiceDAO;
import com.foodConsensus.dao.UserDAO;
import com.foodConsensus.dto.ChoiceDTO;
import com.foodConsensus.model.Choice;
import com.foodConsensus.model.User;

@Service
public class ChoiceService {
	@Autowired
	private ChoiceDAO choiceDao; 
	
	@Autowired
	private UserDAO userDao;
	
	public List<Choice> getChoices() {
		return choiceDao.findAll();
	}
	public Choice addChoice(ChoiceDTO dto, String username) {
		User user = userDao.findByName(username).get();
		Choice choice = new Choice(dto.getName(), user);
		return choiceDao.save(choice);
		
//		User user = (User) userDAO.findUserById(dto.getOwnerId());
//		Choice choice = new Choice(dto.getProposal(), user, dto.getApproval_status());

	}
}
