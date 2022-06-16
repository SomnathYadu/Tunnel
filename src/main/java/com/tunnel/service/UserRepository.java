package com.tunnel.service;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tunnel.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//INFO:: Using native MYSQL query
	@Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
	List<User> findByUserName(String username);
}