package com.myjava.housingapartment.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/housingapartment/health")
@RestController
public class HealthCheckController {

	@GetMapping("/")
	public ResponseEntity<String> getHealth(){
		log.info("Ping I'm alive");
		String message = "Ping I'm alive";
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
		
		
	}
}
