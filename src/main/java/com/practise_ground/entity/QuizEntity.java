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
@Table(name = "tb_quiz")
@DynamicInsert
@DynamicUpdate
public class QuizEntity extends BaseEntity {

	private static final long serialVersionUID = 4628691460663140874L;

	@Column(name = "name")
	private String name;

	@ManyToOne(targetEntity = SubjectEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", nullable = false, unique = false)
	private SubjectEntity subject;

	@ManyToOne(targetEntity = GradeEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id", nullable = false, unique = false)
	private GradeEntity grade;

	@ManyToOne(targetEntity = PractiseGroundWeekEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "week_id", nullable = false, unique = false)
	private PractiseGroundWeekEntity week;

	@ManyToOne(targetEntity = PractiseGroundYearEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "year_id", nullable = false, unique = false)
	private PractiseGroundYearEntity year;

}
