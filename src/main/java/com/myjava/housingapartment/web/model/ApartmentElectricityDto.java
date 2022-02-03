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
public class ApartmentElectricityDto {

	private UUID id;
	
	private UUID apartmentUUID;
	
	private Double measurement;
	
	private OffsetDateTime measurementDate;
	
}
