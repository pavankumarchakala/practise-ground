package com.practise_ground.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : May 17, 2025
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class QuestionDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -146025915188934778L;

	private String name;

	private List<OptionDTO> options;

	private QuestionTypeDTO questionType;

}
