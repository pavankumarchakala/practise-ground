package com.practise_ground.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IQuestionnaireDAO;
import com.practise_ground.dto.QuestionnaireDTO;
import com.practise_ground.entity.QuestionnaireEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements IQuestionnaireService {

	private final IQuestionnaireDAO questionnaireDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<QuestionnaireDTO> create(QuestionnaireDTO questionnaireDTO) {

		QuestionnaireEntity entity = modelMapper.map(questionnaireDTO, QuestionnaireEntity.class);

		QuestionnaireEntity savedEntity = questionnaireDAO.save(entity);

		questionnaireDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(questionnaireDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<QuestionnaireDTO> update(QuestionnaireDTO questionnaireDTO) {

		QuestionnaireEntity entity = modelMapper.map(questionnaireDTO, QuestionnaireEntity.class);

		entity.setStatus(Status.ACTIVE);

		questionnaireDAO.save(entity);

		return ResponseEntity.ok(questionnaireDTO);
	}

	@Override
	public ResponseEntity<QuestionnaireDTO> getById(long id) {

		QuestionnaireEntity entity = questionnaireDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Questionnaire Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		QuestionnaireDTO dto = modelMapper.map(entity, QuestionnaireDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		QuestionnaireEntity entity = questionnaireDAO.findById(id).orElseThrow(() -> PractiseGroundException.builder()
				.message("No Questionnaire Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<QuestionnaireDTO>> findAllByQuizId(long quizId) {

		return ResponseEntity.ok(questionnaireDAO.findByQuizIdAndStatus(quizId, Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, QuestionnaireDTO.class)).toList());

	}

}
