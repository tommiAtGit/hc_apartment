package com.myjava.housingapartment.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.myjava.housingapartment.domain.HousingApartment;
import com.myjava.housingapartment.test.utils.ApartmentMockLibrary;
import com.myjava.housingapartment.web.mappers.ApartmentMapper;
import com.myjava.housingapartment.web.model.HousingApartmentDto;



class ApartmentMapperTest {

	ApartmentMockLibrary lib = null;
	
	ApartmentMapper apartmentMapper = null;
	
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		lib = new ApartmentMockLibrary();
		apartmentMapper = new ApartmentMapper();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test 
	void testApartmentObjecToDto() {
		
		HousingApartmentDto actualApartmentDto = lib.mockApartmentDto();
		HousingApartment apartmentObj = apartmentMapper.mapDtoToObject(actualApartmentDto);
		assertNotNull(apartmentObj);
		assertEquals(actualApartmentDto.getApartmentUUID(),apartmentObj.getApartmentUUID());
		
	}
	
	@Test
	void testApartmentDtoToObject() {
		HousingApartment actualApartment = lib.mockApartment();
		
		HousingApartmentDto apartmentDto = apartmentMapper.mapObjectToDto(actualApartment);
		assertNotNull(apartmentDto);
		assertEquals(actualApartment.getApartmentUUID(),apartmentDto.getApartmentUUID());
		
		
	}
}
	
	