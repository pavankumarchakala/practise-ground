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

import com.practise_ground.dto.UserQuizDTO;
import com.practise_ground.service.IUserQuizService;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 07, 2025
 *
 */
@RestController
@RequestMapping("/userquiz")
@AllArgsConstructor
public class UserQuizController {

	private final IUserQuizService userQuizService;

	@PostMapping("/create")
	public ResponseEntity<UserQuizDTO> create(@Valid @RequestBody UserQuizDTO userQuizDTO) {
		return userQuizService.create(userQuizDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<UserQuizDTO> update(@Valid @RequestBody UserQuizDTO userQuizDTO) {
		return userQuizService.update(userQuizDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserQuizDTO> update(@Valid @Positive @PathVariable long id) {
		return userQuizService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@Valid @Positive @PathVariable long id) {
		return userQuizService.delete(id);
	}

	@GetMapping("/allbyuser/{userId}")
	@Hidden
	public ResponseEntity<List<UserQuizDTO>> findAllByUser(@PathVariable long userId) {
		return userQuizService.findAllByUser(userId);
	}

	@GetMapping("/allbyquiz/{quizId}")
	@Hidden
	public ResponseEntity<List<UserQuizDTO>> findAllByQuiz(@PathVariable long quizId) {
		return userQuizService.findAllByQuiz(quizId);
	}

}
