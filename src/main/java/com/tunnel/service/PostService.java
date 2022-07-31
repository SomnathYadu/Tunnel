package com.tunnel.service;

import java.util.List;

import javax.transaction.Transactional;

import com.tunnel.entity.User;
import com.tunnel.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tunnel.entity.Post;
import com.tunnel.exceptions.UserDoesNotOwnPostException;

@Repository
@Transactional
public class PostService {

	@Autowired
	public PostRepository postRepository;

	@Autowired
	private UserService userService;

	public Post createPost(String username, Post post) {
		User postOwner = userService.getUserByUserName(username);
		Post generatedPost = new Post(postOwner, post);
		return postRepository.save(generatedPost);
	}

	public Post deletePost(Long user, Long postId) {
		Post post = this.getPost(user, postId);
		if(post == null) {
			throw new UserDoesNotOwnPostException("User " + user + " does not own post " + postId);
		}
		postRepository.delete(post);
		return post;
	}
	
	public Post getPost(Long user, Long postId) {
		Post post = postRepository.getPostById(user, postId);
		return post;
	}

	public List<Post> getOwnersAllPost() {
		List<Post> posts = postRepository.findAll();
		return posts;
	}

	public List<Post> getOwnersAllPost(String username) {
		User user = userService.getUserByUserName(username);

		List<Post> posts = postRepository.getByOwner(user.getUserId());
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
