package com.myjava.housingapartment.web.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentWaterDto {

	private UUID id;
	
	//private HousingApartmentDto apartment;
	
	private Double couldWater;
	
	private Double hotWater;
	
	private OffsetDateTime measurementDate;
}
