package com.myjava.housingapartment.web.service;

import java.util.List;
import java.util.UUID;

import com.myjava.housingapartment.web.model.ApartmentElectricityDto;
import com.myjava.housingapartment.web.model.ApartmentWaterDto;

public interface ApartmentWaterService {

	ApartmentWaterDto addApartmentWater(ApartmentWaterDto waterDto);
	List<ApartmentWaterDto> getApartmentWater(UUID apartmentUUID);
	void deleteApartmentWater(UUID waterUUID);
	ApartmentElectricityDto updateApartmetWater(ApartmentWaterDto apartmentWater);
	
}
