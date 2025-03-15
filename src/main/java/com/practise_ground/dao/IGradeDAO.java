package com.practise_ground.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.GradeEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface IGradeDAO extends JpaRepository<GradeEntity, Long> {

	List<GradeEntity> findByStatus(Status active);

	GradeEntity findByName(String name);

}
