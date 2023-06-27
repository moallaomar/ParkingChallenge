package com.example.parkingchallenge.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.net.URI;

@FeignClient(name = "parking-client", url = "feignUrl")
public interface ParkingClient {

    @GetMapping
    String getAllParkingsWithQueryParams(URI uri, @SpringQueryMap @ModelAttribute Object object);

    @GetMapping
    String getAllParkingsWithoutQueryParams(URI uri);
}

