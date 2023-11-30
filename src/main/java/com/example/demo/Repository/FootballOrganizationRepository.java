package com.example.demo.Repository;

import com.example.demo.models.FootballOrganization;


import java.util.List;

public interface FootballOrganizationRepository {
    FootballOrganization read(Long id);

    List<FootballOrganization> readAll();

    void Create(FootballOrganization footballOrganization);
}
