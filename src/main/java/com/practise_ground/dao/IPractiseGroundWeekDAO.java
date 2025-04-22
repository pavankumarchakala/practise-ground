package com.practise_ground.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.PractiseGroundWeekEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface IPractiseGroundWeekDAO extends JpaRepository<PractiseGroundWeekEntity, Long> {

	List<PractiseGroundWeekEntity> findByStatus(Status active);

	PractiseGroundWeekEntity findByName(String name);

	@Query(value = "SELECT week FROM PractiseGroundWeekEntity week WHERE :currDate BETWEEN week.startDate AND week.endDate")
	PractiseGroundWeekEntity findByCurrDate(Date currDate);

}
