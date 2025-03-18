package com.practise_ground.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IUserSubjectDAO;
import com.practise_ground.dto.UserSubjectDTO;
import com.practise_ground.entity.UserSubjectEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 07, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class UserSubjectServiceImpl implements IUserSubjectService {

	private final IUserSubjectDAO userSubjectDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<UserSubjectDTO> create(UserSubjectDTO userSubjectDTO) {

		UserSubjectEntity userSubjectEntity = userSubjectDAO.findByUserIdAndSubjectId(userSubjectDTO.getUser().getId(),
				userSubjectDTO.getSubject().getId());

		if (!ObjectUtils.isEmpty(userSubjectEntity))
			return ResponseEntity.ok(modelMapper.map(userSubjectEntity, UserSubjectDTO.class));

		UserSubjectEntity entity = modelMapper.map(userSubjectDTO, UserSubjectEntity.class);

		UserSubjectEntity savedEntity = userSubjectDAO.save(entity);

		userSubjectDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(userSubjectDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<UserSubjectDTO> update(UserSubjectDTO userSubjectDTO) {

		UserSubjectEntity entity = modelMapper.map(userSubjectDTO, UserSubjectEntity.class);

		entity.setStatus(Status.ACTIVE);

		userSubjectDAO.save(entity);

		return ResponseEntity.ok(userSubjectDTO);
	}

	@Override
	public ResponseEntity<UserSubjectDTO> getById(long id) {

		UserSubjectEntity entity = userSubjectDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Subject Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		UserSubjectDTO dto = modelMapper.map(entity, UserSubjectDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		UserSubjectEntity entity = userSubjectDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No User Subject Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<UserSubjectDTO>> findAllByUser(long userId) {

		return ResponseEntity.ok(userSubjectDAO.findByUserIdAndStatus(userId, Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, UserSubjectDTO.class)).toList());

	}

	@Override
	public ResponseEntity<List<UserSubjectDTO>> findAllBySubject(long subjectId) {

		return ResponseEntity.ok(userSubjectDAO.findBySubjectIdAndStatus(subjectId, Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, UserSubjectDTO.class)).toList());

	}

}
