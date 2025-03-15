package com.practise_ground.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.UserQuizEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Mar 07, 2025
 *
 */
@Repository
public interface IUserQuizDAO extends JpaRepository<UserQuizEntity, Long> {

	List<UserQuizEntity> findByStatus(Status active);

	List<UserQuizEntity> findByUserIdAndStatus(long userId, Status active);

	List<UserQuizEntity> findByQuizIdAndStatus(long quizId, Status active);
}
