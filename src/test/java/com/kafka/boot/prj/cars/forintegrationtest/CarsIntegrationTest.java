package com.kafka.boot.prj.cars.forintegrationtest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * SpringBootTest TDD
 * https://www.youtube.com/watch?v=s9vt6UJiHg4
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CarsIntegrationTest {

    private RestTemplate restTemplate;

    @Test
    public void restControllerTest() {
        //arrange

        //act
        restTemplate.getForEntity("/cars/prius", Cars.class);
        
        //assert

    }

}
