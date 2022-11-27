package com.myjava.housingapartment.web.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import com.myjava.housingapartment.exception.ResourceNotFoundException;
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
	void getCooperativeApartmentsTest(){
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
    @Test
    void updateApartmetTest(){
        HousingApartmentDto apartment_1 = lib.mockApartmentDto();
        HousingApartmentDto apartment_2 = lib.mockApartmentDto();

        HousingApartmentDto a1 = service.addHousingAparment(apartment_1);
        HousingApartmentDto a2 = service.addHousingAparment(apartment_2);

        
        a2.setApartment("M1");
        service.updateApartmet(a2.getApartmentUUID(), a2);
        

        HousingApartmentDto actualApratment = service.getHousingApartment(a2.getApartmentUUID());
        assertNotNull(actualApratment);
        assertEquals(a2.getApartment(), actualApratment.getApartment());

        service.deleteHousingApartment(a1);
        service.deleteHousingApartment(a2);

    }

    @Test
    void updateApartmetUnknownApartmentTest(){
        HousingApartmentDto apartment_1 = lib.mockApartmentDto();
        
        apartment_1.setApartment("M1");
        try {
            service.updateApartmet(apartment_1.getApartmentUUID(), apartment_1);    
        } catch (ResourceNotFoundException e) {
            assertTrue(true);
        }

    }
    @Test
    void updateApartmetApartmentNullTest(){
        HousingApartmentDto apartment_1 = lib.mockApartmentDto();
        HousingApartmentDto a1 = service.addHousingAparment(apartment_1);

        a1.setApartment("M1");
        try {
            service.updateApartmet(a1.getApartmentUUID(), null);
            assertFalse(false);    
        } catch (NullPointerException e) {
            service.deleteHousingApartment(a1);
            assertTrue(true);
        }
        

    }

}
