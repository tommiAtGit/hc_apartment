package com.myjava.housingapartment.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myjava.housingapartment.test.utils.ApartmentMockLibrary;
import com.myjava.housingapartment.web.service.ApartmentElectricityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(ApartmentElectricityController.class)
class ApartmentElectricityControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	
	ApartmentMockLibrary lib = null;

	@MockBean
	ApartmentElectricityService service;
	
	@BeforeEach
	void setUp() throws Exception {
		
		lib = new ApartmentMockLibrary();
	}
	

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getApartmentElectricityByApartmentIdTest() throws Exception {
		given(service.getApartmentElecricity(any())).willReturn(lib.apartmentElectricityDtos());
		
		mockMvc.perform(get("/api/apartmentelectricity/apartment/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void addNewApartmentElectricityTest() throws Exception {
		String apartmentJson = objectMapper.writeValueAsString(lib.mockApartmentElectricityDto());
		log.info(apartmentJson);
		
		given(service.addApartmentElectricity(any(),any())).willReturn(lib.mockApartmentElectricityDto());

		mockMvc.perform(post("/api/apartmentelectricity/apartment/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON).content(apartmentJson))
				.andExpect(status().isCreated());
	}

}
