package com.practise_ground.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "tb_grade_subject")
@DynamicInsert
@DynamicUpdate
public class GradeSubjectEntity extends BaseEntity {

	private static final long serialVersionUID = -8430100421194541593L;

	@ManyToOne(targetEntity = GradeEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id", nullable = false, unique = false)
	private GradeEntity grade;

	@ManyToOne(targetEntity = SubjectEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", nullable = false, unique = false)
	private SubjectEntity subject;

}
