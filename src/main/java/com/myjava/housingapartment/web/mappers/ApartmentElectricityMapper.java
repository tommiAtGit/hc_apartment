package com.myjava.housingapartment.web.mappers;

import org.mapstruct.Mapper;

import com.myjava.housingapartment.domain.ApartmentElectricity;
import com.myjava.housingapartment.web.model.ApartmentElectricityDto;

@Mapper(uses= {DateMapper.class})
public interface ApartmentElectricityMapper {

	ApartmentElectricityDto ApartmentElectricityToDto(ApartmentElectricity apartmentElectricity);
	ApartmentElectricity DtoToApartmentElectricity(ApartmentElectricityDto apartmentElectricityDto);
	
}
