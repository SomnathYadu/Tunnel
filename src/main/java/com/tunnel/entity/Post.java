package com.tunnel.entity;

import javax.persistence.*;

@Entity
@Table(name = "POST")
//TODO :: add postTitle, postCategory, postSection
public class Post {
	
	@Id
//	@SequenceGenerator(name = "user_post_id_gen", sequenceName = "user_post_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@Column(name = "likes")
	private Long likes;

	//TODO :: implement dislike
	@Column(name = "dislike")
	private Long dislike;

	@Column(name = "numberOfComments")
	private Long numberOfComments;

	@Column(name = "type")
	private String mediaType;

	@Column(name = "media_path")
	private String mediaPath;

	@Column(name = "title")
	private String title;



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	protected Post() {
		
	}

	public Post(User owner_username, Long likes, Long dislike, Long numberOfComments, String mediaType, String mediaPath) {
		this.user = owner_username;
		this.likes = likes;
		this.dislike = dislike;
		this.numberOfComments = numberOfComments;
		this.mediaType = mediaType;
		this.mediaPath = mediaPath;
	}

	public Post(User owner_username, Post post) {
		this.user = owner_username;
//		this.likes = post.getLikes();
//		this.dislike = post.getDislike();
//		this.numberOfComments = post.getNumberOfComments();
		this.mediaType = post.getMediaType();
		this.mediaPath = post.getMediaPath();
		this.title = post.getTitle();
	}

	public Long likePost() {
		this.likes++;
		return this.likes;
	}
	
	public Long dislike() {
		this.dislike++;
		return this.dislike;
	}
	
	public Long comment() {
		this.numberOfComments++;
		return this.numberOfComments;
	}

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", owner_username=" + user +
				", likes=" + likes +
				", dislike=" + dislike +
				", numberOfComments=" + numberOfComments +
				", mediaType='" + mediaType + '\'' +
				", mediaPath='" + mediaPath + '\'' +
				", title='" + title + '\'' +
				'}';
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public Long getId() {
		return id;
	}

	public Long getLikes() {
		return likes;
	}

	public Long getDislike() {
		return dislike;
	}

	public Long getNumberOfComments() {
		return numberOfComments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
