package com.practise_ground.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IUserQuizDAO;
import com.practise_ground.dto.UserQuizDTO;
import com.practise_ground.entity.UserQuizEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 07, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class UserQuizServiceImpl implements IUserQuizService {

	private final IUserQuizDAO userQuizDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<UserQuizDTO> create(UserQuizDTO userQuizDTO) {

		UserQuizEntity entity = modelMapper.map(userQuizDTO, UserQuizEntity.class);

		UserQuizEntity savedEntity = userQuizDAO.save(entity);

		userQuizDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(userQuizDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<UserQuizDTO> update(UserQuizDTO userQuizDTO) {

		UserQuizEntity entity = modelMapper.map(userQuizDTO, UserQuizEntity.class);

		entity.setStatus(Status.ACTIVE);

		userQuizDAO.save(entity);

		return ResponseEntity.ok(userQuizDTO);
	}

	@Override
	public ResponseEntity<UserQuizDTO> getById(long id) {

		UserQuizEntity entity = userQuizDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Subject Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		UserQuizDTO dto = modelMapper.map(entity, UserQuizDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		UserQuizEntity entity = userQuizDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Quiz Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<UserQuizDTO>> findAllByUser(long userId) {

		return ResponseEntity.ok(userQuizDAO.findAllByUserIdAndStatus(userId, Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, UserQuizDTO.class)).toList());

	}

	@Override
	public ResponseEntity<List<UserQuizDTO>> findAllByQuiz(long quizId) {

		return ResponseEntity.ok(userQuizDAO.findAllByQuizIdAndStatus(quizId, Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, UserQuizDTO.class)).toList());

	}

}
