package com.myjava.housingapartment.web.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myjava.housingapartment.web.model.ApartmentElectricityDto;
import com.myjava.housingapartment.web.service.ApartmentElectricityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/apartmentelectricity/")
@RestController
public class ApartmentElectricityController {
	
	@Autowired
	ApartmentElectricityService service;

	@PostMapping("apartment/{apartmentId}/electricity")
	ResponseEntity<ApartmentElectricityDto> addNewElectricity(@PathVariable (value = "apartmentId") UUID apartmentId,
			@Valid @RequestBody ApartmentElectricityDto eDto) {
		
		if (apartmentId != null) {
			log.info("New electricity entity added to apartment: " + apartmentId);
			return new ResponseEntity<ApartmentElectricityDto>(service.addApartmentElectricity(apartmentId,eDto),HttpStatus.CREATED);
		}
		else {
			log.info("Error occured while creating new apartment electricity entity: Missing appartment id");
			return null;
		}
		
		
		
	}
	
	@GetMapping("apartment/{apartmentId}")
	ResponseEntity<List<ApartmentElectricityDto>>getElectricityByApartment(@PathVariable (value = "apartmentId") UUID apartmentId){
		log.info("Finding apartment electricity by apartment id: " + apartmentId);
		
		return new ResponseEntity<List<ApartmentElectricityDto>>(service.getApartmentElecricity(apartmentId),HttpStatus.OK);
		
	}
	
	
	@PutMapping("apartment/{apartmentId}/electricity/{electricityId}")
	ResponseEntity<ApartmentElectricityDto>updateElectricity(@PathVariable (value = "apartmentId") UUID apartmentId,
			@PathVariable (value = "electricityId") UUID electricityId,
			@Valid @RequestBody ApartmentElectricityDto eDto ){
		
		log.info("Edit apartment electricity id: " + apartmentId);
		return new ResponseEntity<ApartmentElectricityDto>(service.updateApartmentElectricity(apartmentId, electricityId, eDto),HttpStatus.ACCEPTED);
	}
	
	
}
