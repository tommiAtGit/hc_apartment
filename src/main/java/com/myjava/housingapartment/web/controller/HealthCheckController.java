package com.myjava.housingapartment.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/hc_apartment")
@RestController
public class HealthCheckController {

	@GetMapping(value="/health")
	public ResponseEntity<String> getHealth(){
		
		String message = "-- Ping from housing apartment ---";
		
		log.info(message);
		
		
		return ResponseEntity.ok(message);
		
		
	}
}
