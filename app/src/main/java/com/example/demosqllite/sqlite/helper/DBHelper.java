package com.example.demosqllite.sqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.demosqllite.config.StaticVariable.*;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_STUDENT);
            db.execSQL(CREATE_TABLE_COURSE);
        } catch (Exception e) {
            Log.e("TEXT", "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE_STUDENT);
            db.execSQL(DROP_TABLE_COURSE);
            onCreate(db);
        } catch (Exception e) {
            Log.e("TEXT", "" + e);
        }
    }
}
