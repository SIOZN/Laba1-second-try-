package com.example.demo.controllers;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Car;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;



@RestController
@RequestMapping("api/cars")
public class CarController {
    private final List<Car> cars;

    public CarController(List<Car> carList) {

            Car n1 = new Car(1l, "KIA","passenger",2012,true);
            Car n2 = new Car(2l, "LADA","sedan",2020,false);

            cars = List.of(n1, n2);

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
