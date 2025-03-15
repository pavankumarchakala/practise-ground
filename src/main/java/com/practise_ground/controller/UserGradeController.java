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

import com.practise_ground.dto.UserGradeDTO;
import com.practise_ground.service.IUserGradeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
@RestController
@RequestMapping("/usergrade")
@AllArgsConstructor
public class UserGradeController {

	private final IUserGradeService userGradeService;

	@PostMapping("/create")
	public ResponseEntity<UserGradeDTO> create(@Valid @RequestBody UserGradeDTO userGradeDTO) {
		return userGradeService.create(userGradeDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<UserGradeDTO> update(@Valid @RequestBody UserGradeDTO userGradeDTO) {
		return userGradeService.update(userGradeDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserGradeDTO> findById(@Valid @Positive @PathVariable long id) {
		return userGradeService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@Valid @Positive @PathVariable long id) {
		return userGradeService.delete(id);
	}

	@GetMapping("/allbyuser/{userId}")
	public ResponseEntity<List<UserGradeDTO>> findAllByUser(@PathVariable long userId) {
		return userGradeService.findAllByUser(userId);
	}

	@GetMapping("/allbysubject/{subjectId}")
	public ResponseEntity<List<UserGradeDTO>> findAllByGrade(@PathVariable long subjectId) {
		return userGradeService.findAllByGrade(subjectId);
	}

}
