package com.truechoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.truechoice.model.UserInfo;
import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<UserInfo, Long>{

	@Query("FROM UserInfo u WHERE u.id !=:id and u.gender =:gender")
	List<UserInfo> myMatchesList(long id , String gender);

//	@Query("Select u FROM UserInfo u , FollowInfo f WHERE u.id !=:id and u.gender =:gender OR (f.userId.id = :id and f.isReject = false or f.isIgnored = false) ORDER BY RANDOM() limit 10")
//	List<UserInfo> myMatchesList(long id , String gender);
	
	@Query("FROM UserInfo u WHERE u.id != :id and"
			+ " u.gender =:gender "
			+ "AND (:age is null or u.age =:age) "
			+ "AND (:motherTongue is null or u.motherTongueInfo.id =:motherTongue) "
			+ "AND (:height is null or u.height =:height) "
			+ "AND (:weight is null or u.weight =:weight) "
			+ "AND (:education is null or u.education.id = :education) "
			+ "AND (:religion is null or u.religion.id = :religion) "
			+ "AND (:caste is null or u.caste.id = :caste) "
			+ "order by u.createdAt asc limit 20")
	List<UserInfo> getNewMatches(long id, String gender ,Integer age, Long motherTongue, 
			String height, String weight, Long education,
			Long religion, Long caste);
	
	@Query("FROM UserInfo u WHERE u.id != :id and"
			+ " u.gender =:gender "
			+ "AND u.planInfo.id =:planId "
			+ "AND (:age is null or u.age =:age) "
			+ "AND (:motherTongue is null or u.motherTongueInfo.id =:motherTongue) "
			+ "AND (:height is null or u.height =:height) "
			+ "AND (:weight is null or u.weight =:weight) "
			+ "AND (:education is null or u.education.id = :education) "
			+ "AND (:religion is null or u.religion.id = :religion) "
			+ "AND (:caste is null or u.caste.id = :caste) "
			+ "order by u.createdAt asc limit 20")
	List<UserInfo> getPremiumMatches(long id, long planId, String gender ,Integer age, Long motherTongue, 
			String height, String weight, Long education,
			Long religion, Long caste);
}
