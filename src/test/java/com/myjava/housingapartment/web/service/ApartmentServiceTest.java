package com.myjava.housingapartment.web.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import com.myjava.housingapartment.test.utils.ApartmentMockLibrary;
import com.myjava.housingapartment.web.model.HousingApartmentDto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ApartmentServiceTest {
    
    @Autowired
    ApartmentService service;

    ApartmentMockLibrary lib = null;

    @BeforeEach
	void setUp() throws Exception {
		
		lib = new ApartmentMockLibrary(); 
	}

	@AfterEach
	void tearDown() throws Exception {

	}
    @Test
	void getCooperativeApartments(){
        Integer cooperApartmentCnt = 2;
        UUID cooperativeUUID = UUID.randomUUID();
        HousingApartmentDto apartment_1 = lib.mockApartmentDto();
        HousingApartmentDto apartment_2 = lib.mockApartmentDto();
        HousingApartmentDto apartment_3 = lib.mockApartmentDto();

        apartment_1.setCooperativeUUID(cooperativeUUID);
        apartment_2.setCooperativeUUID(cooperativeUUID);

        HousingApartmentDto a1 = service.addHousingAparment(apartment_1);
        HousingApartmentDto a2 = service.addHousingAparment(apartment_2);
        HousingApartmentDto a3 = service.addHousingAparment(apartment_3);

        List<HousingApartmentDto> coopAprtments = service.getCooperativeApartments(cooperativeUUID);
        assertNotNull(coopAprtments);
        assertTrue(cooperApartmentCnt == coopAprtments.size());

        service.deleteHousingApartment(a1);
        service.deleteHousingApartment(a2);
        service.deleteHousingApartment(a3);




    }

}
