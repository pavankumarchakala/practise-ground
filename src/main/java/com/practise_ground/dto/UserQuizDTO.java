package com.practise_ground.dto;

import java.math.BigDecimal;

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
public class UserQuizDTO extends BaseDTO {

	private static final long serialVersionUID = -1182112312887632315L;

	private QuizDTO quiz;

	private UserDTO user;

	private BigDecimal score;

	private String quizQA;

}
