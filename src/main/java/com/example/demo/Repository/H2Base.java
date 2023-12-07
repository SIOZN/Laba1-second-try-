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

import java.sql.Types;
import java.util.List;



@Repository
public class H2Base implements StaffRepository {

    private static final String CREATE = """
                        INSERT INTO STAFF (Id, Name, Surname, Patronymic,Gender,Birth,Post,Salary)
                        values (:id, :name, :surname, :patronymic, :gender, :birth, CAST(:post AS ENUM('DIRECTOR', 'CLEANER', 'PLAYER', 'MANAGER')), :salary)
                        """;

    private static final String UPDATE = """ 
            UPDATE STAFF SET
            ID = :Id,
            NAME = :name,
            SURNAME = :surname,
            PATRONYMIC = :patronymic,
            GENDER = :gender,
            BIRTH = :birth,
            POST = :post,
            SALARY = :salary
            WHERE ID = :Id
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
        paramsSource.registerSqlType("post", Types.INTEGER);
        namedParameterJdbcTemplate.update(CREATE, paramsSource);
    }
    
    @Override
    public void updateStaff(Staff staff, Integer staffId){
        BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(staff);
        namedParameterJdbcTemplate.update(UPDATE, paramsSource);
    }

    @Override
    public void deleteStaff(Integer staffId) {
            jdbcTemplate.update("DELETE FROM STAFF WHERE Id = ?", staffId);

    }
}