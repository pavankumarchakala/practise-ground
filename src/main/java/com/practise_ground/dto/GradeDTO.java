package com.practise_ground.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class GradeDTO extends BaseDTO {

	private static final long serialVersionUID = -4835737911993999581L;

	@NotEmpty(message = "Grade name must not be empty !!!")
	private String name;

}
