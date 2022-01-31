package com.myjava.housingapartment.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.myjava.housingapartment.domain.ApartmentWater;
import com.myjava.housingapartment.web.model.ApartmentWaterDto;

@Mapper(uses= {DateMapper.class})
public interface ApartmentWaterMapper {

	@Mapping(source = "measurementDate", target = "measurementDate")
	ApartmentWaterDto ApartmentWaterToDto(ApartmentWater apartmentWater);
	@Mapping(source = "measurementDate", target = "measurementDate")
	ApartmentWater DtoToApartmentWater(ApartmentWaterDto apartmentWaterxDto);
	

}
