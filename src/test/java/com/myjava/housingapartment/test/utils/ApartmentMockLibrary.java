package com.myjava.housingapartment.test.utils;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.myjava.housingapartment.domain.ApartmentElectricity;
import com.myjava.housingapartment.domain.ApartmentWater;
import com.myjava.housingapartment.domain.HousingApartment;
import com.myjava.housingapartment.web.model.ApartmentElectricityDto;
import com.myjava.housingapartment.web.model.ApartmentWaterDto;
import com.myjava.housingapartment.web.model.HousingApartmentDto;

@Component
public class ApartmentMockLibrary {

	private static int APARTMENT_COUNT = 5;
	private static int CHILD_COUNT = 4;

/////////////////
// Mock apartment
/////////////////

	public HousingApartment mockApartment() {
		HousingApartment apartment = HousingApartment.builder()
				.apartmentUUID(UUID.randomUUID())
				.cooperativeUUID(UUID.randomUUID())
				.userUUID(UUID.randomUUID()).apartment("3B")
				.build();
		return apartment;
	}

	public HousingApartmentDto mockApartmentDto() {
		HousingApartmentDto housingApartmentDto = HousingApartmentDto.builder()
				.apartmentUUID(UUID.randomUUID())
				.cooperativeUUID(UUID.randomUUID())
				.userUUID(UUID.randomUUID())
				.apartment("3B")
				.build();
		return housingApartmentDto;
	}

	public List<HousingApartmentDto> mockApartments() {
		int i = 0;

		List<HousingApartmentDto> dtos = new ArrayList<>();
		while (i < APARTMENT_COUNT) {
			dtos.add(mockApartmentDto());
			i++;
		}

		return dtos;
	}

/////////////////
// Mock apartment water
/////////////////

	public ApartmentWater mockApartmentWater() {

		ApartmentWater apartmentWater = ApartmentWater.builder()
				.id(UUID.randomUUID())
				.couldWater(4321.0)
				.hotWater(1234.0)
				.measurementDate(new Timestamp(System.currentTimeMillis()))
				.apartment(mockApartment())
				.build();

		return apartmentWater;
	}
	
	public ApartmentWater mockApartmentWater(HousingApartment apartment) {

		ApartmentWater apartmentWater = ApartmentWater.builder()
				.id(UUID.randomUUID())
				.couldWater(4321.0)
				.hotWater(1234.0)
				.measurementDate(new Timestamp(System.currentTimeMillis()))
				.apartment(apartment)
				.build();

		return apartmentWater;
	}

	public List<ApartmentWater> mockApartmentWaters() {

		List<ApartmentWater> waters = new ArrayList<>();

		for (int i = 0; i < CHILD_COUNT; i++) {
			waters.add(mockApartmentWater());
		}
		return waters;

	}

	public List<ApartmentWaterDto> mockApartmentWaterDtos() {

		List<ApartmentWaterDto> waterDtos = new ArrayList<>();

		for (int i = 0; i < CHILD_COUNT; i++) {
			waterDtos.add(mockApartmentWaterDto());
		}
		return waterDtos;

	}

	public ApartmentWaterDto mockApartmentWaterDto() {

		ApartmentWaterDto apartmentWaterDto = ApartmentWaterDto.builder()
				.id(UUID.randomUUID())
				.couldWater(4321.0)
				.hotWater(1234.0)
				.measurementDate(OffsetDateTime.now())
				.build();

		return apartmentWaterDto;
	}

/////////////////
// Mock apartment electricity
/////////////////

	public List<ApartmentElectricity> mockApartmentElectrisityes() {
		List<ApartmentElectricity> electriciyes = new ArrayList<>();

		for (int i = 0; i < CHILD_COUNT; i++) {
			electriciyes.add(mockApartmentElectricity());
		}
		return electriciyes;
	}

	public ApartmentElectricity mockApartmentElectricity() {

		ApartmentElectricity electricity = ApartmentElectricity.builder()
				.id(UUID.randomUUID())
				.measurement(1234.0)
				.measurementDate(new Timestamp(System.currentTimeMillis()))
				.build();

		return electricity;
	}

	public ApartmentElectricityDto mockApartmentElectricityDto() {

		ApartmentElectricityDto electricityDto = ApartmentElectricityDto.builder()
				.id(UUID.randomUUID())
				.measurement(234.12)
				.measurementDate(OffsetDateTime.now())
				.build();

		return electricityDto;
	}

	public List<ApartmentElectricityDto> apartmentElectricityDtos() {
		List<ApartmentElectricityDto> electrisityDtos = new ArrayList<>();

		for (int i = 0; i < CHILD_COUNT; i++) {
			electrisityDtos.add(mockApartmentElectricityDto());
		}
		return electrisityDtos;

	}
}
