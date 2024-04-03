package com.truechoice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.truechoice.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long>{

	public Optional<UserInfo> findByEmail(String email);
	
	public Optional<UserInfo> findByPhone(String mobile);
	
	@Query("FROM UserInfo where email = :email and phone = :phone")
	List<UserInfo> checkUser(String email , String phone);
	
}
