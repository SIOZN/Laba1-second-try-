package com.example.demo.Repository;


import com.example.demo.exceprion.NotFoundException;
import com.example.demo.models.Staff;
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
public class H2Base implements StaffRepository {

    private static final String CREATE = """
                        insert into staff (Id, Name, Surname, Patronymic,Gender,Birth,userPost,Salary)
                        values (:Id, :Name, :Surname, :Patronymic, :Gender, :Birth, :userPost, :Salary)
            """;

    private final RowMapper<Staff> rowMapper = new DataClassRowMapper<>(Staff.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public H2Base(JdbcTemplate jdbcTemplate,
                  NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Staff read(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from staff where Id = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Staff with id = [" + id + "] not found", e);
        }
    }

    @Override
    public List<Staff> readAll() {
        return jdbcTemplate.query("select * from staff", rowMapper);
    }

    @Override
    public void create(Staff staff) {
        BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(staff);
        namedParameterJdbcTemplate.update(CREATE, paramsSource);
    }
}