package com.myjava.housingapartment.web.mappers;



import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.myjava.housingapartment.domain.ApartmentElectricity;
import com.myjava.housingapartment.domain.ApartmentWater;
import com.myjava.housingapartment.domain.HousingApartment;
import com.myjava.housingapartment.web.model.ApartmentElectricityDto;
import com.myjava.housingapartment.web.model.ApartmentWaterDto;
import com.myjava.housingapartment.web.model.HousingApartmentDto;


@Component
public class ApartmentMapper {
	
	private DateMapper dateMapper = null;
	
	public ApartmentMapper(){
		dateMapper = new DateMapper();
	}
	
	public HousingApartment mapDtoToObject(HousingApartmentDto dto) {
		
		HousingApartment apartment = HousingApartment.builder()
				.apartmentUUID(dto.getApartmentUUID())
				.cooperativeUUID(dto.getCooperativeUUID())
				.userUUID(dto.getUserUUID())
				.apartment(dto.getApartment())
				.build();
		return apartment;
	}
	
	public HousingApartmentDto mapObjectToDto(HousingApartment obj) {
		
		HousingApartmentDto housingApartmentDto = HousingApartmentDto.builder()
				.apartmentUUID(obj.getApartmentUUID())
				.cooperativeUUID(obj.getCooperativeUUID())
				.userUUID(obj.getUserUUID())
				.apartment(obj.getApartment())
				.build();
		return housingApartmentDto;
	}
	
	private List<ApartmentWater> mapWaterDtoToObject(List<ApartmentWaterDto> dtos){
		
		List<ApartmentWater> waters = new ArrayList<>();
		
		for (ApartmentWaterDto dto : dtos) {
			ApartmentWater apartmentWater = ApartmentWater.builder()
					.id(dto.getId())
					.couldWater(dto.getCouldWater())
					.hotWater(dto.getHotWater())
					.measurementDate(dateMapper.asTimestamp(dto.getMeasurementDate()))
					.build();
			waters.add(apartmentWater);
		}
		return waters;
	}
	
	private List<ApartmentWaterDto> mapWaterObjectToDto(List<ApartmentWater> waterObjs){
		
		List<ApartmentWaterDto> waterDtos = new ArrayList<>();
		
		for (ApartmentWater obj : waterObjs) {
			ApartmentWaterDto apartmentWaterDto = ApartmentWaterDto.builder()
					.id(obj.getId())
					.couldWater(obj.getCouldWater())
					.hotWater(obj.getHotWater())
					.measurementDate(dateMapper.asOffsetDateTime(obj.getMeasurementDate()))
					.build();
					waterDtos.add(apartmentWaterDto);
		}
		return waterDtos;
	}
	
	private List<ApartmentElectricity>mapElectricityDtoToObject(List<ApartmentElectricityDto> dtos){
		List<ApartmentElectricity> electrisities = new ArrayList<>();
		for (ApartmentElectricityDto dto:dtos) {
			
			ApartmentElectricity electricity = ApartmentElectricity.builder()
					.id(dto.getId())
					.measurement(dto.getMeasurement())
					.measurementDate(dateMapper.asTimestamp(dto.getMeasurementDate()))
					.build();
			electrisities.add(electricity);
		}
		return electrisities;
	}
	
	private List<ApartmentElectricityDto>mapElectricityObjectToDto(List<ApartmentElectricity> objs){
		List<ApartmentElectricityDto> electrisityDtos = new ArrayList<>();
		
		for (ApartmentElectricity obj:objs) {
			
			ApartmentElectricityDto electricityDto = ApartmentElectricityDto.builder()
					.id(obj.getId())
					.measurement(obj.getMeasurement())
					.measurementDate(dateMapper.asOffsetDateTime(obj.getMeasurementDate()))
					.build();
			electrisityDtos.add(electricityDto);
		}
		return electrisityDtos;
	}
}
