package com.example.parkingchallenge.services;

import com.example.parkingchallenge.dto.Parking;

import java.util.List;

public interface ParkingService {


    List<Parking> getAllNearestParking(Double longitude , Double latitude);


}
