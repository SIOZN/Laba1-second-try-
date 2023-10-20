package com.example.demo.controllers;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.FootballOrganization;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/cars/{car_id}/footballOrganizations")
public class FootballOrganizationController {


    private final List<FootballOrganization> footballOrganizationList;

    public FootballOrganizationController() {
        try {
            String strDate1 = "14.10.2023";
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = formatter.parse(strDate1);

            String strDate2 = "21.08.2023";
            Date date2 = formatter.parse(strDate2);

            footballOrganizationList = List.of(new FootballOrganization(1L, "Chealse", "Premeir League", 1L));


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public List<FootballOrganization> getFootballOrganization(@PathVariable("staffId") Long Car_ID) {
        return footballOrganizationList.stream()
                .filter(staff -> staff.Id().equals(Car_ID))
                .toList();
    }



}
