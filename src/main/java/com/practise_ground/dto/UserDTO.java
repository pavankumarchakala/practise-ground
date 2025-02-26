package com.practise_ground.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.practise_ground.enums.Gender;

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

	private GradeDTO grade;

	private Gender gender;

	private LocalDateTime dateOfBirth;

	private String email;

	private boolean isEmailVerified;

	private String address;

	private String phone;

	private String school;

	private String postalCode;

	private List<SubjectDTO> subjects;

}
