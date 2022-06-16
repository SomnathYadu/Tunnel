package com.tunnel.controller;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tunnel.entity.Post;
import com.tunnel.service.PostService;
import com.tunnel.type.MediaType;

@SuppressWarnings("unused")
@RestController
public class PostController {
	
	@Autowired
	private PostService service;
	
	@GetMapping(path="/posts/{owner}")
	public ResponseEntity<List<Post>> getAllPost(@PathVariable String owner) {
		List<Post> post = service.getAllPost(owner);
		return  new ResponseEntity<List<Post>>(post, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/posts/delete/{postId}")
	public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
		//TODO:: get user from login resource 
		Post post = service.deletePost("wanderer123", postId);
		return  new ResponseEntity<Post>(post, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/posts")
	public ResponseEntity<Object> createUser(@RequestBody Post post) {
		//TODO:: extract owner from logged in user
		String username = "wanderer123";
		Post generatedPost = new Post(username, 0L, 0L, 0L, post.getMediaType(), post.getMediaPath());
		Post savedUser = service.createPost(generatedPost);

		return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
	}
}
