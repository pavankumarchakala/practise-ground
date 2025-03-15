package com.practise_ground.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserGradeDTO extends BaseDTO {

	private static final long serialVersionUID = -4628076064000437938L;

	@NotNull(message = "Grade object shouldn't be null !!!")
	private GradeDTO grade;

	@NotNull(message = "User object shouldn't be null !!!")
	private UserDTO user;
}
