package com.myjava.housingapartment.web.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.myjava.housingapartment.repositories.ApartmentElectricityRepository;
import com.myjava.housingapartment.repositories.HousingApartmentRepository;
import com.myjava.housingapartment.web.mappers.ApartmentMapper;
import com.myjava.housingapartment.web.model.ApartmentElectricityDto;

public class ApartmentElectricityServiceImpl implements ApartmentElectricityService{

	@Autowired
	ApartmentMapper mapper;
	
	@Autowired
	HousingApartmentRepository apartmentRepository;
	
	@Autowired
	ApartmentElectricityRepository electricityRepository;
	
	@Override
	public ApartmentElectricityDto addApartmentElectricity(UUID apartmentUUID, ApartmentElectricityDto electricityDto) {
		
		electricityDto.setHc_apartment(mapper.mapObjectToDto(apartmentRepository.findById(apartmentUUID).orElseThrow()));
		
		return mapper.mapElectricityObjectToDto(
				electricityRepository.save(
				mapper.mapElectricityDtoToObject(electricityDto)));
		
	}

	@Override
	public ApartmentElectricityDto updateApartmentElectricity(ApartmentElectricityDto apartmentElectricity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApartmentElectricityDto> getApartmentElecricity(UUID apartmentUUID) {
		
		
		return null;
	}

	@Override
	public void deleteApartmentElectricity(UUID electricityUUID) {
		// TODO Auto-generated method stub
		
	}

}
