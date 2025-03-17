package com.practise_ground.interceptors;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import com.practise_ground.dto.ErrorResponse;
import com.practise_ground.exceptions.PractiseGroundException;

/**
 * @author Pavankumar - created date : Feb 14, 2025
 */
@RestControllerAdvice
public class GlobalExceptionInterceptor {

	@ExceptionHandler(PractiseGroundException.class)
	public ResponseEntity<ErrorResponse> practiseGroundExceptionHandlerMethod(PractiseGroundException e,
			WebRequest webRequest) {

		return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).status(e.getHttpStatus()).build(),
				e.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandlerMethod(Exception e, WebRequest webRequest) {

		return new ResponseEntity<>(
				ErrorResponse.builder().message(e.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> constraintViolationExceptionHandlerMethod() {
		return null;
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
	public ResponseEntity<ErrorResponse> sizeLimitExceededExceptionHandlerMethod(Exception e, WebRequest webRequest) {

		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().message(e.getMessage()).status(HttpStatus.PAYLOAD_TOO_LARGE).build(),
				HttpStatus.PAYLOAD_TOO_LARGE);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchExceptionMethod(Exception e, WebRequest webRequest) {

		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST).build(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionMethod(MethodArgumentNotValidException e,
			WebRequest webRequest) {

		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
