package com.example.demosqllite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.demosqllite.R;
import com.example.demosqllite.adapter.ListStudentAdapter;
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listView = findViewById(R.id.listViewStudent);
        IStudentDao studentDao = new StudentDao(this);
        List<Student> students = studentDao.findAll();
        ListStudentAdapter adapter = new ListStudentAdapter(ListActivity.this, R.layout.activity_student, students);
        listView.setAdapter(adapter);
    }
}
