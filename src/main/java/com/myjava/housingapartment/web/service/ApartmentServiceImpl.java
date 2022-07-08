package com.myjava.housingapartment.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myjava.housingapartment.domain.HousingApartment;
import com.myjava.housingapartment.repositories.HousingApartmentRepository;
import com.myjava.housingapartment.web.mappers.ApartmentMapper;
import com.myjava.housingapartment.web.model.HousingApartmentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	ApartmentMapper mapper;
	@Autowired
	HousingApartmentRepository repository;
	
	@Autowired
	ApartmentElectricityService electricityService;
	
	@Autowired
	ApartmentWaterService waterService;
	
	@Override
	public HousingApartmentDto addHousingAparment(HousingApartmentDto apartmentDto) {
		
		
		return mapper.mapObjectToDto(repository.save(mapper.mapDtoToObject(apartmentDto)));
	}

	@Override
	public HousingApartmentDto getHousingApartment(UUID apartmentUUID) {
		return mapper.mapObjectToDto(repository.findById(apartmentUUID).orElseThrow());
	}

	@Override
	public List<HousingApartmentDto> getHousingApartments() {
		
		List<HousingApartmentDto> dtos = new ArrayList<>();
		
		log.info("Reading apartments...");
		List<HousingApartment> apartments = (List<HousingApartment>) repository.findAll();
		for(HousingApartment apartment:apartments) {
			dtos.add(mapper.mapObjectToDto(apartment));
		}
		
		return dtos;
	}


	@Override
	public void deleteHosingApartment(UUID apartmentUUID) {
		
		if (apartmentUUID != null) {
			HousingApartment apartment = repository.findById(apartmentUUID).orElseThrow();
			repository.delete(apartment);
		}
		
	}

	@Override
	public HousingApartmentDto getHousingApartmentByName(String apartmentName) {
		List<HousingApartment> apartments = (List<HousingApartment>) repository.findAll();
		
		if (apartments != null) {
			HousingApartment apartment = apartments.stream()
					.filter(h->h.getApartment().equals(apartmentName))
					.findAny()
					.orElse(null);
					
					return mapper.mapObjectToDto(
							apartment);
		}
		return null;
	}
	
	
	

	
}
