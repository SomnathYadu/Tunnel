package com.tunnel.repository;
import java.util.List;
import java.util.Optional;

import com.tunnel.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tunnel.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//INFO:: Using native MYSQL query
	@Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
	List<User> getByUserName(String username);

	@Query(value = "select r.role_name from users u inner join user_role ur on (u.user_role_id=ur.user_role_id) inner join role r on(ur.user_role=r.role_id) where username=?1", nativeQuery = true)
	List<Object> getRoleName(String username);

	Optional<User> findByusername(String username);
}