package com.practise_ground.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise_ground.dto.GradeSubjectDTO;
import com.practise_ground.service.IGradeSubjectService;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
@RestController
@RequestMapping("/gradesubject")
@AllArgsConstructor
public class GradeSubjectController {

	private final IGradeSubjectService gradeSubjectService;

	@PostMapping("/create")
	public ResponseEntity<GradeSubjectDTO> create(@RequestBody GradeSubjectDTO gradeSubject) {
		return gradeSubjectService.create(gradeSubject);
	}

	@PutMapping("/update")
	public ResponseEntity<GradeSubjectDTO> update(@RequestBody GradeSubjectDTO gradeSubject) {
		return gradeSubjectService.update(gradeSubject);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GradeSubjectDTO> update(@PathVariable long id) {
		return gradeSubjectService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return gradeSubjectService.delete(id);
	}

//	@GetMapping("/all")
//	public ResponseEntity<List<GradeSubjectDTO>> findAll() {
//		return gradeSubjectService.findAll();
//	}

}
