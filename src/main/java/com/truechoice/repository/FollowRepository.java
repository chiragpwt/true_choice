package com.truechoice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.truechoice.model.FollowInfo;
import com.truechoice.model.FollowersId;

public interface FollowRepository extends CrudRepository<FollowInfo, FollowersId>{

	@Query("FROM FollowInfo where userId.id = :userId and isFavorite = true")
	List<FollowInfo> getFavoritList(long userId );

	@Query("FROM FollowInfo where userId.id = :userId and isIgnored = true")
	List<FollowInfo> getIgnoredList(long userId);
	
	@Query("FROM FollowInfo where userId.id = :userId and isAccept = true")
	List<FollowInfo> getAcceptList(long userId);
	
	@Query("FROM FollowInfo where userId.id = :userId and isReject = true")
	List<FollowInfo> getRejectList(long userId);

	@Query("FROM FollowInfo where userId.id = :userId and isRequested = true")
	List<FollowInfo> getRequestedList(long userId);

	@Query("FROM FollowInfo where followId.id = :userId and isRequested = true")
	List<FollowInfo> getReceivedRequestList(long userId);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("update FollowInfo set isRequested = false where userId.id = :userId and followId.id = :followId ")
	void updateRequestList(long userId , long followId);
	
//	@Query("FROM FollowInfo where userId.id = :userId and followId.id = :followId and isReject = false or isIgnored = false")
//	Optional<FollowInfo> removeRejectandIgnore(long userId , long followId);

}
