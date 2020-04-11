package com.example.demosqllite.sqlite.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demosqllite.model.Course;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.demosqllite.config.StaticVariable.*;

public class CourseDao implements ICourseDao {
    private DBHelper dbHelper;

    public CourseDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Course insert(Course course) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, course.getName());
        long result = sqLiteDatabase.insert(TABLE_COURSE, null, contentValues);
        if (result == -1) {
            return null;
        }
        return course;
    }

    @Override
    public Course findById(int id) {
        Course course = new Course();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_COURSE_BY_ID + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            course.setId(id);
            course.setName(name);
            cursor.moveToNext();
        }
        cursor.close();
        return course;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery(SELECT_ALL_COURSES, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex(ID));
            String name = res.getString(res.getColumnIndex(NAME));
            courses.add(new Course(id, name));
            res.moveToNext();
        }
        res.close();
        return courses;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        int result = sqLiteDatabase.delete(TABLE_COURSE, "id = ?", arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, Course course) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, course.getName());
        String[] arguments = new String[]{id + ""};
        int result = sqLiteDatabase.update(TABLE_COURSE, contentValues, "id = ?", arguments);
        return result != 0;
    }

    @Override
    public Course findByName(String name) {
        Course course = new Course();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_COURSE_BY_NAME);
        query.append("'").append(name).append("'");
        Cursor cursor = sqLiteDatabase.rawQuery(String.valueOf(query), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            course.setId(id);
            course.setName(name);
            cursor.moveToNext();
        }
        cursor.close();
        return course;
    }
}
