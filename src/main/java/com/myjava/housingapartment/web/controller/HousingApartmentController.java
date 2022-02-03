package com.myjava.housingapartment.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myjava.housingapartment.web.model.HousingApartmentDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/housingapartment/")
@RestController
public class HousingApartmentController {

	@PostMapping
	public  ResponseEntity<String> addNewApartment( HousingApartmentDto apartment){
		
		return new ResponseEntity<String>("foo",HttpStatus.OK);
	}
}
