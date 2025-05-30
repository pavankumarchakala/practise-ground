package com.practise_ground.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IGradeSubjectDAO;
import com.practise_ground.dto.GradeSubjectDTO;
import com.practise_ground.dto.SubjectDTO;
import com.practise_ground.entity.GradeSubjectEntity;
import com.practise_ground.entity.SubjectEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class GradeSubjectServiceImpl implements IGradeSubjectService {

	private final IGradeSubjectDAO gradeSubjectDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<GradeSubjectDTO> create(GradeSubjectDTO gradeSubjectDTO) {

		GradeSubjectEntity gradeSubjectEntity = gradeSubjectDAO
				.findByGradeIdAndSubjectId(gradeSubjectDTO.getGrade().getId(), gradeSubjectDTO.getSubject().getId());

		if (!ObjectUtils.isEmpty(gradeSubjectEntity))
			return ResponseEntity.ok(modelMapper.map(gradeSubjectEntity, GradeSubjectDTO.class));

		GradeSubjectEntity entity = modelMapper.map(gradeSubjectDTO, GradeSubjectEntity.class);

		GradeSubjectEntity savedGradeSubjectEntity = gradeSubjectDAO.save(entity);

		gradeSubjectDTO.setId(savedGradeSubjectEntity.getId());

		return ResponseEntity.ok(gradeSubjectDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<GradeSubjectDTO> update(GradeSubjectDTO gradeSubjectDTO) {

		GradeSubjectEntity entity = modelMapper.map(gradeSubjectDTO, GradeSubjectEntity.class);

		entity.setStatus(Status.ACTIVE);

		gradeSubjectDAO.save(entity);

		return ResponseEntity.ok(gradeSubjectDTO);
	}

	@Override
	public ResponseEntity<GradeSubjectDTO> getById(long id) {

		GradeSubjectEntity entity = gradeSubjectDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Grade Subject Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		GradeSubjectDTO dto = modelMapper.map(entity, GradeSubjectDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		GradeSubjectEntity entity = gradeSubjectDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Grade Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<SubjectDTO>> findAllSubjectsByGrade(long gradeId) {
		List<GradeSubjectEntity> gradeSubjectEntities = gradeSubjectDAO.findAllByGradeId(gradeId);

		List<SubjectDTO> subjects = gradeSubjectEntities.parallelStream().map(entity -> {

			SubjectEntity subject = entity.getSubject();

			SubjectDTO subjectDTO = new SubjectDTO();
			subjectDTO.setId(subject.getId());
			subjectDTO.setName(subject.getName());
			subjectDTO.setDefault(subject.isDefault());

			return subjectDTO;

		}).toList();

		return ResponseEntity.ok(subjects);

	}
}
