package com.tunnel.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tunnel.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	// INFO:: Using native MYSQL query
	@Query(value = "SELECT * FROM post WHERE owner = ?1", nativeQuery = true)
	List<Post> getByOwner(String owner);

	// INFO:: Using native MYSQL query
	@Query(value = "SELECT * FROM post WHERE owner = ?1 AND id = ?2", nativeQuery = true)
	Post getPostById(String owner, Long postId);
}
