package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.GradeSubjectDTO;
import com.practise_ground.dto.SubjectDTO;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
public interface IGradeSubjectService {

	ResponseEntity<GradeSubjectDTO> create(GradeSubjectDTO grade);

	ResponseEntity<GradeSubjectDTO> update(GradeSubjectDTO grade);

	ResponseEntity<GradeSubjectDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<SubjectDTO>> findAllSubjectsByGrade(long gradeId);

}
