package com.myjava.housingapartment.web.mappers;



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
					.apartment(this.mapDtoToObject(dto.getHc_apartment()))
					.build();
		
		return apartmentWater;
	}
	
	public ApartmentWaterDto mapWaterObjectToDto(ApartmentWater waterObj){
		
			ApartmentWaterDto apartmentWaterDto = ApartmentWaterDto.builder()
					.id(waterObj.getId())
					.couldWater(waterObj.getCouldWater())
					.hotWater(waterObj.getHotWater())
					.measurementDate(dateMapper.asOffsetDateTime(waterObj.getMeasurementDate()))
					.hc_apartment(this.mapObjectToDto(waterObj.getApartment()))
					.build();
		return apartmentWaterDto;
	}
	
	public ApartmentElectricity mapElectricityDtoToObject(ApartmentElectricityDto dto){
		
		
			return ApartmentElectricity.builder()
					.id(dto.getId())
					.measurement(dto.getMeasurement())
					.measurementDate(dateMapper.asTimestamp(dto.getMeasurementDate()))
					.apartment(this.mapDtoToObject(dto.getHc_apartment()))
					.build();
	}
	
	public ApartmentElectricityDto mapElectricityObjectToDto(ApartmentElectricity obj){
		
			return ApartmentElectricityDto.builder()
					.id(obj.getId())
					.measurement(obj.getMeasurement())
					.measurementDate(dateMapper.asOffsetDateTime(obj.getMeasurementDate()))
					.hc_apartment(this.mapObjectToDto(obj.getApartment()))
					.build();
			
		
	}
}
