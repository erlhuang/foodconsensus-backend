package com.foodConsensus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodConsensus.dao.MotionDAO;
import com.foodConsensus.dto.MotionDTO;
import com.foodConsensus.model.Motion;

@Service
public class MotionService {
	@Autowired
	private MotionDAO motionDao;
	
	public List<Motion> getMotions() {
		return motionDao.findAll();
	}
	
	public Motion addMotion(MotionDTO dto) {
		
		Motion motion = new Motion();
		return motion;
	}
}
