package com.practise_ground.dto;

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
public class GradeSubjectDTO extends BaseDTO {

	private static final long serialVersionUID = -8294662822571825782L;

	private GradeDTO grade;

	private SubjectDTO subject;

}
