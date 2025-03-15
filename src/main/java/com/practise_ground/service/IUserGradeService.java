package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.UserGradeDTO;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
public interface IUserGradeService {

	ResponseEntity<UserGradeDTO> create(UserGradeDTO user);

	ResponseEntity<UserGradeDTO> update(UserGradeDTO user);

	ResponseEntity<UserGradeDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<UserGradeDTO>> findAllByUser(long userId);

	ResponseEntity<List<UserGradeDTO>> findAllByGrade(long subjectId);

}
