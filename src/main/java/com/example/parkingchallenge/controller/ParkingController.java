package com.example.parkingchallenge.controller;


import com.example.parkingchallenge.dto.Parking;
import com.example.parkingchallenge.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/parkings")
@RestController
public class ParkingController {

        @Autowired
        private ParkingService parkingService;

    @GetMapping
    public ResponseEntity getNearestParking(@RequestParam  String longitude , @RequestParam String  latitude){
      List<Parking> parkings  =   parkingService.getAllNearestParking(Double.valueOf(longitude),Double.valueOf(latitude));
        if(parkings.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(parkings);
    }


}
