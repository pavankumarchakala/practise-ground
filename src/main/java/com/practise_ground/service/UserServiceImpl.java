package com.practise_ground.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IUserDAO;
import com.practise_ground.dto.UserDTO;
import com.practise_ground.entity.UserEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.enums.UserRole;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<UserDTO> create(UserDTO userDTO) {

		UserEntity entity = modelMapper.map(userDTO, UserEntity.class);

		UserEntity savedEntity = userDAO.save(entity);

		userDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(userDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<UserDTO> update(UserDTO userDTO) {

		UserEntity entity = modelMapper.map(userDTO, UserEntity.class);

		userDAO.save(entity);

		return ResponseEntity.ok(userDTO);
	}

	@Override
	public ResponseEntity<UserDTO> getById(long id) {

		UserEntity entity = userDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No User Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		UserDTO dto = modelMapper.map(entity, UserDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		UserEntity entity = userDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No User Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<UserDTO>> findAll() {

		return ResponseEntity
				.ok(userDAO.findAll().parallelStream().map(entity -> modelMapper.map(entity, UserDTO.class)).toList());

	}

	@Override
	public ResponseEntity<List<UserDTO>> findAllByRole(UserRole role) {

		return ResponseEntity.ok(userDAO.findAllByRole(role).parallelStream()
				.map(entity -> modelMapper.map(entity, UserDTO.class)).toList());

	}

}
