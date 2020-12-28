package com.foodConsensus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.foodConsensus.model.MotionUser;

@Transactional
public interface MotionUserDAO extends JpaRepository<MotionUser, Integer> {

}
