package com.practise_ground.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tb_subject")
@DynamicInsert
@DynamicUpdate
public class SubjectEntity extends BaseEntity {

	private static final long serialVersionUID = -4433948067465648415L;

	@Column(name = "name")
	private String name;

	@Column(name = "is_default", columnDefinition = "tinyint(1) default 0")
	private boolean isDefault;

}
