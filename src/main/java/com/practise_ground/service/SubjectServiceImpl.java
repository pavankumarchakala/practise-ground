package com.practise_ground.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.ISubjectDAO;
import com.practise_ground.dto.SubjectDTO;
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
public class SubjectServiceImpl implements ISubjectService {

	private final ISubjectDAO subjectDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<SubjectDTO> create(SubjectDTO subjectDTO) {

		SubjectEntity entity = modelMapper.map(subjectDTO, SubjectEntity.class);

		SubjectEntity savedEntity = subjectDAO.save(entity);

		subjectDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(subjectDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<SubjectDTO> update(SubjectDTO subjectDTO) {

		SubjectEntity entity = modelMapper.map(subjectDTO, SubjectEntity.class);

		entity.setStatus(Status.ACTIVE);

		subjectDAO.save(entity);

		return ResponseEntity.ok(subjectDTO);
	}

	@Override
	public ResponseEntity<SubjectDTO> getById(long id) {

		SubjectEntity entity = subjectDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Subject Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		SubjectDTO dto = modelMapper.map(entity, SubjectDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<SubjectDTO> findDefaultSubject() {

		SubjectEntity entity = subjectDAO.findByIsDefault(true).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Default Subject Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		SubjectDTO dto = modelMapper.map(entity, SubjectDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		SubjectEntity entity = subjectDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Subject Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<SubjectDTO>> findAll() {

		return ResponseEntity.ok(subjectDAO.findByStatus(Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, SubjectDTO.class)).toList());

	}

}
