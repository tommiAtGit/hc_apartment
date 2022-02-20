package com.myjava.housingapartment.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.myjava.housingapartment.domain.ApartmentElectricity;
import com.myjava.housingapartment.domain.HousingApartment;

public interface ApartmentElectricityRepository extends CrudRepository<ApartmentElectricity, HousingApartment> {
	List<ApartmentElectricity> findByApartment(HousingApartment apartment);
}

