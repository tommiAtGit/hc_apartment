package com.myjava.housingapartment.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjava.housingapartment.domain.ApartmentWater;
import com.myjava.housingapartment.exception.ResourceNotFoundException;
import com.myjava.housingapartment.repositories.ApartmentWaterRepository;
import com.myjava.housingapartment.repositories.HousingApartmentRepository;
import com.myjava.housingapartment.repositories.WaterRepository;
import com.myjava.housingapartment.web.mappers.ApartmentMapper;
import com.myjava.housingapartment.web.model.ApartmentWaterDto;
import com.myjava.housingapartment.web.model.HousingApartmentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApartmentWaterServiceImpl implements ApartmentWaterService{

	@Autowired
	ApartmentMapper mapper;
	
	@Autowired
	HousingApartmentRepository apartmentRepository;
	
	@Autowired
	ApartmentWaterRepository apartmenWaterRepository;
	
	@Autowired
	WaterRepository waterRepository;
	
	@Override
	public ApartmentWaterDto addApartmentWater(UUID apartmentUUID, ApartmentWaterDto waterDto) {
		
		waterDto.setHc_apartment(mapper.mapObjectToDto(apartmentRepository.findById(apartmentUUID).orElseThrow()));
		
		return mapper.mapWaterObjectToDto(
				apartmenWaterRepository.save(
						mapper.mapWaterDtoToObject(waterDto)));
				
	}

	/**
	 * Find all water measurements for the apartment
	 */
	@Override
	public List<ApartmentWaterDto> getApartmentWater(UUID apartmentUUID) {
		
		HousingApartmentDto apartmentDto = mapper.mapObjectToDto(apartmentRepository.findById(apartmentUUID).orElseThrow());
		
		if (apartmentDto != null) {
			List<ApartmentWater> aW = apartmenWaterRepository.findByApartment(
					mapper.mapDtoToObject(apartmentDto));
			
			List<ApartmentWaterDto>aWDto = new ArrayList<>();
			
			for(ApartmentWater w:aW) {
				ApartmentWaterDto dto = mapper.mapWaterObjectToDto(w);
				aWDto.add(dto);
			}
			
			return aWDto;
					
		}
		else {
			log.error("Apartment not found with id: " + apartmentUUID );
			throw new ResourceNotFoundException(" Apartment" + apartmentUUID + " not found");
		}
		
	}

	@Override
	public Boolean deleteApartmentWater(UUID waterUUID) {
		
		try {
			waterRepository.deleteById(waterUUID);
			return true;
		} catch (Exception e) {
			log.error("Error occured while deleleting apartment electricity entity. " + e.getMessage());
			return false;
		}
		
	}

	@Override
	public ApartmentWaterDto updateApartmetWater(UUID apartmentUUID, UUID waterUUID, ApartmentWaterDto waterDto) {
		if(!apartmentRepository.existsById(apartmentUUID)) {
            throw new ResourceNotFoundException("Apartment " + apartmentUUID + " not found");
        }
		
		return mapper.mapWaterObjectToDto(waterRepository.findById(apartmentUUID).map(water -> {
			water.setCouldWater(waterDto.getCouldWater());
			water.setHotWater(waterDto.getHotWater());
            return waterRepository.save(water);
        }).orElseThrow(() -> new ResourceNotFoundException("water with id " + waterUUID + "not found")));
		
	}

}
