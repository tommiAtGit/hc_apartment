package com.myjava.housingapartment.web.mappers;

import org.mapstruct.Mapper;

import com.myjava.housingapartment.domain.HousingApartment;
import com.myjava.housingapartment.web.model.HousingApartmentDto;



@Mapper
public interface ApartmentMapper {

	HousingApartmentDto HousingApartmentToDto(HousingApartment housingApartment);
	
	HousingApartment DtoToHousingApartment(HousingApartmentDto housingApartmentDto);
	
	
}
