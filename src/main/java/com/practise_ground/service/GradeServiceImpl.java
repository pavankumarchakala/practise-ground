package com.practise_ground.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IGradeDAO;
import com.practise_ground.dao.IGradeSubjectDAO;
import com.practise_ground.dao.ISubjectDAO;
import com.practise_ground.dto.GradeDTO;
import com.practise_ground.entity.GradeEntity;
import com.practise_ground.entity.GradeSubjectEntity;
import com.practise_ground.entity.SubjectEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements IGradeService {

	private final IGradeDAO gradeDAO;

	private final ISubjectDAO subjectDAO;

	private final IGradeSubjectDAO gradeSubjectDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<GradeDTO> create(GradeDTO gradeDTO) {

		GradeEntity gradeEntity = gradeDAO.findByName(gradeDTO.getName());

		if (!ObjectUtils.isEmpty(gradeEntity))
			return ResponseEntity.ok(modelMapper.map(gradeEntity, GradeDTO.class));

		GradeEntity entity = modelMapper.map(gradeDTO, GradeEntity.class);

		GradeEntity savedGradeEntity = gradeDAO.save(entity);

		SubjectEntity englishSubject = subjectDAO.findByName("English");

		if (ObjectUtils.isEmpty(englishSubject)) {

			englishSubject = subjectDAO.save(SubjectEntity.builder().name("English").isDefault(true).build());
		}

		gradeSubjectDAO.save(GradeSubjectEntity.builder().grade(savedGradeEntity).subject(englishSubject).build());

		gradeDTO.setId(savedGradeEntity.getId());

		return ResponseEntity.ok(gradeDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<GradeDTO> update(GradeDTO gradeDTO) {

		GradeEntity entity = modelMapper.map(gradeDTO, GradeEntity.class);

		entity.setStatus(Status.ACTIVE);

		gradeDAO.save(entity);

		return ResponseEntity.ok(gradeDTO);
	}

	@Override
	public ResponseEntity<GradeDTO> getById(long id) {

		GradeEntity entity = gradeDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Grade Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		GradeDTO dto = modelMapper.map(entity, GradeDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		GradeEntity entity = gradeDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Grade Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<GradeDTO>> findAll() {

		return ResponseEntity.ok(gradeDAO.findByStatus(Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, GradeDTO.class)).toList());

	}

}
