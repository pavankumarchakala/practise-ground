package com.practise_ground.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.QuestionnaireEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface IQuestionnaireDAO extends JpaRepository<QuestionnaireEntity, Long> {

	List<QuestionnaireEntity> findByStatus(Status active);

	List<QuestionnaireEntity> findByQuizIdAndStatus(long quizId, Status active);

}
