package com.myjava.housingapartment.web.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myjava.housingapartment.web.model.ApartmentWaterDto;
import com.myjava.housingapartment.web.service.ApartmentWaterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/apartmentwater/")
@RestController
public class ApartmentWaterController {
	
	@Autowired
	ApartmentWaterService service;
	
	@PostMapping("apartment/{apartmentId}")
	public ResponseEntity<ApartmentWaterDto> addNewWaterEntry(@PathVariable (value = "apartmentId") UUID apartmentId,
			@Valid @RequestBody ApartmentWaterDto waterDto) {
		
		if (apartmentId != null) {
			log.info("New water entity added to apartment: " + apartmentId);
			return new ResponseEntity<ApartmentWaterDto>(service.addApartmentWater(apartmentId, waterDto) ,HttpStatus.CREATED);
		}
		else {
			log.info("Error occured while creating new apartment electricity entity: Missing appartment id");
			return null;
		}
	}
	
	@GetMapping("apartment/{apartmentId}")
	public ResponseEntity<List<ApartmentWaterDto>>getWaterByApartment(@PathVariable (value = "apartmentId") UUID apartmentId){
		log.info("Finding apartment water by apartment id: " + apartmentId);
		
		return new ResponseEntity<List<ApartmentWaterDto>>(service.getApartmentWater(apartmentId),HttpStatus.OK);
		
	}
	
	@PutMapping("apartment/{apartmentId}/water/{waterId}")
	public ResponseEntity<ApartmentWaterDto>updateElectricity(@PathVariable (value = "apartmentId") UUID apartmentId,
			@PathVariable (value = "waterId") UUID waterId,
			@Valid @RequestBody ApartmentWaterDto waterDto ){
		
		log.info("Edit apartment electricity id: " + apartmentId);
		return new ResponseEntity<ApartmentWaterDto>(service.updateApartmetWater(apartmentId, waterId, waterDto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("electricity/{waterId}")
	public ResponseEntity<UUID> deleteElectricity(@PathVariable (value = "waterId") UUID waterId){
		
		log.info("Deleting electricity with id: " + waterId);
		
		var isRemoved = service.deleteApartmentWater(waterId);
		
		if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(waterId, HttpStatus.OK);
		
	}
	

}
