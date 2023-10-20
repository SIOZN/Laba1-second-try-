package com.example.demo.controllers;


import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Staff;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.demo.models.UserPost.director;


@RestController

@RequestMapping("api/staffs")
public class StaffController {
    private final List<Staff> staffs;

    public StaffController(List<Staff> staffList) {

        try {
        String strDate1 = "14.10.2023";
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = formatter.parse(strDate1);

            Staff n1 = new Staff(1L,"Alex","Nemkov","Anatolyevich",
                true, date1, director, 45000);

        staffs = List.of(n1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping
    public List<Staff> getStaffs() {
        return staffs;
    }

    @GetMapping("/{staffId}")
    public Staff getStaff(@PathVariable("staff_id") Long staffId) {
        return staffs.stream()
                .filter(staff -> staff.Id().equals(staffId))
                .findAny()
                .orElse(null);
    }

    @DeleteMapping("/delete/{staffId}")
    public void deleteStaff(@PathVariable("staff_id") Long staffId) {
        staffs.removeIf(staff -> staff.Id().equals(staffId));
    }

}
