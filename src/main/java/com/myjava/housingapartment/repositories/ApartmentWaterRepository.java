package com.myjava.housingapartment.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.myjava.housingapartment.domain.ApartmentWater;

public interface ApartmentWaterRepository extends CrudRepository<ApartmentWater, UUID> {

}
