package com.example.demo.Repository;


import com.example.demo.exceprion.NotFoundException;
import com.example.demo.exceprion.NotUniquePrimaryKeyException;
import com.example.demo.models.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
public class StaffRepositoryH2 implements StaffRepository {

    private static final String CREATE = """
                        INSERT INTO STAFF (Id, Name, Surname, Patronymic,Gender,Birth,Post,Salary)
                        values (:id, :name, :surname, :patronymic, :gender, :birth, :post , :salary)
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
    public StaffRepositoryH2(JdbcTemplate jdbcTemplate,
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
        try {
            BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(staff);
            paramsSource.registerSqlType("post", Types.NVARCHAR);
            namedParameterJdbcTemplate.update(CREATE, paramsSource);
        }
        catch (DuplicateKeyException e){
            throw new NotUniquePrimaryKeyException("Человек с таким id уже существует" + staff.Id(), e);

        }
    }
    
    @Override
    public void updateStaff(Staff staff, Integer staffId){
            BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(staff);
            paramsSource.registerSqlType("post", Types.NVARCHAR);
            namedParameterJdbcTemplate.update(UPDATE, paramsSource);

    }

    @Override
    public void deleteStaff(Integer staffId) {
            jdbcTemplate.update("DELETE FROM STAFF WHERE Id = ?", staffId);

    }
}