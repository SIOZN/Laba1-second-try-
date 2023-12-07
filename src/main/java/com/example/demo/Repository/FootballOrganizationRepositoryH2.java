package com.example.demo.Repository;

import com.example.demo.exceprion.NotFoundException;
import com.example.demo.models.FootballOrganization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FootballOrganizationRepositoryH2 implements FootballOrganizationRepository {
    private static final String CREATE = """
                        insert into footballorganization (Id, clubName, League, Staff_id)
                        values (:id, :clubName, :league, :staffId)
            """;


    private static final String UPDATE = """
            UPDATE footballorganization SET
            ID = :Id,
            clubName = :clubName,
            LEAGUE = :league,
            staff_id = :staffId
            WHERE ID = :Id
            """;
    private final RowMapper<FootballOrganization> rowMapper = new DataClassRowMapper<>(FootballOrganization.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FootballOrganizationRepositoryH2(JdbcTemplate jdbcTemplate,
                            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public FootballOrganization read(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from footballOrganization where id = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("footballOrganization with id = [" + id + "] not found", e);
        }
    }

    @Override
    public List<FootballOrganization> readAll() {
        return jdbcTemplate.query("select * from footballOrganization", rowMapper);
    }

    @Override
    public void createOrg(FootballOrganization footballOrganization) {
        BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(footballOrganization);
        namedParameterJdbcTemplate.update(CREATE, paramsSource);
    }

    @Override
    public void updateFootballOrganizations(FootballOrganization footballOrganization, Integer FootballOrganizationId){
        BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(footballOrganization);
        namedParameterJdbcTemplate.update(UPDATE, paramsSource);
    }

    @Override
    public void deleteFootballOrganizations(Integer FootballOrganizationId) {
        jdbcTemplate.update("DELETE FROM footballOrganizations WHERE Id = ?", FootballOrganizationId);

    }


}
