package com.kafka.boot.prj.cars.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarsController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private CarService carService;

    @Test
    public void getCar_ShouldReturnCar() throws Exception {
        given(carService
                .getCarDetails(anyString()))
                .willReturn(new Car("Prius", "Hybrid"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Prius"))
                .andExpect(jsonPath("type").value("Hybrid"));

    }
}
