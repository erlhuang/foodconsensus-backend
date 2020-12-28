package com.foodConsensus.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.foodConsensus.model.Choice;

public interface ChoiceDAO extends JpaRepository<Choice, Integer>{
	//returns all choices found
	public List<Choice> findAll();
	
	public List<Choice> findChoiceById(int choiceId);
}
