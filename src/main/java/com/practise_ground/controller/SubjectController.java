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

import com.practise_ground.dto.SubjectDTO;
import com.practise_ground.service.ISubjectService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@RestController
@RequestMapping("/subject")
@AllArgsConstructor
public class SubjectController {

	private final ISubjectService subjectService;

	@PostMapping("/create")
	public ResponseEntity<SubjectDTO> create(@Valid @RequestBody SubjectDTO subject) {
		return subjectService.create(subject);
	}

	@PutMapping("/update")
	public ResponseEntity<SubjectDTO> update(@Valid @RequestBody SubjectDTO subject) {
		return subjectService.update(subject);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubjectDTO> update(@Valid @Positive @PathVariable long id) {
		return subjectService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@Positive @PathVariable long id) {
		return subjectService.delete(id);
	}

	@GetMapping("/all")
	public ResponseEntity<List<SubjectDTO>> findAll() {
		return subjectService.findAll();
	}

}
