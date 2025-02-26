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

import com.practise_ground.dto.GradeDTO;
import com.practise_ground.service.IGradeService;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@RestController
@RequestMapping("/grade")
@AllArgsConstructor
public class GradeController {

	private final IGradeService gradeService;

	@PostMapping("/create")
	public ResponseEntity<GradeDTO> create(@RequestBody GradeDTO grade) {
		return gradeService.create(grade);
	}

	@PutMapping("/update")
	public ResponseEntity<GradeDTO> update(@RequestBody GradeDTO grade) {
		return gradeService.update(grade);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GradeDTO> update(@PathVariable long id) {
		return gradeService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return gradeService.delete(id);
	}

	@GetMapping("/all")
	public ResponseEntity<List<GradeDTO>> findAll() {
		return gradeService.findAll();
	}

}
