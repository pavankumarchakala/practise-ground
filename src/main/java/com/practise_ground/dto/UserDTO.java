package com.practise_ground.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.practise_ground.enums.Gender;
import com.practise_ground.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 14, 2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserDTO extends BaseDTO {

	private static final long serialVersionUID = 3673432348254489323L;

	private String fullName;

	private List<GradeDTO> grades;

	private Gender gender;

	private UserRole role;

	private LocalDateTime dateOfBirth;

	@Email(message = "Please enter a valid Email Address !!!")
	@NotEmpty(message = "Email Address must not be empty !!!")
	private String email;

	private boolean isEmailVerified;

	private String address;

	private String phone;

	private String school;

	private String postalCode;

	private List<SubjectDTO> subjects;

}
