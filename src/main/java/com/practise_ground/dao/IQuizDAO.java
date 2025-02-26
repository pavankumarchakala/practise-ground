package com.practise_ground.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.QuizEntity;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface IQuizDAO extends JpaRepository<QuizEntity, Long> {

}
