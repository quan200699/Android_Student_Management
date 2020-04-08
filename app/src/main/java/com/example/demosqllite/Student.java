package com.example.demosqllite;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    public Student(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
