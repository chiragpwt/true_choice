package com.truechoice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "follow_details")
public class FollowInfo implements java.io.Serializable {

	@EmbeddedId
	@Id
	private FollowersId id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_Id" , nullable = true)
	@MapsId("userId")
	private UserInfo userId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="follow_Id" , nullable = true)
	@MapsId("followId")
	private UserInfo followId;
	
//	@Id
//	@Column(name = "user_Id")
//	private long userId;
//	
//	@Id
//	@Column(name = "follow_Id")
//	private long followId;

	@Column(name = "requested" , columnDefinition = "boolean default false", nullable = false)
	private boolean isRequested;
	
	@Column(name = "accept" , columnDefinition = "boolean default false", nullable = false)
	private boolean isAccept;
	
	@Column(name = "ignored" , columnDefinition = "boolean default false", nullable = false)
	private boolean isIgnored;
	
	@Column(name = "favorite" , columnDefinition = "boolean default false", nullable = false)
	private boolean isFavorite;
	
	@Column(name = "reject" , columnDefinition = "boolean default false", nullable = false)
	private boolean isReject;
	
	public FollowInfo() {
		super();
	}

	public FollowersId getId() {
		return id;
	}

	public void setId(FollowersId id) {
		this.id = id;
	}

	public UserInfo getUserId() {
		return userId;
	}

	public void setUserId(UserInfo userId) {
		this.userId = userId;
	}

	public UserInfo getFollowId() {
		return followId;
	}

	public void setFollowId(UserInfo followId) {
		this.followId = followId;
	}

	public boolean isRequested() {
		return isRequested;
	}

	public void setRequested(boolean isRequested) {
		this.isRequested = isRequested;
	}

	public boolean isAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}

	public boolean isIgnored() {
		return isIgnored;
	}

	public void setIgnored(boolean isIgnored) {
		this.isIgnored = isIgnored;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public boolean isReject() {
		return isReject;
	}

	public void setReject(boolean isReject) {
		this.isReject = isReject;
	}
}
