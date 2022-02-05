package com.myjava.housingapartment.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myjava.housingapartment.web.model.HousingApartmentDto;
import com.myjava.housingapartment.web.service.ApartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/housingapartment/")
@RestController
public class HousingApartmentController {

	private final ApartmentService apartmentService;
	
	@PostMapping
	public  ResponseEntity<HousingApartmentDto> addNewApartment( @Validated @RequestBody HousingApartmentDto apartmentDto){
		
		log.info("New apartment added with name: " + apartmentDto.getApartment());
		return new ResponseEntity<HousingApartmentDto>(apartmentService.addHousingAparment(apartmentDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{apartmentID}")
	public ResponseEntity<HousingApartmentDto> getApartmentById(@PathVariable("cooperativeId")UUID apartmentUUID){
		log.info("Get Apartment called with id: " + apartmentUUID);
		
		return new ResponseEntity<HousingApartmentDto>(apartmentService.getHousingApartment(apartmentUUID),HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<HousingApartmentDto>> getApartments(){
		log.info("Get all Apartments" );
		
		return new ResponseEntity<List<HousingApartmentDto>>(apartmentService.getHousingApartments(),HttpStatus.OK);
		
	}
	
}
