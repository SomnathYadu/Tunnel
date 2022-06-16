package com.tunnel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
public class Post {
	
	

	@Id
//	@SequenceGenerator(name = "user_post_id_gen", sequenceName = "user_post_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//TODO :: add postTitle, postCatagory, postSection
	@Column(name = "owner")
	private String ownerUserName;
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
	
	protected Post() {
		
	}

	public Post(String ownerUserName, Long likes, Long dislike, Long numberOfComments,
			String mediaType, String mediaPath) {
		super();
		this.ownerUserName = ownerUserName;
		this.likes = likes;
		this.dislike = dislike;
		this.numberOfComments = numberOfComments;
		this.mediaType = mediaType;
		this.mediaPath = mediaPath;
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
		return "Post [id=" + id + ", owner=" + ownerUserName + ", likes=" + likes + ", dislike=" + dislike
				+ ", numberOfComments=" + numberOfComments + ", mediaType=" + mediaType + ", mediaPath=" + mediaPath
				+ "]";
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

	public String getOwner() {
		return ownerUserName;
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

}
