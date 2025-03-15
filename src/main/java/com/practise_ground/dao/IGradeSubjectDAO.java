package com.practise_ground.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.GradeSubjectEntity;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
@Repository
public interface IGradeSubjectDAO extends JpaRepository<GradeSubjectEntity, Long> {

	List<GradeSubjectEntity> findAllByGradeId(long gradeId);

}
