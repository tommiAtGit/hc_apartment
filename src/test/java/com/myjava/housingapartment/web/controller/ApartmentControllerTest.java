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
import com.myjava.housingapartment.web.service.ApartmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(ApartmentController.class)
class ApartmentControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	
	ApartmentMockLibrary lib = null;

	@MockBean
	ApartmentService service;

	@BeforeEach
	void setUp() throws Exception {
		
		lib = new ApartmentMockLibrary(); 
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getAparmentByIdTest() throws Exception {
		given(service.getHousingApartment(any())).willReturn(lib.mockApartmentDto());
		
		mockMvc.perform(get("/api/housingapartment/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void saveNewHousingApartmentTest() throws Exception {

		String apartmentJson = objectMapper.writeValueAsString(lib.mockApartmentDto());
		log.info(apartmentJson);
		

		given(service.addHousingAparment(any())).willReturn(lib.mockApartmentDto());

		mockMvc.perform(post("/api/housingapartment/").contentType(MediaType.APPLICATION_JSON).content(apartmentJson))
				.andExpect(status().isCreated());

	}
	
	@Test
	void getAllApartmentsTest() throws Exception {
		given(service.getHousingApartments()).willReturn(lib.mockApartments());
		
		mockMvc.perform(get("/api/housingapartment/").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	

}
