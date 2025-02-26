package com.practise_ground.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise_ground.dto.UserDTO;
import com.practise_ground.entity.UserEntity;
import com.practise_ground.enums.UserRole;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Repository
public interface IUserDAO extends JpaRepository<UserEntity, Long> {

	List<UserDTO> findAllByRole(UserRole role);

}
