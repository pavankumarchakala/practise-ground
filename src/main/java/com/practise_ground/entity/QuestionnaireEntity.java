package com.practise_ground.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "tb_questionnaire")
@DynamicInsert
@DynamicUpdate
public class QuestionnaireEntity extends BaseEntity {

	private static final long serialVersionUID = -2471981813138636532L;

	@Column(name = "question")
	private String question;

	@Column(name = "option_a")
	private String optionA;

	@Column(name = "option_b")
	private String optionB;

	@Column(name = "option_c")
	private String optionC;

	@Column(name = "option_d")
	private String optionD;

	@Column(name = "answer")
	private String answer;

	@ManyToOne(targetEntity = QuizEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "quiz_id", nullable = false, unique = false)
	private QuizEntity quiz;

}
