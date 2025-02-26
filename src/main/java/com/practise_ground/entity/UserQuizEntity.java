package com.practise_ground.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "tb_user_quiz_score")
@DynamicInsert
@DynamicUpdate
public class UserQuizEntity extends BaseEntity {

	private static final long serialVersionUID = -5584520292027822225L;

	@ManyToOne(targetEntity = QuizEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_id", nullable = false, unique = false)
	private QuizEntity quiz;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, unique = false)
	private UserEntity user;

	@Column(name = "score", columnDefinition = "Decimal(3,2) default '0.00'")
	private BigDecimal score;

	@Lob
	@Column(name = "quiz_qa")
	private String quizQA;
}
