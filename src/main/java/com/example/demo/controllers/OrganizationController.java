package com.example.demo.controllers;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Organization;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/cars/{car_id}/organizations")
public class OrganizationController {


    private final List<Organization> organizationsList;

    public OrganizationController() {
        try {
            String strDate1 = "14.10.2023";
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = formatter.parse(strDate1);

            String strDate2 = "21.08.2023";
            Date date2 = formatter.parse(strDate2);

            organizationsList = List.of(new Organization(1L, 1L, 3000000, date1),
                    new Organization(2L, 2L, 2500000, date2));


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public List<Organization> getOrganization(@PathVariable("car_id") Long Car_ID) {
        return organizationsList.stream()
                .filter(phoneNumber -> phoneNumber.Car_ID().equals(Car_ID))
                .toList();
    }



}
