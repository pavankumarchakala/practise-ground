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

import com.practise_ground.dto.UserSubjectDTO;
import com.practise_ground.service.IUserSubjectService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 07, 2025
 *
 */
@RestController
@RequestMapping("/usersubject")
@AllArgsConstructor
public class UserSubjectController {

	private final IUserSubjectService userSubjectService;

	@PostMapping("/create")
	public ResponseEntity<UserSubjectDTO> create(@Valid @RequestBody UserSubjectDTO userSubjectDTO) {
		return userSubjectService.create(userSubjectDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<UserSubjectDTO> update(@Valid @RequestBody UserSubjectDTO userSubjectDTO) {
		return userSubjectService.update(userSubjectDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserSubjectDTO> findById(@Valid @Positive @PathVariable long id) {
		return userSubjectService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@Valid @Positive @PathVariable long id) {
		return userSubjectService.delete(id);
	}

	@GetMapping("/allbyuser/{userId}")
	public ResponseEntity<List<UserSubjectDTO>> findAllByUser(@PathVariable long userId) {
		return userSubjectService.findAllByUser(userId);
	}

	@GetMapping("/allbysubject/{subjectId}")
	public ResponseEntity<List<UserSubjectDTO>> findAllBySubject(@PathVariable long subjectId) {
		return userSubjectService.findAllBySubject(subjectId);
	}

}
