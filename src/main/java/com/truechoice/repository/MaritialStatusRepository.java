package com.truechoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truechoice.model.MaritialStatusModel;

@Repository
public interface MaritialStatusRepository extends JpaRepository<MaritialStatusModel, Long>{

}
