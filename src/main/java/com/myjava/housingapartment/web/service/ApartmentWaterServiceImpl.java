package com.myjava.housingapartment.web.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjava.housingapartment.domain.ApartmentWater;
import com.myjava.housingapartment.exception.ResourceNotFoundException;
import com.myjava.housingapartment.repositories.ApartmentWaterRepository;
import com.myjava.housingapartment.repositories.HousingApartmentRepository;
import com.myjava.housingapartment.repositories.WaterRepository;
import com.myjava.housingapartment.web.mappers.ApartmentMapper;
import com.myjava.housingapartment.web.mappers.DateMapper;
import com.myjava.housingapartment.web.model.ApartmentWaterDto;
import com.myjava.housingapartment.web.model.HousingApartmentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApartmentWaterServiceImpl implements ApartmentWaterService{

	@Autowired
	ApartmentMapper mapper;
	
	@Autowired
	DateMapper dateMapper;
	
	@Autowired
	HousingApartmentRepository apartmentRepository;
	
	@Autowired
	ApartmentWaterRepository apartmenWaterRepository;
	
	@Autowired
	WaterRepository waterRepository;
	
	@Override
	public ApartmentWaterDto addApartmentWater(UUID apartmentUUID, ApartmentWaterDto waterDto) {
		
		waterDto.setHc_apartment(mapper.mapObjectToDto(apartmentRepository.findById(apartmentUUID).orElseThrow()));
		
		if (waterDto.getCouldWater() != null) {
			return mapper.mapWaterObjectToDto(
					apartmenWaterRepository.save(
							mapper.mapWaterDtoToObject(waterDto)));	
		}
		else {
			log.error("Apartment" + apartmentUUID + " not found");
			throw new ResourceNotFoundException(" Apartment" + apartmentUUID + " not found"); 
		}
		
				
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
	public ApartmentWaterDto updateApartmetWater(UUID waterUUID, ApartmentWaterDto waterDto) {
		if(!waterRepository.existsById(waterUUID)) {
            throw new ResourceNotFoundException("Water entity: " + waterUUID + " not found");
        }
		
		return mapper.mapWaterObjectToDto(waterRepository.findById(waterUUID).map(water -> {
			water.setCouldWater(waterDto.getCouldWater());
			water.setHotWater(waterDto.getHotWater());
			water.setMeasurementDate(dateMapper.asTimestamp(waterDto.getMeasurementDate()));
            return waterRepository.save(water);
        }).orElseThrow(() -> new ResourceNotFoundException("water with id " + waterUUID + "not found")));
		
	}
	
	@Override
	public ApartmentWaterDto getHousingApartmentWaterConsumption(UUID apartmentUUID,OffsetDateTime startTime, OffsetDateTime endTime) {
		
		if (apartmentUUID.equals(null)) {
			log.error("Apartment UUID parameter missing");
			throw new IllegalArgumentException();
		}
		
		List <ApartmentWaterDto> apartmentWaterDtos = this.getApartmentWater(apartmentUUID);
		if (apartmentWaterDtos.equals(null)) {
			log.error("Apartmentnot found with UUID: " + apartmentUUID );
			throw new IllegalArgumentException();
		}
		//Get water consumption of start date 
		ApartmentWaterDto startResult = (ApartmentWaterDto) apartmentWaterDtos
				.stream()
				.filter(w -> w.getMeasurementDate() == startTime)
                .collect(Collectors.toList());
		
        
		//Get water consumption of end date 
		ApartmentWaterDto endResult = (ApartmentWaterDto) apartmentWaterDtos
				.stream()
				.filter(w -> w.getMeasurementDate() == endTime)
                .collect(Collectors.toList());
		
		// Calculate hot water delta
		double hotwaterDelta = endResult.getHotWater() - startResult.getHotWater();
		double coldWaterDelta = endResult.getCouldWater() - endResult.getCouldWater();
		
		endResult.setHotWater(hotwaterDelta);
		endResult.setCouldWater(coldWaterDelta);
		
		return endResult;
		
	}

}
