package com.myjava.housingapartment.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.myjava.housingapartment.web.service.ApartmentWaterService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@WebMvcTest(ApartmentWaterController.class)
class ApartmentWaterControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	
	ApartmentMockLibrary lib = null;

	@MockBean
	ApartmentWaterService service;
	
	@BeforeEach
	void setUp() throws Exception {
		
		lib = new ApartmentMockLibrary();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getApartmentWaterByApartmentIdTest() throws Exception {
		given(service.getApartmentWater(any())).willReturn(lib.mockApartmentWaterDtos());
		
		mockMvc.perform(get("/api/apartmentwater/apartment/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void addNewApartmentElectricityTest() throws Exception {
		String apartmentJson = objectMapper.writeValueAsString(lib.mockApartmentWaterDto());
		log.info(apartmentJson);
		
		given(service.addApartmentWater(any(),any())).willReturn(lib.mockApartmentWaterDto());

		mockMvc.perform(post("/api/apartmentwater/apartment/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON).content(apartmentJson))
				.andExpect(status().isCreated());
	}
	
	@Test
	void updateApartmentElectricityTest()throws Exception{
		String apartmentJson = objectMapper.writeValueAsString(lib.mockApartmentWaterDto());
		log.info(apartmentJson);
		
		given(service.updateApartmetWater(any(), any(), any())).willReturn(lib.mockApartmentWaterDto());
		
		mockMvc.perform(put("/api/apartmentwater/apartment/"+UUID.randomUUID()+"/water/" + UUID.randomUUID())
				.contentType(MediaType.APPLICATION_JSON).content(apartmentJson))
				.andExpect(status()
				.isAccepted());
		
	}
	
	@Test
	void deleteApartmentElectricityTest() throws Exception{
		
		when(service.deleteApartmentWater(any())).thenReturn(true);
		
		mockMvc.perform(delete("/api/apartmentwater/water/" + UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status()
				.isOk());
		
	}
	
	@Test
	void deleteApartmentWaterNotFoundTest() throws Exception{
		
		when(service.deleteApartmentWater(any())).thenReturn(false);
		
		mockMvc.perform(delete("/api/apartmentwater/water/" + UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status()
				.isNotFound());
		
	}
	
	

}
