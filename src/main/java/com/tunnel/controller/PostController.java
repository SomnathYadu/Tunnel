package com.tunnel.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tunnel.entity.Post;
import com.tunnel.service.PostService;

@SuppressWarnings("unused")
@RestController
public class PostController {
	
	@Autowired
	private PostService service;
	
	@GetMapping(path="/posts/{owner}")
	public ResponseEntity<List<Post>> getAllOwnerPost(@PathVariable String owner) {
		List<Post> post = service.getOwnersAllPost(owner);
		return  new ResponseEntity<List<Post>>(post, HttpStatus.OK);
	}

	@GetMapping(path="/posts")
	public ResponseEntity<List<Post>> getAllPost() {
		List<Post> post = service.getOwnersAllPost();
		return  new ResponseEntity<List<Post>>(post, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/posts/delete/{postId}")
	public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
		//TODO:: get user from login resource
		Long userId = 1L;

		Post post = service.deletePost(userId, postId);
		return  new ResponseEntity<Post>(post, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post) {
		//TODO:: extract owner from logged in user
		String username = "wanderer123";

		Post result = service.createPost(username, post);
		return new ResponseEntity<Object>(result, HttpStatus.CREATED);
	}

//	@PostMapping(path = "/posts/initializePost")
//	public ResponseEntity<Object> createInitializePost() {
//		//TODO:: extract owner from logged in user
//		String username = "wanderer123";
//		Post generatedPost = new Post(postOwner, 0L, 0L, 0L, "video", "c:/postvideo/mp.4");
//		Post savedUser = service.createPost(generatedPost);
//
//		return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
//	}
}