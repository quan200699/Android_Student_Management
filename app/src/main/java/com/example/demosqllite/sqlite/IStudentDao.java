package com.example.demosqllite.sqlite;

import com.example.demosqllite.model.Student;

import java.util.List;

public interface IStudentDao extends IGeneralDao<Student> {
    List<Student> findAllByCourse(int courseId);
}
