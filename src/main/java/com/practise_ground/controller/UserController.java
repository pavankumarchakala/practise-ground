package com.practise_ground.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practise_ground.dto.UserDTO;
import com.practise_ground.enums.UserRole;
import com.practise_ground.service.IUserService;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 14, 2025
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

	private final IUserService userService;

	@GetMapping("/")
	@Hidden
	public String defaultMapping() {
		return "Welcome !!";
	}

	@GetMapping("/loggedInUser")
	@Hidden
	public ResponseEntity<Principal> loggedInUserInfo(Principal principal) {
		return ResponseEntity.ok(principal);
	}

	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO user) {
		user.setRole(UserRole.USER);
		return userService.create(user);
	}

	@PutMapping("/update")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO user) {
		user.setRole(UserRole.USER);
		return userService.update(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@Valid @Positive @PathVariable long id) {
		return userService.getById(id);
	}

	@GetMapping
	public ResponseEntity<UserDTO> findByEmail(@Valid @Email @RequestParam String email) {
		return userService.getByEmail(email);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@Valid @Positive @PathVariable long id) {
		return userService.delete(id);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> findAll() {
		return userService.findAll();
	}

	@GetMapping("/allByRole/{role}")
	public ResponseEntity<List<UserDTO>> findAllByRole(@Valid @NotBlank @PathVariable String role) {
		return userService.findAllByRole(UserRole.getValueOf(role));
	}

	@GetMapping("/verifyUser/{id}")
	public ResponseEntity<Boolean> verifyUserById(@Valid @Positive @PathVariable long id) {
		return userService.verifyUserById(id);
	}

//	@GetMapping("/verifyUser")
//	public ResponseEntity<UserDTO> verifyUserById(@Valid @Email @RequestParam String email) {
//		return userService.verifyUserByEmail(email);
//	}
//
//	@GetMapping("/verifyUserByEmail/{email:.+}")
//	public ResponseEntity<UserDTO> verifyUserByEmail(@Valid @Email @PathVariable String email) {
//		return userService.verifyUserByEmail(email);
//	}
}
