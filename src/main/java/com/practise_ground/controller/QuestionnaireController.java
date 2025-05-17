package com.practise_ground.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise_ground.dto.QuestionDTO;
import com.practise_ground.dto.QuestionnaireDTO;
import com.practise_ground.service.IQuestionnaireService;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@RestController
@RequestMapping("/questionnaire")
@AllArgsConstructor
public class QuestionnaireController {

	private final IQuestionnaireService questionnaireService;

	@PostMapping("/create")
	public ResponseEntity<QuestionnaireDTO> create(@RequestBody QuestionnaireDTO questionnaire) {
		return questionnaireService.create(questionnaire);
	}

	@PutMapping("/update")
	public ResponseEntity<QuestionnaireDTO> update(@RequestBody QuestionnaireDTO questionnaire) {
		return questionnaireService.update(questionnaire);
	}

	@GetMapping("/{id}")
	public ResponseEntity<QuestionnaireDTO> findById(@PathVariable long id) {
		return questionnaireService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return questionnaireService.delete(id);
	}

	@GetMapping("/all/{quizId}")
	public ResponseEntity<List<QuestionDTO>> findAllByQuizId(@PathVariable long quizId) {
		return questionnaireService.findAllByQuizId(quizId);
	}

}
