package com.practise_ground.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.PractiseGroundYearEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface IPractiseGroundYearDAO extends JpaRepository<PractiseGroundYearEntity, Long> {

	List<PractiseGroundYearEntity> findAllByStatus(Status active);

	PractiseGroundYearEntity findByName(String name);

}
