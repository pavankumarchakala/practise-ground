package com.practise_ground.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IPractiseGroundWeekDAO;
import com.practise_ground.dto.PractiseGroundWeekDTO;
import com.practise_ground.entity.PractiseGroundWeekEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class PractiseGroundWeekServiceImpl implements IPractiseGroundWeekService {

	private final IPractiseGroundWeekDAO practiseGroundWeekDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<PractiseGroundWeekDTO> create(PractiseGroundWeekDTO practiseGroundWeekDTO) {

		PractiseGroundWeekEntity entity = modelMapper.map(practiseGroundWeekDTO, PractiseGroundWeekEntity.class);

		PractiseGroundWeekEntity savedEntity = practiseGroundWeekDAO.save(entity);

		practiseGroundWeekDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(practiseGroundWeekDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<PractiseGroundWeekDTO> update(PractiseGroundWeekDTO practiseGroundWeekDTO) {

		PractiseGroundWeekEntity entity = modelMapper.map(practiseGroundWeekDTO, PractiseGroundWeekEntity.class);

		entity.setStatus(Status.ACTIVE);

		practiseGroundWeekDAO.save(entity);

		return ResponseEntity.ok(practiseGroundWeekDTO);
	}

	@Override
	public ResponseEntity<PractiseGroundWeekDTO> getById(long id) {

		PractiseGroundWeekEntity entity = practiseGroundWeekDAO.findById(id).orElseThrow(() -> PractiseGroundException
				.builder().message("No PractiseGroundWeek Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		PractiseGroundWeekDTO dto = modelMapper.map(entity, PractiseGroundWeekDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		PractiseGroundWeekEntity entity = practiseGroundWeekDAO.findById(id).orElseThrow(() -> PractiseGroundException
				.builder().message("No PractiseGroundWeek Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<PractiseGroundWeekDTO>> findAll() {

		return ResponseEntity.ok(practiseGroundWeekDAO.findAllByStatus(Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, PractiseGroundWeekDTO.class)).toList());

	}

}
