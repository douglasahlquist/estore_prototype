package com.ahlquist.estore.repositories;

/**
 * @author Douglas Ahlquist
 *
 */

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u where u.email = :email")
	Optional<User> findByEmail(String email);

	@Query("SELECT u FROM User u where u.username = :username")
	public Optional<User> findByUsername(@Param("username") final String username);

	@Query("SELECT u FROM User u where u.id = :username AND u.password = :password")
	public Optional<User> findByUsernamePassword(@Param("username") final String username,
			@Param("password") final String password);

}