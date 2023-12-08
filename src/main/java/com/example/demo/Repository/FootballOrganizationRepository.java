package com.example.demo.Repository;

import com.example.demo.models.FootballOrganization;



import java.util.List;

public interface FootballOrganizationRepository {
    FootballOrganization read(Long id);

    List<FootballOrganization> readAll();

    void createOrg(FootballOrganization footballOrganization);

    void updateFootballOrganizations(FootballOrganization footballOrganization, Integer FootballOrganizationId);

    void deleteFootballOrganizations(Integer FootballOrganizationId);
}
