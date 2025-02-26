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
public class QuizDTO extends BaseDTO {

	private static final long serialVersionUID = -3489853431859785393L;

	private String name;

	private SubjectDTO subject;

	private GradeDTO grade;

	private PractiseGroundWeekDTO week;

	private PractiseGroundYearDTO year;

}
