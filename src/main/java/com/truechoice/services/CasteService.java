package com.truechoice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.truechoice.model.Caste;
import com.truechoice.repository.CasteRepository;

@Service
public class CasteService {

	@Autowired
	CasteRepository castRepo;
	
	public ResponseEntity<?> addCaste(String name) {
		Caste caste = new Caste();
		caste.setName(name);
		castRepo.save(caste);
		return new ResponseEntity(caste , HttpStatus.OK);
	}

	public List<Caste> getAllCaste() {
		 List<Caste>  castList= castRepo.findAll();
		return castList;
	}

}
