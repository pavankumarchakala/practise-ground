package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.QuestionDTO;
import com.practise_ground.dto.QuestionnaireDTO;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
public interface IQuestionnaireService {

	ResponseEntity<QuestionnaireDTO> create(QuestionnaireDTO questionnaire);

	ResponseEntity<QuestionnaireDTO> update(QuestionnaireDTO questionnaire);

	ResponseEntity<QuestionnaireDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<QuestionDTO>> findAllByQuizId(long quizId);

}
