package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.UserQuizDTO;

/**
 * @author Pavankumar - created date : Mar 07, 2025
 *
 */
public interface IUserQuizService {

	ResponseEntity<UserQuizDTO> create(UserQuizDTO user);

	ResponseEntity<UserQuizDTO> update(UserQuizDTO user);

	ResponseEntity<UserQuizDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<UserQuizDTO>> findAllByUser(long userId);

	ResponseEntity<List<UserQuizDTO>> findAllByQuiz(long quizId);

	ResponseEntity<List<UserQuizDTO>> findAllUserQuizByUserSubjectGradeCurrentDate(long subjectId, long gradeId,
			long userId);

}
