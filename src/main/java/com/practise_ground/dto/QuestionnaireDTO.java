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
public class QuestionnaireDTO extends BaseDTO {

	private static final long serialVersionUID = 164240943267371374L;

	@NotEmpty(message = "Question must not be empty !!!")
	private String question;

	@NotEmpty(message = "Option-A must not be empty !!!")
	private String optionA;

	@NotEmpty(message = "Option-B must not be empty !!!")
	private String optionB;

	@NotEmpty(message = "Option-C must not be empty !!!")
	private String optionC;

	@NotEmpty(message = "Option-D must not be empty !!!")
	private String optionD;

	@NotEmpty(message = "Answer for the Question must not be empty !!!")
	private String answer;

	@NotNull(message = "Quiz object shouldn't be null !!!")
	private QuizDTO quiz;

}
