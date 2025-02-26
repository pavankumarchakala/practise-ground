package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.PractiseGroundWeekDTO;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
public interface IPractiseGroundWeekService {

	ResponseEntity<PractiseGroundWeekDTO> create(PractiseGroundWeekDTO practiseGroundWeek);

	ResponseEntity<PractiseGroundWeekDTO> update(PractiseGroundWeekDTO practiseGroundWeek);

	ResponseEntity<PractiseGroundWeekDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<PractiseGroundWeekDTO>> findAll();

}
