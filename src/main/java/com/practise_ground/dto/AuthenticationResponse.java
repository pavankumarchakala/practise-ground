package com.practise_ground.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 14, 2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -6478326314567303801L;

	private String message;

	private String type;

}
