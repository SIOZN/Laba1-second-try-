package com.example.demo.controllers;
import com.example.demo.models.Staff;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.FootballOrganization;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/footballOrganizations")
public class FootballOrganizationController {


    private final List<FootballOrganization> footballOrganizationList;

    public FootballOrganizationController() {
            footballOrganizationList = new ArrayList<>(List.of(new FootballOrganization(1L, "Chelsea", "Premeir League",
                    1L)));

    }
    @GetMapping
    public List<FootballOrganization> getFootballOrganization() {
        return footballOrganizationList;
    }



    @GetMapping("/{FootballOrganizationId}")
    public FootballOrganization getFootballOrganization(@PathVariable("FootballOrganizationId") Long FootballOrganizationId) {
        return footballOrganizationList.stream()
                .filter(staff -> staff.Id() == FootballOrganizationId)
                .findAny()
                .orElse(null);
    }
    @DeleteMapping("/{footballOrganizationId}")
    public void deleteFootballOrganization(@PathVariable("footballOrganizationId") Long footballOrganizationId) {
        footballOrganizationList.remove(getFootballOrganization(footballOrganizationId));
    }

    @PostMapping("/add/{footballOrganizationId}")
    public void createFootballOrganization(@PathVariable("footballOrganizationId") Long footballOrganizationId,
                                           @RequestBody FootballOrganization footballOrganization) {
        if (footballOrganization != null) {
            footballOrganizationList.add(footballOrganization);
        }
    }

    @PutMapping("/{footballOrganizationId}")
    public void putFootballOrganization(@PathVariable("footballOrganizationId") Long footballOrganizationId, @RequestBody FootballOrganization newfootballOrganizations) {
        footballOrganizationList.remove(getFootballOrganization(footballOrganizationId));

        if (newfootballOrganizations != null) {
            footballOrganizationList.add(newfootballOrganizations);
        }
    }
}
