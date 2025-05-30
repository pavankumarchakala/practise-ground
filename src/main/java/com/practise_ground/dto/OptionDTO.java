package com.practise_ground.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class OptionDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3261896336885258798L;

	private String name;

	@JsonProperty("isAnswer")
	private boolean isAnswer;

}
