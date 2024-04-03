package com.truechoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truechoice.model.MotherTongueModel;

@Repository
public interface MotherTongueRepository extends JpaRepository<MotherTongueModel, Long>{

}
