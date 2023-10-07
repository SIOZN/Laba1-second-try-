package com.example.demo.models;

public record Car(Long Id,
                  String Brand_Name, // make it enum
                  String Type, // ues camel-case for variable names
                  Integer Year,
                  Boolean Is_Mech

                  // add others date types

) {}
