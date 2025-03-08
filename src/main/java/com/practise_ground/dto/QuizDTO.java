package com.practise_ground.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class QuizDTO extends BaseDTO {

	private static final long serialVersionUID = -3489853431859785393L;

	@NotEmpty(message = "Quiz name must not be empty !!!")
	private String name;

	@NotNull(message = "Subject object shouldn't be null !!!")
	private SubjectDTO subject;

	@NotNull(message = "Grade object shouldn't be null !!!")
	private GradeDTO grade;

	@NotNull(message = "Week object shouldn't be null !!!")
	private PractiseGroundWeekDTO week;

	@NotNull(message = "Year object shouldn't be null !!!")
	private PractiseGroundYearDTO year;

}
