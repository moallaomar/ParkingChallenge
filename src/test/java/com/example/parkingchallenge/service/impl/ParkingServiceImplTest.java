package com.example.parkingchallenge.service.impl;

import com.example.parkingchallenge.config.H2JpaConfig;
import com.example.parkingchallenge.dto.Parking;
import com.example.parkingchallenge.services.ParkingService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {H2JpaConfig.class})
@Sql("/initDB.sql")
public class ParkingServiceImplTest {

    @Autowired
    private ParkingService parkingService;


    @Test
    public void shouldReturnParkingList() {
        List<Parking> parkingList = parkingService.getAllNearestParking(0.345002261647649, 46.58349874703973);
        Assertions.assertThat(parkingList).isNotEmpty();
    }
}
