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
public class SubjectDTO extends BaseDTO {

	private static final long serialVersionUID = -1860278642432953015L;

	@NotEmpty(message = "Subject name must not be empty !!!")
	private String name;

}
