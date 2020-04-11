package com.example.demosqllite.sqlite;

import com.example.demosqllite.model.Course;

public interface ICourseDao extends IGeneralDao<Course> {
    Course findByName(String name);
}
