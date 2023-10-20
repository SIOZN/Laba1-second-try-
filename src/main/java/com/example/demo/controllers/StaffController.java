package com.example.demo.controllers;


import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Staff;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.demo.models.UserPost.*;


@RestController

@RequestMapping("api/staffs")
public class StaffController {
    private final List<Staff> staffs;

    public StaffController(List<Staff> staffList) {

        try {
        String strDate1 = "14.10.2023";
        String strDate2 = "28.02.2023";
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = formatter.parse(strDate1);
        Date date2 = formatter.parse(strDate2);

            Staff n1 = new Staff(1L,"Alex","Nemkov","Anatolyevich",
                true, date1, DIRECTOR, 45000);
            Staff n2 = new Staff(2L,"Danil","Maslov","Borisovich",
                    true, date2, PLAYER, 34000);

        staffs = List.of(n1,n2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping
    public List<Staff> getStaffs() {
        return staffs;
    }

    @GetMapping("/{staffId}")
    public Staff getStaff(@PathVariable("staffId") Long staffId) {
        return staffs.stream()
                .filter(staff -> staff.Id() == staffId)
                .findAny()
                .orElse(null);
    }

    @DeleteMapping("/del/{staffId}")
    public void deleteStaff(@PathVariable("staffId") Long staffId) {
        staffs.remove(getStaff(staffId));
    }

}
