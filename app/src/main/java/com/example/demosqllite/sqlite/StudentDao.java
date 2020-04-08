package com.example.demosqllite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.demosqllite.Student;
import com.example.demosqllite.config.StaticVariable;

import java.util.List;

public class StudentDao implements IStudentDao {
    private DBHelper dbHelper;

    public StudentDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Student insert(Student student) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StaticVariable.NAME, student.getName());
        contentValues.put(StaticVariable.PHONE_NUMBER, student.getPhoneNumber());
        contentValues.put(StaticVariable.EMAIL, student.getEmail());
        long result = sqLiteDatabase.insert(StaticVariable.TABLE_NAME, null, contentValues);
        if (result == -1) {
            return null;
        }
        return student;
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public boolean updateById(Student student) {
        return false;
    }
}
