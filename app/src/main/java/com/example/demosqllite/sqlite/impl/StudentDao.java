package com.example.demosqllite.sqlite.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.demosqllite.config.StaticVariable.*;

public class StudentDao implements IStudentDao {
    private DBHelper dbHelper;

    public StudentDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Student insert(Student student) {
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getName());
        contentValues.put(PHONE_NUMBER, student.getPhoneNumber());
        contentValues.put(EMAIL, student.getEmail());
        contentValues.put(COURSE_ID, student.getCourseId());
        long result = sqLiteDatabase.insert(TABLE_STUDENT, null, contentValues);
        if (result == -1) {
            return null;
        }
        return student;
    }

    @Override
    public Student findById(int id) {
        Student student = new Student();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_STUDENT_BY_ID + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(PHONE_NUMBER));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            int course_Id = cursor.getInt(cursor.getColumnIndex(COURSE_ID));
            student.setId(id);
            student.setName(name);
            student.setPhoneNumber(phoneNumber);
            student.setEmail(email);
            student.setCourseId(course_Id);
            cursor.moveToNext();
        }
        cursor.close();
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery(SELECT_ALL_STUDENTS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex(ID));
            String name = res.getString(res.getColumnIndex(NAME));
            String phoneNumber = res.getString(res.getColumnIndex(PHONE_NUMBER));
            String email = res.getString(res.getColumnIndex(EMAIL));
            int course_id = res.getInt(res.getColumnIndex(COURSE_ID));
            students.add(new Student(id, name, phoneNumber, email, course_id));
            res.moveToNext();
        }
        res.close();
        return students;
    }

    @Override
    public boolean removeById(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] arguments = new String[]{id + ""};
        int result = sqLiteDatabase.delete(TABLE_STUDENT, "id = ? ", arguments);
        return result != 0;
    }

    @Override
    public boolean updateById(int id, Student student) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getName());
        contentValues.put(PHONE_NUMBER, student.getPhoneNumber());
        contentValues.put(EMAIL, student.getEmail());
        contentValues.put(COURSE_ID, student.getCourseId());
        String[] arguments = new String[]{id + ""};
        int result = sqLiteDatabase.update(TABLE_STUDENT, contentValues, ID + "= ?", arguments);
        return result == 1;
    }

    @Override
    public List<Student> findAllByCourse(int courseId) {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.dbHelper.getReadableDatabase();
        StringBuilder query = new StringBuilder(SELECT_ALL_STUDENTS_BY_COURSE);
        query.append(courseId);
        Cursor res = sqLiteDatabase.rawQuery(String.valueOf(query), null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex(ID));
            String name = res.getString(res.getColumnIndex(NAME));
            String phoneNumber = res.getString(res.getColumnIndex(PHONE_NUMBER));
            String email = res.getString(res.getColumnIndex(EMAIL));
            int course_id = res.getInt(res.getColumnIndex(COURSE_ID));
            students.add(new Student(id, name, phoneNumber, email, course_id));
            res.moveToNext();
        }
        res.close();
        return students;
    }
}
