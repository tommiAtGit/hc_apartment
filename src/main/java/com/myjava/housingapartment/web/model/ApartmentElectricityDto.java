package com.myjava.housingapartment.web.model;

import java.time.OffsetDateTime;
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
public class ApartmentElectricityDto {

	private UUID id;
	
	private HousingApartment apartment;
	
	private Double measurement;
	
	private OffsetDateTime measurementDate;
	
}
