package com.myjava.housingapartment.web.service;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjava.housingapartment.domain.ApartmentElectricity;
import com.myjava.housingapartment.exception.ResourceNotFoundException;
import com.myjava.housingapartment.repositories.ApartmentElectricityRepository;
import com.myjava.housingapartment.repositories.ElectricityRepository;
import com.myjava.housingapartment.repositories.HousingApartmentRepository;
import com.myjava.housingapartment.web.mappers.ApartmentMapper;
import com.myjava.housingapartment.web.model.ApartmentElectricityDto;
import com.myjava.housingapartment.web.model.HousingApartmentDto;

import lombok.extern.slf4j.Slf4j;
 

@Slf4j
@Service
public class ApartmentElectricityServiceImpl implements ApartmentElectricityService{

	@Autowired
	ApartmentMapper mapper;
	
	@Autowired
	HousingApartmentRepository apartmentRepository;
	
	@Autowired
	ApartmentElectricityRepository apartmentElectricityRepository;
	
	@Autowired
	ElectricityRepository electricityRepository;
	
	@Override
	public ApartmentElectricityDto addApartmentElectricity(UUID apartmentUUID, ApartmentElectricityDto electricityDto) {
		
		
		electricityDto.setHc_apartment(mapper.mapObjectToDto(apartmentRepository.findById(apartmentUUID).orElseThrow()));
		
		return mapper.mapElectricityObjectToDto(
				apartmentElectricityRepository.save(
				mapper.mapElectricityDtoToObject(electricityDto)));
		
	}

	/***
	 * Find all electricity measurement for the apartment
	 */
	@Override
	public List<ApartmentElectricityDto> getApartmentElecricity(UUID apartmentUUID ) {
		
		HousingApartmentDto apartmentDto = mapper.mapObjectToDto(apartmentRepository.findById(apartmentUUID).orElseThrow());
		
		if (apartmentDto != null) {
			List<ApartmentElectricity> apE = apartmentElectricityRepository.findByApartment(mapper.mapDtoToObject(apartmentDto));
			List<ApartmentElectricityDto> apEDto = new ArrayList<>();
			
			for(ApartmentElectricity e : apE) {
				ApartmentElectricityDto dto = mapper.mapElectricityObjectToDto(e); 
				apEDto.add(dto);
			}
			return apEDto;
		}
		else {
			log.error("Apartment not found with id: " + apartmentUUID );
			throw new ResourceNotFoundException(" Apartment" + apartmentUUID + " not found");
		}
	}

	@Override
	public Boolean deleteApartmentElectricity(UUID electricityUUID) {
		
		
		try {
			electricityRepository.deleteById(electricityUUID);
			return true;
		} catch (Exception e) {
			log.error("Error occured while deleleting apartment electricity entity. " + e.getMessage());
			return false;
		}
	}

	@Override
	public ApartmentElectricityDto updateApartmentElectricity(UUID apartmentUUID, UUID electricityId,
			ApartmentElectricityDto apartmentElectricity) {
		
		if(!apartmentRepository.existsById(apartmentUUID)) {
            throw new ResourceNotFoundException("Apartment " + apartmentUUID + " not found");
        }
		
		
		return mapper.mapElectricityObjectToDto(electricityRepository.findById(apartmentUUID).map(electricity -> {
			electricity.setMeasurement(apartmentElectricity.getMeasurement());
            return electricityRepository.save(electricity);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + electricityId + "not found")));

	}

}
