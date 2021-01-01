package com.foodConsensus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.foodConsensus.model.Motion;
import com.foodConsensus.model.Suggestion;

@Transactional
public interface SuggestionDAO extends JpaRepository<Suggestion, Integer>{
	public List<Suggestion> findSuggestionBySuggestionId(int suggestionId);
	public List<Suggestion> findSuggestionByMotionid(Motion motion);
	
}
