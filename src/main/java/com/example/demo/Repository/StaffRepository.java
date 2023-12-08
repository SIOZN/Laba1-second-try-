package com.example.demo.Repository;

import com.example.demo.models.Staff;

import java.util.List;

public interface StaffRepository {

    Staff read(Long id);

    List<Staff> readAll();

    void create(Staff staff);

    void updateStaff(Staff staff, Integer staffId);

    void deleteStaff(Integer staffId);
}