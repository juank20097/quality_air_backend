	package com.quality_air.quality_air_backend.repo;
	
	import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
	
	import com.quality_air.quality_air_backend.entities.User;

import io.swagger.v3.oas.annotations.Hidden;
	
	@Hidden
	@Repository
	public interface UserRepo extends JpaRepository<User, Integer> {
		
		@Query("select a from User a where a.status = true")
		List<User> findByStatus();
		
		@Query("SELECT u FROM User u WHERE (u.email = :identifier OR u.nickName = :identifier) AND u.password = :password")
		Optional<User> findByIdentifierAndPassword(@Param("identifier") String identifier, @Param("password") String password);

	
	}
