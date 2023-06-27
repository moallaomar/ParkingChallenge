package com.example.parkingchallenge.clients;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReverseGeoCodingClientTest {

    @Autowired
    private ReverseGeoCodingClient reverseGeoCodingClient;

    @Test
    public void shouldReturnJson() {
        String jsonLocation = reverseGeoCodingClient.getCityFromLatLong("json", "43.670539", "7.120827");

        Assertions.assertThat(jsonLocation).isNotNull();
    }

}
