package com.practise_ground.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.practise_ground.enums.Status;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 14, 2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO implements Serializable {

	private static final long serialVersionUID = -5047053970728864781L;

	private long id;

	@Hidden
	private LocalDateTime createdDate;

	@Hidden
	private LocalDateTime updatedDate;

	@Hidden
	private String createdBy;

	@Hidden
	private String updatedBy;

	@Hidden
	private Status status;

}
