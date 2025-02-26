package com.practise_ground.dto;

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

	private String question;

	private String optionA;

	private String optionB;

	private String optionC;

	private String optionD;

	private String answer;

	private QuizDTO quiz;

}
