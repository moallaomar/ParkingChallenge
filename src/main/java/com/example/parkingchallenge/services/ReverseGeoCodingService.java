package com.example.parkingchallenge.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ReverseGeoCodingService {


    String getTownOrCityFromOpenStreetMap(String latitude, String longitude) ;
}
