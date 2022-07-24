package com.tunnel.repository;

import com.tunnel.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query(value = "SELECT * FROM user_role", nativeQuery = true)
    List<UserRole> findByAll();
}
