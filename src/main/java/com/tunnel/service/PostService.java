package com.tunnel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tunnel.entity.Post;
import com.tunnel.exceptions.UserDoesNotOwnPostException;

@Repository
@Transactional
public class PostService {

	@Autowired
	public PostRepository postRepository;

	public Post createPost(Post post) {
		postRepository.save(post);
		return post;
	}

	public Post deletePost(String owner, Long postId) {
		
		Post post = this.getPost(owner, postId);
		if(post == null) {
			throw new UserDoesNotOwnPostException("User " + owner + " does not own post " + postId);
		}
		postRepository.delete(post);
		return post;
	}
	
	public Post getPost(String owner, Long postId) {
		Post post = postRepository.getPostById(owner, postId);
		return post;
	}

	public List<Post> getAllPost() {
		List<Post> posts = postRepository.findAll();
		return posts;
	}

	public List<Post> getAllPost(String username) {
		List<Post> posts = postRepository.getByOwner(username);
		if (posts == null || posts.size() == 0) {
			throw new UserDoesNotOwnPostException("User " + username + " does not own any post");
		}
		return posts;
	}

//	public Boolean likePost(Long postId) {
//		try {
//			Post post = postRepository.getReferenceById(postId);
//			post.setLike(post.getLike() + 1);
//			postRepository.save(post);
//			return true;
//		} catch(Exception e) {
//			//TODO :: Generate error log
//			return false;
//		}
//	}

	// TODO :: implement dislike
}
