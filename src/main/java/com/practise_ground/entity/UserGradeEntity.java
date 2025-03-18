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
@Table(name = "tb_user_grade")
@DynamicInsert
@DynamicUpdate
public class UserGradeEntity extends BaseEntity {

	private static final long serialVersionUID = 7907211550994971606L;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true)
	private UserEntity user;

	@ManyToOne(targetEntity = GradeEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "grade_id", nullable = true)
	private GradeEntity grade;

}
