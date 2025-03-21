package com.practise_ground.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.practise_ground.dao.IGradeDAO;
import com.practise_ground.dao.IGradeSubjectDAO;
import com.practise_ground.dao.ISubjectDAO;
import com.practise_ground.dao.IUserDAO;
import com.practise_ground.dao.IUserGradeDAO;
import com.practise_ground.dao.IUserSubjectDAO;
import com.practise_ground.dto.GradeDTO;
import com.practise_ground.dto.SubjectDTO;
import com.practise_ground.dto.UserDTO;
import com.practise_ground.entity.UserEntity;
import com.practise_ground.entity.UserGradeEntity;
import com.practise_ground.entity.UserSubjectEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.enums.UserRole;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO;

	private final ISubjectDAO subjectDAO;

	private final IGradeDAO gradeDAO;

	private final IUserGradeDAO userGradeDAO;

	private final IUserSubjectDAO userSubjectDAO;

	private final IGradeSubjectDAO gradeSubjectDAO;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ResponseEntity<UserDTO> create(UserDTO userDTO) {

		Optional<UserEntity> optionalUser = userDAO.findByEmailAndStatus(userDTO.getEmail(), Status.ACTIVE);

		if (optionalUser.isPresent()) {

			UserEntity userEntity = optionalUser.get();

			userDTO.setSubjects(userSubjectDAO.findByUserIdAndStatus(userEntity.getId(), Status.ACTIVE).parallelStream()
					.map(item -> modelMapper.map(item.getSubject(), SubjectDTO.class)).toList());

			userDTO.setGrades(userGradeDAO.findByUserIdAndStatus(userEntity.getId(), Status.ACTIVE).parallelStream()
					.map(item -> modelMapper.map(item.getGrade(), GradeDTO.class)).toList());

			return ResponseEntity.ok(userDTO);

		}

		UserEntity entity = modelMapper.map(userDTO, UserEntity.class);

		UserEntity savedEntity = userDAO.save(entity);

		long userId = savedEntity.getId();

		userDTO.setId(userId);

		userDTO.setSubjects(userSubjectDAO.findByUserIdAndStatus(userId, Status.ACTIVE).parallelStream()
				.map(item -> modelMapper.map(item.getSubject(), SubjectDTO.class)).toList());

		userDTO.setGrades(userGradeDAO.findByUserIdAndStatus(entity.getId(), Status.ACTIVE).parallelStream()
				.map(item -> modelMapper.map(item.getGrade(), GradeDTO.class)).toList());

		return ResponseEntity.ok(userDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<UserDTO> update(UserDTO userDTO) {

		UserEntity entity = modelMapper.map(userDTO, UserEntity.class);

		entity.setStatus(Status.ACTIVE);

		UserEntity savedEntity = userDAO.save(entity);

		userGradeDAO.deleteAllByUserId(userDTO.getId());

		userSubjectDAO.deleteAllByUserId(userDTO.getId());

		if (!CollectionUtils.isEmpty(userDTO.getGrades())) {

			userDTO.getGrades().parallelStream().forEach(item -> userGradeDAO.save(
					UserGradeEntity.builder().grade(gradeDAO.findById(item.getId()).get()).user(savedEntity).build()));

			userDTO.setGrades(userGradeDAO.findByUserIdAndStatus(userDTO.getId(), Status.ACTIVE).parallelStream()
					.map(item -> modelMapper.map(item.getGrade(), GradeDTO.class)).toList());

		}

		if (!CollectionUtils.isEmpty(userDTO.getSubjects())) {

			userDTO.getSubjects().parallelStream().forEach(item -> userSubjectDAO.save(UserSubjectEntity.builder()
					.subject(subjectDAO.findById(item.getId()).get()).user(savedEntity).build()));

			userDTO.setSubjects(userSubjectDAO.findByUserIdAndStatus(userDTO.getId(), Status.ACTIVE).parallelStream()
					.map(item -> modelMapper.map(item.getSubject(), SubjectDTO.class)).toList());
		} else if (!CollectionUtils.isEmpty(userDTO.getGrades())) {

			userDTO.getGrades().parallelStream().forEach(grade -> {

				gradeSubjectDAO.findAllByGradeIdAndSubjectIsDefault(grade.getId(), true).parallelStream()
						.map(gradeSubject -> gradeSubject.getSubject())
						.forEach(item -> userSubjectDAO.save(UserSubjectEntity.builder()
								.subject(subjectDAO.findById(item.getId()).get()).user(savedEntity).build()));

				userDTO.setSubjects(userSubjectDAO.findByUserIdAndStatus(userDTO.getId(), Status.ACTIVE)
						.parallelStream().map(item -> modelMapper.map(item.getSubject(), SubjectDTO.class)).toList());

			});

		}

		return ResponseEntity.ok(userDTO);
	}

	@Override
	public ResponseEntity<UserDTO> getById(long userId) {

		UserEntity entity = userDAO.findById(userId).orElseThrow(() -> PractiseGroundException.builder()
				.message("No User Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		UserDTO dto = modelMapper.map(entity, UserDTO.class);

		dto.setSubjects(userSubjectDAO.findByUserIdAndStatus(userId, Status.ACTIVE).parallelStream()
				.map(item -> modelMapper.map(item.getSubject(), SubjectDTO.class)).toList());

		dto.setGrades(userGradeDAO.findByUserIdAndStatus(entity.getId(), Status.ACTIVE).parallelStream()
				.map(item -> modelMapper.map(item.getGrade(), GradeDTO.class)).toList());

		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<UserDTO> getByEmail(String email) {

		UserEntity entity = userDAO.findByEmailAndStatus(email, Status.ACTIVE).orElseThrow(() -> PractiseGroundException
				.builder().message("No User Found !!").httpStatus(HttpStatus.NOT_FOUND).build());

		UserDTO dto = modelMapper.map(entity, UserDTO.class);

		dto.setGrades(userGradeDAO.findByUserIdAndStatus(entity.getId(), Status.ACTIVE).parallelStream()
				.map(item -> modelMapper.map(item.getGrade(), GradeDTO.class)).toList());

		dto.setSubjects(userSubjectDAO.findByUserIdAndStatus(entity.getId(), Status.ACTIVE).parallelStream()
				.map(item -> modelMapper.map(item.getSubject(), SubjectDTO.class)).toList());

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

		return ResponseEntity.ok(userDAO.findByStatus(Status.ACTIVE).parallelStream().map(entity -> {
			UserDTO dto = modelMapper.map(entity, UserDTO.class);
			dto.setSubjects(userSubjectDAO.findByUserIdAndStatus(dto.getId(), Status.ACTIVE).parallelStream()
					.map(item -> modelMapper.map(item.getSubject(), SubjectDTO.class)).toList());
			dto.setGrades(userGradeDAO.findByUserIdAndStatus(dto.getId(), Status.ACTIVE).parallelStream()
					.map(item -> modelMapper.map(item.getGrade(), GradeDTO.class)).toList());
			return dto;
		}).toList());

	}

	@Override
	public ResponseEntity<List<UserDTO>> findAllByRole(UserRole role) {

		return ResponseEntity.ok(userDAO.findByRoleAndStatus(role, Status.ACTIVE).parallelStream().map(entity -> {
			UserDTO dto = modelMapper.map(entity, UserDTO.class);
			dto.setSubjects(userSubjectDAO.findByUserIdAndStatus(dto.getId(), Status.ACTIVE).parallelStream()
					.map(item -> modelMapper.map(item.getSubject(), SubjectDTO.class)).toList());
			dto.setGrades(userGradeDAO.findByUserIdAndStatus(dto.getId(), Status.ACTIVE).parallelStream()
					.map(item -> modelMapper.map(item.getGrade(), GradeDTO.class)).toList());
			return dto;
		}).toList());

	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> verifyUserById(long userId) {

		userDAO.findById(userId).orElseThrow(() -> PractiseGroundException.builder().message("No User Found !!")
				.httpStatus(HttpStatus.NOT_FOUND).build()).setEmailVerified(true);

		return ResponseEntity.ok(true);
	}

//	@Override
//	@Transactional
//	public ResponseEntity<Boolean> verifyUserByEmail(String email) {
//
//		userDAO.findByEmail(email).orElseThrow(() -> PractiseGroundException.builder().message("No User Found !!")
//				.httpStatus(HttpStatus.NOT_FOUND).build()).setEmailVerified(true);
//
//		return ResponseEntity.ok(true);
//	}

}
