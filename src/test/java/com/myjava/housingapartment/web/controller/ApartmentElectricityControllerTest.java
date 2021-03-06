package com.myjava.housingapartment.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
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
		given(service.getApartmentElecricity(any())).willReturn(lib.mockApartmentElectricityDtos());
		
		mockMvc.perform(get("/api/apartmentelectricity/apartment/" + UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status()
				.isOk());
	}
	
	@Test
	void calculateApartmentElectricityConsumptionTest()throws Exception{
		when(service.getHousingApartmenElectricityConsumption(any(),any(), any())).thenReturn(lib.mockApartmentElectricityDto());
		
		String startTime = OffsetDateTime.now().minusDays(1).toString();
		String endTime = OffsetDateTime.now().plusDays(1).toString();
		mockMvc.perform(get("/api/apartmentelectricity/apartment/electricityConsumption/" + UUID.randomUUID() +"?start=" +startTime+"&end="+endTime)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status()
				.isOk());
	}
	
	@Test
	void calculateApartmentElectricityConsumptionConflictRequestTest()throws Exception{
		when(service.getHousingApartmenElectricityConsumption(any(),any(), any())).thenReturn(lib.mockApartmentElectricityDto());
		
		String startTime = OffsetDateTime.now().plusDays(1).toString();
		String endTime = OffsetDateTime.now().minusDays(1).toString();
		mockMvc.perform(get("/api/apartmentelectricity/apartment/electricityConsumption/" + UUID.randomUUID() +"?start=" +startTime+"&end="+endTime)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status()
				.isConflict());
	}
	
	@Test
	void calculateApartmentElectricityConsumptionBadRequestTest()throws Exception{
		when(service.getHousingApartmenElectricityConsumption(any(),any(), any())).thenReturn(lib.mockApartmentElectricityDto());
		
		String startTime = null;
		String endTime = OffsetDateTime.now().minusDays(1).toString();
		mockMvc.perform(get("/api/apartmentelectricity/apartment/electricityConsumption/" + UUID.randomUUID() +"?start=" +startTime+"&end="+endTime)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status()
				.isBadRequest());
	}
	
	@Test
	void addNewApartmentElectricityTest() throws Exception {
		String apartmentJson = objectMapper.writeValueAsString(lib.mockApartmentElectricityDto());
		log.info(apartmentJson);
		
		given(service.addApartmentElectricity(any(),any())).willReturn(lib.mockApartmentElectricityDto());

		mockMvc.perform(post("/api/apartmentelectricity/apartment/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON).content(apartmentJson))
				.andExpect(status().isCreated());
	}
	
	@Test
	void updateApartmentElectricityTest()throws Exception{
		String apartmentJson = objectMapper.writeValueAsString(lib.mockApartmentElectricityDto());
		log.info(apartmentJson);
		
		given(service.updateApartmentElectricity(any(), any())).willReturn(lib.mockApartmentElectricityDto());
		
		mockMvc.perform(put("/api/apartmentelectricity/apartment/electricity/" + UUID.randomUUID())
				.contentType(MediaType.APPLICATION_JSON).content(apartmentJson))
				.andExpect(status()
				.isAccepted());
		
	}
	
	@Test
	void deleteApartmentElectricityTest() throws Exception{
		
		when(service.deleteApartmentElectricity(any())).thenReturn(true);
		
		mockMvc.perform(delete("/api/apartmentelectricity/electricity/" + UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status()
				.isOk());
		
	}
	
	@Test
	void deleteApartmentElectricityNotFoundTest() throws Exception{
		
		when(service.deleteApartmentElectricity(any())).thenReturn(false);
		
		mockMvc.perform(delete("/api/apartmentelectricity/electricity/" + UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status()
				.isNotFound());
		
	}

}
