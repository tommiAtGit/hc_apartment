package com.myjava.housingapartment.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myjava.housingapartment.domain.HousingApartment;
import com.myjava.housingapartment.exception.ResourceNotFoundException;
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
	public void deleteHousingApartment(HousingApartmentDto apartmentDto) {
		
		if (apartmentDto != null) {
			repository.delete(mapper.mapDtoToObject(apartmentDto));
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

	@Override
	public List<HousingApartmentDto> getCooperativeApartments(UUID cooperativeUUID) {
		
		List<HousingApartmentDto> aptDtos = new ArrayList<HousingApartmentDto>();

		if(cooperativeUUID != null){
			List<HousingApartment> apartments = (List<HousingApartment>) repository.findAll();
			if (apartments != null){
				List<HousingApartment> coopApartments = apartments
					.stream()
					.filter(c->c.getCooperativeUUID().equals(cooperativeUUID))
					.collect(Collectors.toList());
				
				coopApartments.forEach(apt -> aptDtos.add(mapper.mapObjectToDto(apt)));	
				
				return aptDtos;
			}
		}
		return null;
	}

	@Override
	public HousingApartmentDto updateApartmet(UUID apartmentUUID, HousingApartmentDto apartmentDto) {
		if(!repository.existsById(apartmentUUID)) {
            throw new ResourceNotFoundException("Water entity: " + apartmentUUID + " not found");
        }
		
		return mapper.mapObjectToDto(repository.findById(apartmentUUID).map(apartment -> {
			apartment.setApartment(apartmentDto.getApartment());
			apartment.setApartmentUUID(apartmentDto.getApartmentUUID());
			apartment.setCooperativeUUID(apartmentDto.getCooperativeUUID());
			apartment.setUserUUID(apartmentDto.getUserUUID());
            return repository.save(apartment);
        }).orElseThrow(() -> new ResourceNotFoundException("Apartment with id " + apartmentUUID + "not found")));
		
	}
	

	
}
