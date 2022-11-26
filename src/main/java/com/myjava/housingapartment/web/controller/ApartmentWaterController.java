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
			log.info("Error occured while creating new apartment water entity: Missing appartment id");
			return null;
		}
	}
	
	@GetMapping("apartment/{apartmentId}")
	public ResponseEntity<List<ApartmentWaterDto>>getWaterByApartment(@PathVariable (value = "apartmentId") UUID apartmentId){
		log.info("Finding apartment water by apartment id: " + apartmentId);
		
		return new ResponseEntity<List<ApartmentWaterDto>>(service.getApartmentWater(apartmentId),HttpStatus.OK);
		
	}
	
	@GetMapping("apartment/waterConsumption/{apartmentUUID}")
	public ResponseEntity<ApartmentWaterDto> getApartmentWaterConsumption(@PathVariable("apartmentUUID")UUID apartmentUUID, 
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startTime,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endTime ){
		
		
		
		/**
		 * public void processDateTime(@RequestParam("start") 
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
                            LocalDateTime date) {
        // The rest of your code (Spring already parsed the date).
}
		 */
		log.info("Get Apartment water consumption called with id: " + apartmentUUID);
		log.info("Start time: " + startTime);
		log.info("End Time: " + endTime);
		
		
		
		if (startTime.equals(null)| endTime.equals(null)) {
			log.error("Start time or end time missings");
			return new ResponseEntity<ApartmentWaterDto>(HttpStatus.BAD_REQUEST);
		}
		if (endTime.isBefore(startTime)) {
			log.error("Start time before end time");
			return new ResponseEntity<ApartmentWaterDto>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<ApartmentWaterDto>(service.getHousingApartmentWaterConsumption(apartmentUUID, startTime, endTime),HttpStatus.OK);
		
	}
	
	@PutMapping("apartment/water/{waterId}")
	public ResponseEntity<ApartmentWaterDto>updateWater(
			@PathVariable (value = "waterId") UUID waterId,
			@Valid @RequestBody ApartmentWaterDto waterDto ){
		
		
		log.info("Edit entity with electricity id: " + waterId);
		return new ResponseEntity<ApartmentWaterDto>(service.updateApartmetWater( waterId, waterDto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("apartment/water/{waterId}")
	public ResponseEntity<Boolean>deleteWater(
			@PathVariable (value = "waterId") UUID waterId
			 ){
		
		
		log.info("Delete entity with water id: " + waterId);
		
		return new ResponseEntity<Boolean>(service.deleteApartmentWater(waterId),HttpStatus.OK);
	}

}
