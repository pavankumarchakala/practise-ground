package com.practise_ground.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.QuizEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface IQuizDAO extends JpaRepository<QuizEntity, Long> {

	List<QuizEntity> findByStatus(Status active);

	QuizEntity findByGradeIdAndSubjectIdAndWeekIdAndYearId(long gradeId, long subjectId, long weekId, long yearId);

	List<QuizEntity> findByGradeIdAndSubjectIdAndYearIdAndWeekEndDateBefore(long gradeId, long subjectId, long yearId,
			Date currDate);

	List<QuizEntity> findByGradeIdAndSubjectIdAndYearIdAndWeekStartDateBefore(long gradeId, long subjectId, long id,
			Date currDate);

}
