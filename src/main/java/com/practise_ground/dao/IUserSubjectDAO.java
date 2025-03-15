package com.practise_ground.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.UserSubjectEntity;
import com.practise_ground.enums.Status;

/**
 * @author Pavankumar - created date : Mar 07, 2025
 *
 */
@Repository
public interface IUserSubjectDAO extends JpaRepository<UserSubjectEntity, Long> {

	void deleteByUserId(long id);

	List<UserSubjectEntity> findByUserIdAndStatus(long userId, Status active);

	List<UserSubjectEntity> findBySubjectIdAndStatus(long subjectId, Status active);

	void deleteAllByUserId(long userId);

}
