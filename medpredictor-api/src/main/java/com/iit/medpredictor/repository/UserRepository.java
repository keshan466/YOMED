package com.iit.medpredictor.repository;

import com.iit.medpredictor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository interface for user entity
 */
public interface UserRepository extends JpaRepository< User, Long >
{
	/**
	 * Query method to check given email exists
	 */
	boolean existsByEmail(String email);

	/**
	 * Query method to check given username exists
	 */
	boolean existsByUsername(String username);

	/**
	 * Query method to find user by given username and password
	 */
	Optional<User> findUserByUsernameAndAndPassword(String username, String password);

	/**
	 * Query method to find user by username
	 */
	User findUserByUsername(String username);

	/**
	 * Query method to count all users
	 */
	@Query(value = "select count(u) from User u")
	Long countAll();

}
