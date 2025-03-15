package com.practise_ground.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practise_ground.dao.IPractiseGroundYearDAO;
import com.practise_ground.dto.PractiseGroundYearDTO;
import com.practise_ground.entity.PractiseGroundYearEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class PractiseGroundYearServiceImpl implements IPractiseGroundYearService {

	private final IPractiseGroundYearDAO practiseGroundYearDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<PractiseGroundYearDTO> create(PractiseGroundYearDTO practiseGroundYearDTO) {

		PractiseGroundYearEntity entity = modelMapper.map(practiseGroundYearDTO, PractiseGroundYearEntity.class);

		PractiseGroundYearEntity savedEntity = practiseGroundYearDAO.save(entity);

		practiseGroundYearDTO.setId(savedEntity.getId());

		return ResponseEntity.ok(practiseGroundYearDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<PractiseGroundYearDTO> update(PractiseGroundYearDTO practiseGroundYearDTO) {

		PractiseGroundYearEntity entity = modelMapper.map(practiseGroundYearDTO, PractiseGroundYearEntity.class);

		entity.setStatus(Status.ACTIVE);

		practiseGroundYearDAO.save(entity);

		return ResponseEntity.ok(practiseGroundYearDTO);
	}

	@Override
	public ResponseEntity<PractiseGroundYearDTO> getById(long id) {

		PractiseGroundYearEntity entity = practiseGroundYearDAO.findById(id).orElseThrow(() -> PractiseGroundException
				.builder().message("No PractiseGroundYear Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		PractiseGroundYearDTO dto = modelMapper.map(entity, PractiseGroundYearDTO.class);

		return ResponseEntity.ok(dto);
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> delete(long id) {

		PractiseGroundYearEntity entity = practiseGroundYearDAO.findById(id).orElseThrow(() -> PractiseGroundException
				.builder().message("No PractiseGroundYear Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		entity.setStatus(Status.INACTIVE);

		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<PractiseGroundYearDTO>> findAll() {

		return ResponseEntity.ok(practiseGroundYearDAO.findByStatus(Status.ACTIVE).parallelStream()
				.map(entity -> modelMapper.map(entity, PractiseGroundYearDTO.class)).toList());

	}

}
