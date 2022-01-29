package com.myjava.housingapartment.web.model;

import java.util.Date;
import java.util.UUID;

import com.myjava.housingapartment.domain.HousingApartment;

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
	
	private HousingApartment apartment;
	
	private Double couldWater;
	
	private Double hotWater;
	
	private Date measurementDate;
}
