package com.example.parkingchallenge.service.impl;

import com.example.parkingchallenge.exceptions.InvalidGeoCodeException;
import com.example.parkingchallenge.services.ReverseGeoCodingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReverseGeoCodingServiceImplTest {

    @Autowired
    private ReverseGeoCodingService reverseGeoCodingService;


    /**
     * Test for Paris City
     * @throws JsonProcessingException
     */
    @Test
    public void shouldReturnParisCity()  {
       String townOrCity = reverseGeoCodingService.getTownOrCityFromOpenStreetMap("48.856614","2.3522219");
        Assertions.assertEquals(townOrCity ,"Paris");
    }

    @Test
    public void shouldReturnCagnesSurMerTown() {
        String townOrCity = reverseGeoCodingService.getTownOrCityFromOpenStreetMap("43.670539","7.120827");
        Assertions.assertEquals(townOrCity ,"Cagnes-sur-Mer");
    }

    @Test
    public void shouldReturnPoitiers()  {
        String townOrCity = reverseGeoCodingService.getTownOrCityFromOpenStreetMap("46.573098","0.343387");
        Assertions.assertEquals(townOrCity ,"Poitiers");
    }
    @Test
    public void shouldFailOnRuntimeException()  {
        try {
          reverseGeoCodingService.getTownOrCityFromOpenStreetMap("46.845243", "-3.010110");
        } catch (InvalidGeoCodeException e) {
            Assertions.assertEquals("Merci de choisir une géolocalisation d'une ville ou  d'une commune", e.getMessage());
        }
    }
}
