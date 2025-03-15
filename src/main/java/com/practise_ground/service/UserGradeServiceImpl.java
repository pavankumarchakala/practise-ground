package com.practise_ground.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IUserGradeDAO;
import com.practise_ground.dto.UserGradeDTO;
import com.practise_ground.entity.UserGradeEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class UserGradeServiceImpl implements IUserGradeService {

	private final IUserGradeDAO userGradeDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<UserGradeDTO> create(UserGradeDTO userGradeDTO) {

		UserGradeEntity entity = modelMapper.map(userGradeDTO, UserGradeEntity.class);

		UserGradeEntity savedEntity = userGradeDAO.save(entity);

		userGradeDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(userGradeDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<UserGradeDTO> update(UserGradeDTO userGradeDTO) {

		UserGradeEntity entity = modelMapper.map(userGradeDTO, UserGradeEntity.class);

		entity.setStatus(Status.ACTIVE);

		userGradeDAO.save(entity);

		return ResponseEntity.ok(userGradeDTO);
	}

	@Override
	public ResponseEntity<UserGradeDTO> getById(long id) {

		UserGradeEntity entity = userGradeDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Grade Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		UserGradeDTO dto = modelMapper.map(entity, UserGradeDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		UserGradeEntity entity = userGradeDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No User Grade Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<UserGradeDTO>> findAllByUser(long userId) {

		return ResponseEntity.ok(userGradeDAO.findByUserIdAndStatus(userId, Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, UserGradeDTO.class)).toList());

	}

	@Override
	public ResponseEntity<List<UserGradeDTO>> findAllByGrade(long gradeId) {

		return ResponseEntity.ok(userGradeDAO.findByGradeIdAndStatus(gradeId, Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, UserGradeDTO.class)).toList());

	}

}
