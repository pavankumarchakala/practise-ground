package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.PractiseGroundYearDTO;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
public interface IPractiseGroundYearService {

	ResponseEntity<PractiseGroundYearDTO> create(PractiseGroundYearDTO practiseGroundYear);

	ResponseEntity<PractiseGroundYearDTO> update(PractiseGroundYearDTO practiseGroundYear);

	ResponseEntity<PractiseGroundYearDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<PractiseGroundYearDTO>> findAll();

}
