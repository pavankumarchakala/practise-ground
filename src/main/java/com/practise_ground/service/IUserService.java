package com.practise_ground.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practise_ground.dto.UserDTO;
import com.practise_ground.enums.UserRole;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
public interface IUserService {

	ResponseEntity<UserDTO> create(UserDTO user);

	ResponseEntity<UserDTO> update(UserDTO user);

	ResponseEntity<UserDTO> getById(long id);

	ResponseEntity<Boolean> delete(long id);

	ResponseEntity<List<UserDTO>> findAll();

	ResponseEntity<List<UserDTO>> findAllByRole(UserRole role);

}
