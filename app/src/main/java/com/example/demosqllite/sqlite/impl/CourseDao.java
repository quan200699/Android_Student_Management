package com.example.demosqllite.sqlite.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demosqllite.config.StaticVariable;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class CourseDao implements ICourseDao {
    private DBHelper dbHelper;

    public CourseDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Course insert(Course course) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StaticVariable.NAME, course.getName());
        long result = sqLiteDatabase.insert(StaticVariable.CREATE_TABLE_COURSE, null, contentValues);
        if (result == -1) {
            return null;
        }
        return course;
    }

    @Override
    public Course findById(int id) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(StaticVariable.SELECT_ALL_COURSES, null);
        cursor.moveToFirst();
        while (!cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(StaticVariable.ID));
            String name = cursor.getString(cursor.getColumnIndex(StaticVariable.NAME));
            courses.add(new Course(id, name));
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public boolean updateById(int id, Course course) {
        return false;
    }
}