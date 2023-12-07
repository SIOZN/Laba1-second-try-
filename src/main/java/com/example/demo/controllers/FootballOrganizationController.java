package com.example.demo.controllers;
import com.example.demo.Repository.FootballOrganizationRepositoryH2;
import com.example.demo.exceprion.NotFoundException;
import com.example.demo.models.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final FootballOrganizationRepositoryH2 FootballOrgH2;

    @Autowired
    public FootballOrganizationController(FootballOrganizationRepositoryH2 FootballOrgH2) {
        this.FootballOrgH2 = FootballOrgH2;
    }
    @GetMapping
    public List<FootballOrganization> getFootballOrganization() {
        return FootballOrgH2.readAll();
    }



    @GetMapping("/{FootballOrganizationId}")
    public FootballOrganization getFootballOrganization(@PathVariable("FootballOrganizationId") Long FootballOrganizationId) {
        return FootballOrgH2.read(FootballOrganizationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody FootballOrganization footballOrganization) {
        FootballOrgH2.createOrg(footballOrganization);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{FootballOrganizationId}")
    public void updateFootballOrganization(@RequestBody FootballOrganization footballOrganization, @PathVariable("FootballOrganizationId") int FootballOrganizationId) {
        FootballOrgH2.updateFootballOrganizations(footballOrganization, FootballOrganizationId);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{FootballOrganizationId}")
    public void deleteFootballOrganizations(@PathVariable("FootballOrganizationId") int FootballOrganizationId) {
        FootballOrgH2.deleteFootballOrganizations(FootballOrganizationId);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
