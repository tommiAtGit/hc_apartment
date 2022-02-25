package com.myjava.housingapartment.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myjava.housingapartment.domain.ApartmentWater;
import com.myjava.housingapartment.domain.HousingApartment;

public interface ApartmentWaterRepository extends CrudRepository<ApartmentWater, HousingApartment> {
		List<ApartmentWater> findByApartment(HousingApartment apartment);
		
}
