package com.myjava.housingapartment.web.service;

import java.util.List;
import java.util.UUID;

import com.myjava.housingapartment.web.model.ApartmentElectricityDto;

public interface ApartmentElectricityService {

	ApartmentElectricityDto addApartmentElectricity(UUID apartmentUUID, ApartmentElectricityDto electricityDto);
	ApartmentElectricityDto updateApartmentElectricity(ApartmentElectricityDto apartmentElectricity);
	List<ApartmentElectricityDto> getApartmentElecricity(UUID apartmentUUID);
	void deleteApartmentElectricity(UUID electricityUUID);
}
