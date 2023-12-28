package com.Registration.RegistrationProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Registration.RegistrationProject.entity.User;

public interface UserDao extends JpaRepository<User,Long> {
	
	User findByUsername(String username);
	
	@Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username")
    public boolean existsByUsername(@Param("username") String username);
}
