package com.foodConsensus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.foodConsensus.model.Motion;
import com.foodConsensus.model.MotionUser;
import com.foodConsensus.model.User;

@Transactional
public interface MotionUserDAO extends JpaRepository<MotionUser, Integer> {
	List<MotionUser> findByMotion(Motion motion);
	List<MotionUser> findByUserid(User user);
}
