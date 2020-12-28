package com.foodConsensus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.foodConsensus.model.MotionChoice;

@Transactional
public interface MotionChoiceDAO extends JpaRepository<MotionChoice, Integer> {
	public List<MotionChoice> findAll();
	
}
