package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Car;


import java.util.List;



@RestController

@RequestMapping("api/cars")
public class CarController {
    private final List<Car> cars;

    public CarController(List<Car> carList) {

            Car n1 = new Car(1L, "KIA","passenger",2012,true);
            Car n2 = new Car(2L, "LADA","sedan",2020,false);
            Car n3 = new Car(3L, "HONDA","passenger",2009,true);
            cars = List.of(n1, n2, n3);

    }
    @GetMapping
    public List<Car> getCars() {
        return cars;
    }

    @GetMapping("/{car_id}")
    public Car getCar(@PathVariable("car_id") Long carId) {
        return cars.stream()
                .filter(car -> car.Id().equals(carId))
                .findAny()
                .orElse(null);
    }
}
