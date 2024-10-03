package com.quality_air.quality_air_backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quality_air.quality_air_backend.entities.User;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Repository interface for accessing User entities.
 * 
 * This interface extends JpaRepository to provide CRUD operations 
 * and custom query methods for User entities in the database.
 */
@Hidden
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    /**
     * Retrieves a list of active users (status = true).
     * 
     * @return a list of users with active status.
     */
    @Query("select a from User a where a.status = true")
    List<User> findByStatus();

    /**
     * Finds a user by email or nickname and password.
     * 
     * @param identifier the email or nickname of the user.
     * @param password   the password of the user.
     * @return an Optional containing the user if found, otherwise empty.
     */
    @Query("SELECT u FROM User u WHERE (u.email = :identifier OR u.nickName = :identifier) AND u.password = :password")
    Optional<User> findByIdentifierAndPassword(@Param("identifier") String identifier, @Param("password") String password);
}
