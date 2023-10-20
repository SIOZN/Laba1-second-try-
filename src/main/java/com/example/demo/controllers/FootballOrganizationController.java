package com.example.demo.controllers;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.FootballOrganization;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/staffs/{staffId}/footballOrganizations")
public class FootballOrganizationController {


    private final List<FootballOrganization> footballOrganizationList;

    public FootballOrganizationController() {


            footballOrganizationList = List.of(new FootballOrganization(1L, "Chelsea", "Premeir League",
                    1L));



    }

    @GetMapping()
    public List<FootballOrganization> getFootballOrganization(@PathVariable("staffId") Long Car_ID) {
        return footballOrganizationList.stream()
                .filter(staff -> staff.Id().equals(Car_ID))
                .toList();
    }



}
