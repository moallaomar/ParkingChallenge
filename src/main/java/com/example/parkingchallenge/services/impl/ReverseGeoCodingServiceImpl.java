package com.example.parkingchallenge.services.impl;

import com.example.parkingchallenge.clients.ReverseGeoCodingClient;
import com.example.parkingchallenge.exceptions.InvalidGeoCodeException;
import com.example.parkingchallenge.services.ReverseGeoCodingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReverseGeoCodingServiceImpl implements ReverseGeoCodingService {

    @Autowired
    private ReverseGeoCodingClient reverseGeoCodingClient;

    /**
     * Récupération du nom de la ville ou de la commune à partir de la latitude et longitude en consultant OpenStreetMap
     * @param latitude
     * @param longitude
     * @return nom de la ville ou de la commune
     */
    public String getTownOrCityFromOpenStreetMap(String latitude, String longitude) {
        String jsonLocation = reverseGeoCodingClient.getCityFromLatLong("json", latitude, longitude);
        return getTownOrCityFromJsonResponse(jsonLocation);
    }

    /**
     * Récupération du nom de la ville ou de la commune à partir du json réponse d'OpenStreetMap
     * @param jsonLocation
     * @return nom de la ville ou de la commune
     */
    private String getTownOrCityFromJsonResponse(String jsonLocation) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        if(jsonLocation.contains("error")){
            throw new InvalidGeoCodeException("Merci de choisir une géolocalisation d'une ville ou  d'une commune");
        }
        try {
            jsonNode=  objectMapper.readTree(jsonLocation).get("address");
        }catch (IOException e) {
            throw new RuntimeException("Erreur lors du parsing du json d'OpenStreetMap");
        }
        if(!jsonLocation.contains("city")){
            return jsonNode.get("town").asText();
        }
        return jsonNode.get("city").asText();
    }
}
