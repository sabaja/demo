package com.kafka.boot.prj.cars.tdd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarsController {

    @GetMapping("/cars/{name}")
    public Car getCars(@PathVariable String name){
        return null;
    }
}
