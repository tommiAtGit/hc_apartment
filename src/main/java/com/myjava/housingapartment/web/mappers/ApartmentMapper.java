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
	
	
	public ApartmentWater mapWaterDtoToObject(ApartmentWaterDto dto){
		
			ApartmentWater apartmentWater = ApartmentWater.builder()
					.id(dto.getId())
					.couldWater(dto.getCouldWater())
					.hotWater(dto.getHotWater())
					.measurementDate(dateMapper.asTimestamp(dto.getMeasurementDate()))
					.build();
		
		return apartmentWater;
	}
	
	public ApartmentWaterDto mapWaterObjectToDto(ApartmentWater waterObjs){
		
			ApartmentWaterDto apartmentWaterDto = ApartmentWaterDto.builder()
					.id(waterObjs.getId())
					.couldWater(waterObjs.getCouldWater())
					.hotWater(waterObjs.getHotWater())
					.measurementDate(dateMapper.asOffsetDateTime(waterObjs.getMeasurementDate()))
					.build();
		return apartmentWaterDto;
	}
	
	public ApartmentElectricity mapElectricityDtoToObject(ApartmentElectricityDto dto){
		
		
			return ApartmentElectricity.builder()
					.id(dto.getId())
					.measurement(dto.getMeasurement())
					.measurementDate(dateMapper.asTimestamp(dto.getMeasurementDate()))
					.build();
	}
	
	public ApartmentElectricityDto mapElectricityObjectToDto(ApartmentElectricity obj){
		
			return ApartmentElectricityDto.builder()
					.id(obj.getId())
					.measurement(obj.getMeasurement())
					.measurementDate(dateMapper.asOffsetDateTime(obj.getMeasurementDate()))
					.build();
			
		
	}
}
