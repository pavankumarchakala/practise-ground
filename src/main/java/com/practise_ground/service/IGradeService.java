package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.GradeDTO;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
public interface IGradeService {

	ResponseEntity<GradeDTO> create(GradeDTO grade);

	ResponseEntity<GradeDTO> update(GradeDTO grade);

	ResponseEntity<GradeDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<GradeDTO>> findAll();

}
