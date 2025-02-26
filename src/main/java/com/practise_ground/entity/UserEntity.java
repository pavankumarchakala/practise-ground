package com.practise_ground.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.practise_ground.enums.Gender;
import com.practise_ground.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 25, 2025
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "tb_user")
@DynamicInsert
@DynamicUpdate
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 3043163430423347121L;

	@Column(name = "full_name")
	private String fullName;

	@ManyToOne(targetEntity = GradeEntity.class)
	@JoinColumn(name = "grade_id", nullable = false)
	private GradeEntity grade;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRole role;

	@Column(name = "date_of_birth", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateOfBirth;

	@Column(name = "email")
	private String email;

	@Column(name = "is_email_verified", columnDefinition = "tinyint(1) default 0")
	private boolean isEmailVerified;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "school")
	private String school;

	@Column(name = "postal_code")
	private String postalCode;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserSubjectEntity> userSubjects;

}
