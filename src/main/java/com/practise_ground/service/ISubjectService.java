package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.SubjectDTO;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
public interface ISubjectService {

	ResponseEntity<SubjectDTO> create(SubjectDTO subject);

	ResponseEntity<SubjectDTO> update(SubjectDTO subject);

	ResponseEntity<SubjectDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<SubjectDTO>> findAll();

	ResponseEntity<SubjectDTO> findDefaultSubject();

}
