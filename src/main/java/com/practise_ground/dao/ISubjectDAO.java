package com.practise_ground.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.SubjectEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface ISubjectDAO extends JpaRepository<SubjectEntity, Long> {

	List<SubjectEntity> findByStatus(Status active);

	SubjectEntity findByName(String name);

	Optional<SubjectEntity> findByIsDefault(boolean isDefault);

}
