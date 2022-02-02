package com.myjava.housingapartment.housingApartment.web;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.myjava.housingapartment.domain.ApartmentElectricity;
import com.myjava.housingapartment.domain.ApartmentWater;
import com.myjava.housingapartment.domain.HousingApartment;
import com.myjava.housingapartment.web.mappers.ApartmentMapper;
import com.myjava.housingapartment.web.model.ApartmentElectricityDto;
import com.myjava.housingapartment.web.model.ApartmentWaterDto;
import com.myjava.housingapartment.web.model.HousingApartmentDto;



class ApartmentMapperTest {

	
	ApartmentMapper apartmentMapper = null;
	
	private static int CHILD_COUNT=4;
	
	
	@BeforeEach
	void setUp() throws Exception {
		apartmentMapper = new ApartmentMapper();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test 
	void testApartmentObjecToDto() {
		
		HousingApartmentDto actualApartmentDto = mockApartmentDto();
		HousingApartment apartmentObj = apartmentMapper.mapDtoToObject(actualApartmentDto);
		assertNotNull(apartmentObj);
		assertEquals(actualApartmentDto.getApartmentUUID(),apartmentObj.getApartmentUUID());
		assertEquals(CHILD_COUNT,apartmentObj.getApartmentElectricitys().size());
		assertEquals(CHILD_COUNT,apartmentObj.getApartmentWaters().size());
		
	}
	
	@Test
	void testApartmentDtoToObject() {
		HousingApartment actualApartment = mockApartment();
		
		HousingApartmentDto apartmentDto = apartmentMapper.mapObjectToDto(actualApartment);
		assertNotNull(apartmentDto);
		assertEquals(actualApartment.getApartmentUUID(),apartmentDto.getApartmentUUID());
		assertEquals(CHILD_COUNT,apartmentDto.getApartmentElectricityDtos().size());
		assertEquals(CHILD_COUNT,apartmentDto.getApartmentWaterDtos().size());
		
	}
	
	/////////////////
	// Mock apartment
	/////////////////
	
	private HousingApartment mockApartment() {
		HousingApartment apartment = HousingApartment.builder()
				.apartmentUUID(UUID.randomUUID())
				.cooperativeUUID(UUID.randomUUID())
				.userUUID(UUID.randomUUID())
				.apartment("3B")
				.apartmentWaters(mockApartmentWaters())
				.apartmentElectricitys(mockApartmentElectrisityes())
				.build();
		return apartment;
	}
	
	private HousingApartmentDto mockApartmentDto() {
		HousingApartmentDto housingApartmentDto = HousingApartmentDto.builder()
				.apartmentUUID(UUID.randomUUID())
				.cooperativeUUID(UUID.randomUUID())
				.userUUID(UUID.randomUUID())
				.apartment("3B")
				.apartmentWaterDtos(mockApartmentWaterDtos())
				.apartmentElectricityDtos(apartmentElectricityDtos())
				
				.build();
		return housingApartmentDto;
	}

	/////////////////
	// Mock apartment water
	/////////////////
	
	private ApartmentWater mockApartmentWater() {
		
		ApartmentWater apartmentWater = ApartmentWater.builder()
				.id(UUID.randomUUID())
				//.apartment(mockApartment())
				.couldWater(4321.0)
				.hotWater(1234.0)
				.measurementDate(new Timestamp(System.currentTimeMillis()))
				.build();
		
		return apartmentWater;
	}
	
	private List<ApartmentWater> mockApartmentWaters(){
		
		List<ApartmentWater> waters = new ArrayList<>();
		
		for (int i= 0; i<CHILD_COUNT;i++) {
			waters.add(mockApartmentWater());
		}
		return waters;
		
	}
	
	private List<ApartmentWaterDto> mockApartmentWaterDtos(){
		
		List<ApartmentWaterDto> waterDtos = new ArrayList<>();
		
		for (int i= 0; i<CHILD_COUNT;i++) {
			waterDtos.add(mockApartmentWaterDto());
		}
		return waterDtos;
		
	}
	
	private ApartmentWaterDto mockApartmentWaterDto() {
		
		ApartmentWaterDto apartmentWaterDto = ApartmentWaterDto.builder()
				.id(UUID.randomUUID())
				//.apartment(mockApartmentDto())
				.couldWater(4321.0)
				.hotWater(1234.0)
				.measurementDate(OffsetDateTime.now())
				.build();
		
		return apartmentWaterDto;
	}

	/////////////////
	// Mock apartment electricity
	/////////////////
	
	private List<ApartmentElectricity> mockApartmentElectrisityes(){
		List<ApartmentElectricity> electriciyes = new ArrayList<>();
		
		for (int i= 0; i<CHILD_COUNT;i++) {
			electriciyes.add(mockApartmentElectricity());
		}
		return electriciyes;
	}
	
	
	private ApartmentElectricity mockApartmentElectricity() {
		
		ApartmentElectricity electricity = ApartmentElectricity.builder()
				.id(UUID.randomUUID())
				//.apartment(mockApartment())
				.measurement(1234.0)
				.measurementDate(new Timestamp(System.currentTimeMillis()))
				.build();
		
		return electricity;
	}
	
	private ApartmentElectricityDto mockApartmentElectricityDto() {
		
		ApartmentElectricityDto electricityDto = ApartmentElectricityDto.builder()
				.id(UUID.randomUUID())
				//.apartment(mockApartment())
				.measurement(234.12)
				.measurementDate(OffsetDateTime.now())
				.build();
		
		return electricityDto;
	}
	
	private List<ApartmentElectricityDto> apartmentElectricityDtos(){
		List<ApartmentElectricityDto> electrisityDtos = new ArrayList<>();
		
		for (int i= 0; i<CHILD_COUNT;i++) {
			electrisityDtos.add(mockApartmentElectricityDto());
		}
		return electrisityDtos;
		
	}
}
