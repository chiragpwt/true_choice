package com.truechoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truechoice.model.ImageDetails;

public interface ImageRepository extends JpaRepository<ImageDetails, Long>{

}
