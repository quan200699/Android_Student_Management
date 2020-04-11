package com.example.demosqllite.config;

public class StaticVariable {
    public static final String DATABASE_NAME = "student_management";
    public static final String TABLE_STUDENT = "students";
    public static final String TABLE_COURSE = "courses";
    public static final int DATABASE_Version = 1;
    public static final String ID = "id";
    public static final String COURSE_ID = "course_id";
    public static final String NAME = "name";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String EMAIL = "email";
    public static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ,"
            + PHONE_NUMBER + " VARCHAR(225) ," + EMAIL + " VARCHAR(225) ," + COURSE_ID + " INTEGER);";
    public static final String CREATE_TABLE_COURSE = "CREATE TABLE " + TABLE_COURSE +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255));";
    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM " + TABLE_STUDENT;
    public static final String SELECT_ALL_STUDENTS_BY_COURSE = "SELECT * FROM " + TABLE_STUDENT + " WHERE " + COURSE_ID + " =";
    public static final String SELECT_STUDENT_BY_ID = "SELECT * FROM " + TABLE_STUDENT + " WHERE ID=";
    public static final String DROP_TABLE_STUDENT = "DROP TABLE IF EXISTS " + TABLE_STUDENT;
    public static final String SELECT_ALL_COURSES = "SELECT * FROM " + TABLE_COURSE;
    public static final String SELECT_COURSE_BY_ID = "SELECT * FROM " + TABLE_COURSE + " WHERE ID=";
    public static final String SELECT_COURSE_BY_NAME = "SELECT * FROM " + TABLE_COURSE + " WHERE NAME = ";
    public static final String DROP_TABLE_COURSE = "DROP TABLE IF EXISTS " + TABLE_COURSE;
    public static final String MESSAGE_CREATE_SUCCESS = "Tạo mới thành công";
    public static final String MESSAGE_DELETE_SUCCESS = "Xóa thành công";
    public static final String MESSAGE_UPDATE_SUCCESS = "Cập nhật thành công";
    public static final String MESSAGE_FAIL = "Có lỗi xảy ra";
}
