package com.myjava.housingapartment.web.service;

import java.util.UUID;
import java.util.List;

import com.myjava.housingapartment.web.model.HousingApartmentDto;

public interface ApartmentService {

	HousingApartmentDto addHousingAparment(HousingApartmentDto apatmentDto);
	HousingApartmentDto getHousingApartment(UUID apartmentUUID);
	List<HousingApartmentDto> getHousingApartments();
	HousingApartmentDto getHousingApartmentByName(String apartmentName);
	void deleteHousingApartment(HousingApartmentDto apartmentDto);
	List<HousingApartmentDto> getCooperativeApartments(UUID cooperativeUUID);
	HousingApartmentDto updateApartmet(UUID apartmentUUID, HousingApartmentDto apartmentDto);
	
	
	
	
	
	
	
	
	
	
}
