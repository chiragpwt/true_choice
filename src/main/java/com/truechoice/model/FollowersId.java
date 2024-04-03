package com.truechoice.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FollowersId implements Serializable {

	@Column(name = "user_Id")
	private long userId;
	
	@Column(name = "follow_Id")
	private long followId;

	public FollowersId() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFollowId() {
		return followId;
	}

	public void setFollowId(long followId) {
		this.followId = followId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(followId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FollowersId other = (FollowersId) obj;
		return followId == other.followId && userId == other.userId;
	}
}
