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

import com.practise_ground.dto.QuizDTO;
import com.practise_ground.service.IQuizService;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@RestController
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {

	private final IQuizService quizService;

	@PostMapping("/create")
	public ResponseEntity<QuizDTO> create(@RequestBody QuizDTO quiz) {
		return quizService.create(quiz);
	}

	@PutMapping("/update")
	public ResponseEntity<QuizDTO> update(@RequestBody QuizDTO quiz) {
		return quizService.update(quiz);
	}

	@GetMapping("/{id}")
	public ResponseEntity<QuizDTO> findById(@PathVariable long id) {
		return quizService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return quizService.delete(id);
	}

	@GetMapping("/all")
	public ResponseEntity<List<QuizDTO>> findAll() {
		return quizService.findAll();
	}

}
