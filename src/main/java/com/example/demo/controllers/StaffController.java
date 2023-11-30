package com.example.demo.controllers;


import com.example.demo.exceprion.NotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Staff;
import com.example.demo.Repository.StaffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.demo.models.UserPost.*;


@RestController

@RequestMapping("api/staffs")
public class StaffController {
    private final List<Staff> staffs;

    public StaffController(List<Staff> staffList) {

        try {
        String strDate1 = "14.10.2003";
        String strDate2 = "28.02.2003";
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = formatter.parse(strDate1);
        Date date2 = formatter.parse(strDate2);

            Staff n1 = new Staff(1L,"Alex","Nemkov","Anatolyevich",
                true, date1, DIRECTOR, 45000);
            Staff n2 = new Staff(2L,"Danil","Maslov","Borisovich",
                    true, date2, PLAYER, 34000);

        staffs = new ArrayList<>(List.of(n1,n2));
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

    @DeleteMapping("/{staffId}")
    public void deleteStaff(@PathVariable("staffId") Long staffId) {
        staffs.remove(getStaff(staffId));
    }

    @PostMapping("add/{staffId}")
    public Staff addStaff(@RequestBody Staff newStaffs) {
        staffs.add(newStaffs);
        return newStaffs;
    }

    @PutMapping("/{staffId}")
    public void putStaff(@PathVariable("staffId") Long staffId, @RequestBody Staff newStaffs) {
        staffs.remove(getStaff(staffId));
        if (newStaffs != null) {
            staffs.add(newStaffs);

        }
    }

    //@PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    //public void create(@RequestBody Staff staff) {
      //  StaffRepository.create(staff);
    //}

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }



}
