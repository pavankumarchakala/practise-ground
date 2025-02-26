package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.QuizDTO;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
public interface IQuizService {

	ResponseEntity<QuizDTO> create(QuizDTO quiz);

	ResponseEntity<QuizDTO> update(QuizDTO quiz);

	ResponseEntity<QuizDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<QuizDTO>> findAll();

}
