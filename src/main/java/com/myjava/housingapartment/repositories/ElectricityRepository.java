package com.myjava.housingapartment.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.myjava.housingapartment.domain.ApartmentElectricity;


public interface ElectricityRepository extends CrudRepository<ApartmentElectricity, UUID> {
	
	Optional<ApartmentElectricity> findById(UUID id);
	
}
