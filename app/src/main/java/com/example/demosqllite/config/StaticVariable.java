package com.example.demosqllite.config;

public class StaticVariable {
    public static final String DATABASE_NAME = "student_management";
    public static final String TABLE_NAME = "student";
    public static final int DATABASE_Version = 1;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String EMAIL = "email";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ,"
            + PHONE_NUMBER + " VARCHAR(225) ," + EMAIL + " VARCHAR(255));";
    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
    public static final String SELECT_STUDENT = "SELECT * FROM student WHERE ID=";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String MESSAGE_CREATE_SUCCESS = "Tạo mới thành công";
    public static final String MESSAGE_DELETE_SUCCESS = "Xóa thành công";
    public static final String MESSAGE_UPDATE_SUCCESS = "Cập nhật thành công";
    public static final String MESSAGE_FAIL = "Có lỗi xảy ra";
}
