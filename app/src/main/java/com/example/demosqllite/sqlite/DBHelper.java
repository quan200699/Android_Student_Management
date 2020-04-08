package com.example.demosqllite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.demosqllite.config.StaticVariable.CREATE_TABLE;
import static com.example.demosqllite.config.StaticVariable.DATABASE_NAME;
import static com.example.demosqllite.config.StaticVariable.DATABASE_Version;
import static com.example.demosqllite.config.StaticVariable.DROP_TABLE;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Log.e("TEXT", "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Log.e("TEXT", "" + e);
        }
    }
}
