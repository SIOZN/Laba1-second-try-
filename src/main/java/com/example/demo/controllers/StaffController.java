package com.example.demo.controllers;


import com.example.demo.exceprion.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Staff;
import com.example.demo.Repository.StaffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.text.ParseException;
import java.util.List;



@RestController

@RequestMapping("api/staffs")
public class StaffController {

    private final StaffRepository staffRepository;


    @Autowired
    public StaffController(StaffRepository staffRepository) {
     this.staffRepository = staffRepository;

    }
    @GetMapping
    public List<Staff> getStaffs() {
        return staffRepository.readAll();
    }

    @GetMapping("/{staffId}")
    public Staff getStaff(@PathVariable("staffId") Long staffId) {
        return staffRepository.read(staffId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Staff staff) {
        staffRepository.create(staff);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{staff_id}")
    public void updateStaff(@RequestBody Staff staff, @PathVariable("staff_id") int staffId) {
        staffRepository.updateStaff(staff, staffId);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{staff_id}")
    public void deleteMember(@PathVariable("staff_id") int staffId) {
        staffRepository.deleteStaff(staffId);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }



}
