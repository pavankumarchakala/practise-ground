package com.practise_ground.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.dto.UserGradeDTO;
import com.practise_ground.entity.UserGradeEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Mar 15, 2025
 *
 */
@Repository
public interface IUserGradeDAO extends JpaRepository<UserGradeEntity, Long> {

	List<UserGradeDTO> findAllByGradeIdAndStatus(long gradeId, Status active);

	List<UserGradeDTO> findAllByUserIdAndStatus(long userId, Status active);

}
