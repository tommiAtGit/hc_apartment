package com.myjava.housingapartment.web.service;

import java.util.List;
import java.util.UUID;

import com.myjava.housingapartment.web.model.ApartmentWaterDto;

public interface ApartmentWaterService {

	ApartmentWaterDto addApartmentWater(UUID apartmentUUID, ApartmentWaterDto waterDto);
	List<ApartmentWaterDto> getApartmentWater(UUID apartmentUUID);
	Boolean deleteApartmentWater(UUID waterUUID);
	ApartmentWaterDto updateApartmetWater(UUID waterUUDI, ApartmentWaterDto waterDto);
	
}
