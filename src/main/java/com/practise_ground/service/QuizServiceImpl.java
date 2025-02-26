package com.practise_ground.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IQuizDAO;
import com.practise_ground.dto.QuizDTO;
import com.practise_ground.entity.QuizEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Service
@AllArgsConstructor
public class QuizServiceImpl implements IQuizService {

	private final IQuizDAO quizDAO;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<QuizDTO> create(QuizDTO quizDTO) {

		QuizEntity entity = modelMapper.map(quizDTO, QuizEntity.class);

		QuizEntity savedEntity = quizDAO.save(entity);

		quizDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(quizDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<QuizDTO> update(QuizDTO quizDTO) {

		QuizEntity entity = modelMapper.map(quizDTO, QuizEntity.class);

		quizDAO.save(entity);

		return ResponseEntity.ok(quizDTO);
	}

	@Override
	public ResponseEntity<QuizDTO> getById(long id) {

		QuizEntity entity = quizDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Quiz Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		QuizDTO dto = modelMapper.map(entity, QuizDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		QuizEntity entity = quizDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Quiz Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<QuizDTO>> findAll() {

		return ResponseEntity
				.ok(quizDAO.findAll().parallelStream().map(entity -> modelMapper.map(entity, QuizDTO.class)).toList());

	}

}
