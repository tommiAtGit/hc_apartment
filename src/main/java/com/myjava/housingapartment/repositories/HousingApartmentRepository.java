package com.myjava.housingapartment.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.myjava.housingapartment.domain.HousingApartment;


public interface HousingApartmentRepository  extends CrudRepository<HousingApartment, UUID> {

}
