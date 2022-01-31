package com.myjava.housingapartment.housingApartment.web;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.myjava.housingapartment.domain.ApartmentWater;
import com.myjava.housingapartment.domain.HousingApartment;
import com.myjava.housingapartment.web.mappers.ApartmentElectricityMapper;
import com.myjava.housingapartment.web.mappers.ApartmentMapper;
import com.myjava.housingapartment.web.mappers.ApartmentWaterMapper;
import com.myjava.housingapartment.web.model.ApartmentWaterDto;
import com.myjava.housingapartment.web.model.HousingApartmentDto;



class ApartmentMapperTest {

	private ApartmentMapper apartmentMapper
    = Mappers.getMapper(ApartmentMapper.class);
	
	private ApartmentElectricityMapper electricityMapper
	= Mappers.getMapper(ApartmentElectricityMapper.class);
	
	private ApartmentWaterMapper waterMapper
	= Mappers.getMapper(ApartmentWaterMapper.class);
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test 
	void testApartmentObjecToDto() {
		HousingApartment apartmentObj = apartmentMapper.DtoToHousingApartment(mockApartmentDto());
		assertNotNull(apartmentObj);
		
	}
	
	@Test
	void testApartmentDtoToObject() {
		HousingApartment actualApartment = mockApartment();
		
		HousingApartmentDto apartmentDto = apartmentMapper.HousingApartmentToDto(actualApartment);
		assertNotNull(apartmentDto);
		assertEquals(actualApartment.getApartmentUUID(),apartmentDto.getApartmentUUID());
		
	}
	
//	@Test
//	void testApartmentWaterDtoToObject() {
//		
//		ApartmentWater aptW = waterMapper.DtoToApartmentWater(mockApartmentWaterDto());
//		assertNotNull(aptW);
//		assertEquals(mockApartmentWaterDto().getApartment().getApartment(), aptW.getApartment().getApartment());
//		
//		
//		
//	}
//	
//	@Test
//	void testApartmentWaterObjectToDto() {
//		
//		ApartmentWaterDto aptWDto = waterMapper.ApartmentWaterToDto(mockApartmentWater());
//		assertNotNull(aptWDto);
//		assertEquals(mockApartmentWater().getApartment().getApartment(), aptWDto.getApartment().getApartment());
//		
//	}
	
	private HousingApartment mockApartment() {
		HousingApartment apartment = HousingApartment.builder()
				.apartmentUUID(UUID.randomUUID())
				.cooperativeUUID(UUID.randomUUID())
				.userUUID(UUID.randomUUID())
				.apartment("3B")
				.build();
		return apartment;
	}
	
	private HousingApartmentDto mockApartmentDto() {
		HousingApartmentDto housingApartmentDto = HousingApartmentDto.builder()
				.apartmentUUID(UUID.randomUUID())
				.cooperativeUUID(UUID.randomUUID())
				.userUUID(UUID.randomUUID())
				.apartment("3B")
				.build();
		return housingApartmentDto;
	}
	
	private ApartmentWater mockApartmentWater() {
		
		ApartmentWater apartmentWater = ApartmentWater.builder()
				.id(UUID.randomUUID())
				.apartment(mockApartment())
				.couldWater(4321.0)
				.hotWater(1234.0)
				.measurementDate(new Timestamp(System.currentTimeMillis()))
				.build();
		
		return apartmentWater;
	}
	
	
	private ApartmentWaterDto mockApartmentWaterDto() {
		
		ApartmentWaterDto apartmentWaterDto = ApartmentWaterDto.builder()
				.id(UUID.randomUUID())
				.apartment(mockApartmentDto())
				.couldWater(4321.0)
				.hotWater(1234.0)
				.measurementDate(OffsetDateTime.now())
				.build();
		
		return apartmentWaterDto;
	}
}
