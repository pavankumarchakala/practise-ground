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
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "tb_user_subject")
@DynamicInsert
@DynamicUpdate
public class UserSubjectEntity extends BaseEntity {

	private static final long serialVersionUID = -5584520292027822225L;

	@ManyToOne(targetEntity = SubjectEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", nullable = false, unique = false)
	private SubjectEntity subject;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, unique = false)
	private UserEntity user;

}
