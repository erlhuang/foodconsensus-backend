package com.foodConsensus.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodConsensus.model.Motion;

@Transactional
public interface MotionDAO extends JpaRepository<Motion, Integer>{

}
