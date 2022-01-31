package com.myjava.housingapartment.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HousingApartmentDto {

	private UUID apartmentUUID;
	
	private UUID cooperativeUUID;
	
	private UUID userUUID;
	
	private String apartment;
}
