package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.UserSubjectDTO;

/**
 * @author Pavankumar - created date : Mar 07, 2025
 *
 */
public interface IUserSubjectService {

	ResponseEntity<UserSubjectDTO> create(UserSubjectDTO user);

	ResponseEntity<UserSubjectDTO> update(UserSubjectDTO user);

	ResponseEntity<UserSubjectDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<UserSubjectDTO>> findAllByUser(long userId);

	ResponseEntity<List<UserSubjectDTO>> findAllBySubject(long subjectId);

}
