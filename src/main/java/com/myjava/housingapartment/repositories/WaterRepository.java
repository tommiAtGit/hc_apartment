package com.myjava.housingapartment.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.myjava.housingapartment.domain.ApartmentWater;

public interface WaterRepository extends CrudRepository<ApartmentWater, UUID> {

	 Optional<ApartmentWater> findById(UUID id); 
}
