package com.practise_ground.dto;

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
public class QuestionTypeDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7879631757281564261L;

	private String name;

	private boolean isActive;

}
