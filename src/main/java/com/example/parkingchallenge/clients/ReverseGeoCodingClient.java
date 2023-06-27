package com.example.parkingchallenge.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reverseGeoCodingClient", url = "https://nominatim.openstreetmap.org")
public interface ReverseGeoCodingClient {


    @GetMapping("/reverse")
    String getCityFromLatLong(@RequestParam String format, @RequestParam String lat, @RequestParam String lon);
}
