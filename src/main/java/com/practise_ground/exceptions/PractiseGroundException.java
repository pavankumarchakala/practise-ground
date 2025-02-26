package com.practise_ground.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Pavankumar - created date : Feb 14, 2025
 */
@Setter
@Getter
public class PractiseGroundException extends RuntimeException {

	private static final long serialVersionUID = 3849807106163417698L;

	private HttpStatus httpStatus;

	private String message;

	public PractiseGroundException() {
		super();
	}

	public PractiseGroundException(Throwable t) {
		super(t);
	}

	public PractiseGroundException(String message) {
		super(message);
		this.message = message;
	}

	public PractiseGroundException(String message, HttpStatus status) {
		super(message);
		this.message = message;
		this.httpStatus = status;
	}

	public PractiseGroundException(String message, Throwable t) {
		super(message, t);
		this.message = message;
	}

}
