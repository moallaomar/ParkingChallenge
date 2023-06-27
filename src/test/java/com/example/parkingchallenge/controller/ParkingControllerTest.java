package com.example.parkingchallenge.controller;

import com.example.parkingchallenge.config.H2JpaConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {H2JpaConfig.class})
@Sql("/initDB.sql")
@AutoConfigureMockMvc
public class ParkingControllerTest {


    @Autowired
    private MockMvc mvc;


    @Test
    public void shouldReturnDistanceof0MeterParkingNotreDame() throws Exception {
        mvc.perform(get("/parkings?longitude=0.345002261647649&latitude=46.58349874703973")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

    }
}
