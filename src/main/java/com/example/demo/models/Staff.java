package com.example.demo.models;
import java.util.Date;
public record Staff(Long Id,
                    String Name,
                    String Surname,
                    String Patronymic,
                    Boolean Gender,
                    Date Birth,
                    UserPost Post, // Enum
                    Integer Salary


                    // add others date types
)
{}


