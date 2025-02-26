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

import com.practise_ground.dto.PractiseGroundYearDTO;
import com.practise_ground.service.IPractiseGroundYearService;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@RestController
@RequestMapping("/year")
@AllArgsConstructor
public class PractiseGroundYearController {

	private final IPractiseGroundYearService practiseGroundYearService;

	@PostMapping("/create")
	public ResponseEntity<PractiseGroundYearDTO> create(@RequestBody PractiseGroundYearDTO practiseGroundYear) {
		return practiseGroundYearService.create(practiseGroundYear);
	}

	@PutMapping("/update")
	public ResponseEntity<PractiseGroundYearDTO> update(@RequestBody PractiseGroundYearDTO practiseGroundYear) {
		return practiseGroundYearService.update(practiseGroundYear);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PractiseGroundYearDTO> update(@PathVariable long id) {
		return practiseGroundYearService.getById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return practiseGroundYearService.delete(id);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PractiseGroundYearDTO>> findAll() {
		return practiseGroundYearService.findAll();
	}

}
