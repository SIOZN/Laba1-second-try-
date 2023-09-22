package com.example.demo.models;

import java.util.Date;

public record Organization(
        Long Id,
        Long Car_ID,
        Integer Cost,
        Date Rent
) {
}
