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
        long result = sqLiteDatabase.insert(StaticVariable.TABLE_COURSE, null, contentValues);
        if (result == -1) {
            return null;
        }
        return course;
    }

    @Override
    public Course findById(int id) {
        Course course = new Course();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(StaticVariable.SELECT_COURSE + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(StaticVariable.NAME));
            course.setId(id);
            course.setName(name);
        }
        cursor.close();
        return course;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery(StaticVariable.SELECT_ALL_COURSES, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex(StaticVariable.ID));
            String name = res.getString(res.getColumnIndex(StaticVariable.NAME));
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
        int result = sqLiteDatabase.delete(StaticVariable.TABLE_COURSE, "id = ?", arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, Course course) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StaticVariable.NAME, course.getName());
        String[] arguments = new String[]{id + ""};
        int result = sqLiteDatabase.update(StaticVariable.TABLE_COURSE, contentValues, "id = ?", arguments);
        return result != 0;
    }
}
