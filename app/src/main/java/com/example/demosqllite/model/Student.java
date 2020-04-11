package com.example.demosqllite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private int courseId;

    public Student(String name, String phoneNumber, String email, int courseId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.courseId = courseId;
    }
}
