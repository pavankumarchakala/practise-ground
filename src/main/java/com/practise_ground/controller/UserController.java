package com.practise_ground.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

	@GetMapping("/")
	public String defaultMapping() {
		return "Welcome !!";
	}

	@GetMapping("/user")
	public Principal loggedInUserInfo(Principal principal) {
		return principal;
	}
}
