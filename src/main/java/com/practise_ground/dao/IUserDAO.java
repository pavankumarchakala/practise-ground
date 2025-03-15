package com.practise_ground.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.entity.UserEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.enums.UserRole;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface IUserDAO extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findByRole(UserRole role);

	List<UserEntity> findByStatus(Status active);

	List<UserEntity> findByRoleAndStatus(UserRole role, Status active);

	Optional<UserEntity> findByEmailAndStatus(String email, Status active);

}
