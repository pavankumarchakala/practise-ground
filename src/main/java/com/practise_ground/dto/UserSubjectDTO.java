package com.practise_ground.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserSubjectDTO extends BaseDTO {

	private static final long serialVersionUID = 458839139006694296L;

	@NotNull(message = "Subject object shouldn't be null !!!")
	private SubjectDTO subject;

	@NotNull(message = "User object shouldn't be null !!!")
	private UserDTO user;

}
