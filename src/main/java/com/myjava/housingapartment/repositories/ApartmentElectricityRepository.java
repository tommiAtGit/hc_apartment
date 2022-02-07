package com.myjava.housingapartment.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.myjava.housingapartment.domain.ApartmentElectricity;

public interface ApartmentElectricityRepository extends CrudRepository<ApartmentElectricity, UUID> {
	
}
