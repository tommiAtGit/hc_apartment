package com.myjava.housingapartment.web.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class ApartmentController {

	private final ApartmentService apartmentService;
	
	@PostMapping
	public  ResponseEntity<HousingApartmentDto> addNewApartment( @Validated @RequestBody HousingApartmentDto apartmentDto){

		log.info("New apartment added with name: " + apartmentDto.getApartment());
		return new ResponseEntity<HousingApartmentDto>(apartmentService.addHousingAparment(apartmentDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{apartmentUUID}")
	public ResponseEntity<HousingApartmentDto> getApartmentById(@PathVariable("apartmentUUID")UUID apartmentUUID){
		log.info("Get Apartment called with id: " + apartmentUUID);
		
		return new ResponseEntity<HousingApartmentDto>(apartmentService.getHousingApartment(apartmentUUID),HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<HousingApartmentDto>> getApartments(){
		log.info("Get all Apartments" );
		
		return new ResponseEntity<List<HousingApartmentDto>>(apartmentService.getHousingApartments(),HttpStatus.OK);
		
	}
	@PutMapping("apartment/{apartmentId}")
	public ResponseEntity<HousingApartmentDto>updateApartment(
			@PathVariable (value = "apartmentId") UUID apartmentId,
			@Valid @RequestBody HousingApartmentDto apartmentDto ){
		log.info("Edit entity with apartment id: " + apartmentId);
		return new ResponseEntity<HousingApartmentDto>(apartmentService.updateApartmet( apartmentId, apartmentDto),HttpStatus.ACCEPTED);
	}

	@DeleteMapping("apartment/{apartmentId}")
	public ResponseEntity<Boolean>deleteApartment(
			@PathVariable (value = "apartmentId") UUID apartmentId,
			@Valid @RequestBody HousingApartmentDto apartmentDto ){
		
		
		log.info("Delete entity with apartment id: " + apartmentId);
		if (apartmentDto != null){
			try {
				apartmentService.deleteHousingApartment(apartmentDto);
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			} catch (Exception e) {
				log.error("Error occured while deleting apartment ");
				return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else{
			log.error("No apartment available while deleting apartment");
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
