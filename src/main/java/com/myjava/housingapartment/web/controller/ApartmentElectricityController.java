package com.myjava.housingapartment.web.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("apartment/{apartmentId}")
	public ResponseEntity<ApartmentElectricityDto> addNewElectricity(@PathVariable (value = "apartmentId") UUID apartmentId,
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
	public ResponseEntity<List<ApartmentElectricityDto>>getElectricityByApartment(@PathVariable (value = "apartmentId") UUID apartmentId){
		log.info("Finding apartment electricity by apartment id: " + apartmentId);
		
		return new ResponseEntity<List<ApartmentElectricityDto>>(service.getApartmentElecricity(apartmentId),HttpStatus.OK);
		
	}
	
	@GetMapping("apartment/electricityConsumption/{apartmentUUID}")
	public ResponseEntity<ApartmentElectricityDto> getApartmentWaterConsumption(@PathVariable("apartmentUUID")UUID apartmentUUID, 
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startTime,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endTime ){
		
		
		
		/**
		 * public void processDateTime(@RequestParam("start") 
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
                            LocalDateTime date) {
        // The rest of your code (Spring already parsed the date).
}
		 */
		log.info("Get Apartment electricity consumption called with id: " + apartmentUUID);
		log.info("Start time: " + startTime);
		log.info("End Time: " + endTime);
		
		
		
		if (startTime.equals(null)| endTime.equals(null)) {
			log.error("Start time or end time missings");
			return new ResponseEntity<ApartmentElectricityDto>(HttpStatus.BAD_REQUEST);
		}
		if (endTime.isBefore(startTime)) {
			log.error("Start time before end time");
			return new ResponseEntity<ApartmentElectricityDto>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<ApartmentElectricityDto>(service.getHousingApartmenElectricityConsumption(apartmentUUID, startTime, endTime),HttpStatus.OK);
		
	}
	
	@PutMapping("apartment/electricity/{electricityId}")
	public ResponseEntity<ApartmentElectricityDto>updateElectricity( @PathVariable (value = "electricityId") UUID electricityId,
			@Valid @RequestBody ApartmentElectricityDto eDto ){
		
		log.info("Edit apartment electricity id: " + electricityId);
		return new ResponseEntity<ApartmentElectricityDto>(service.updateApartmentElectricity(electricityId, eDto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("electricity/{electricityId}")
	public ResponseEntity<UUID> deleteElectricity(@PathVariable (value = "electricityId") UUID electricityId){
		
		log.info("Deleting electricity with id: " + electricityId);
		
		var isRemoved = service.deleteApartmentElectricity(electricityId);
		
		if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(electricityId, HttpStatus.OK);
		
	}
	
}
