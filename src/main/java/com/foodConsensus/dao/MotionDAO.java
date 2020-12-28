package com.foodConsensus.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodConsensus.model.Motion;
import com.foodConsensus.model.User;

@Transactional
public interface MotionDAO extends JpaRepository<Motion, Integer>{
	public List<Motion> findAll();
	
	public List<Motion> findMotionById(int motionId);
}
