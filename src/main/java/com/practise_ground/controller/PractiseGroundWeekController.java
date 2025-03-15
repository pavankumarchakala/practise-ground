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

import com.practise_ground.dto.PractiseGroundWeekDTO;
import com.practise_ground.service.IPractiseGroundWeekService;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@RestController
@RequestMapping("/week")
@AllArgsConstructor
public class PractiseGroundWeekController {

	private final IPractiseGroundWeekService practiseGroundWeekService;

	@PostMapping("/create")
	public ResponseEntity<PractiseGroundWeekDTO> create(@RequestBody PractiseGroundWeekDTO practiseGroundWeek) {
		return practiseGroundWeekService.create(practiseGroundWeek);
	}

	@PutMapping("/update")
	public ResponseEntity<PractiseGroundWeekDTO> update(@RequestBody PractiseGroundWeekDTO practiseGroundWeek) {
		return practiseGroundWeekService.update(practiseGroundWeek);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PractiseGroundWeekDTO> findById(@PathVariable long id) {
		return practiseGroundWeekService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return practiseGroundWeekService.delete(id);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PractiseGroundWeekDTO>> findAll() {
		return practiseGroundWeekService.findAll();
	}

}
