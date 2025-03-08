package com.practise_ground.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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
public class UserQuizDTO extends BaseDTO {

	private static final long serialVersionUID = -1182112312887632315L;

	@NotNull
	private QuizDTO quiz;

	@NotNull
	private UserDTO user;

	@NotNull(message = "Score shouldn't be null !!!")
	@DecimalMax(value = "100.00")
	@DecimalMin(value = "0.00")
	@Digits(integer = 3, fraction = 2)
	private BigDecimal score;

	private String quizQA;

}
