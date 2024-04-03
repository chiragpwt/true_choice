package com.truechoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truechoice.model.PlanInfo;

public interface PlanRepository extends JpaRepository<PlanInfo, Integer>{

}
