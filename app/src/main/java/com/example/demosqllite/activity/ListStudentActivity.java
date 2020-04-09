package com.example.demosqllite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.demosqllite.R;
import com.example.demosqllite.adapter.ListStudentAdapter;
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

import java.util.List;

public class ListStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        ListView listView = findViewById(R.id.listViewStudent);
        IStudentDao studentDao = new StudentDao(this);
        final List<Student> students = studentDao.findAll();
        ListStudentAdapter adapter = new ListStudentAdapter(ListStudentActivity.this, R.layout.activity_student_row, students);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListStudentActivity.this, StudentActivity.class);
                intent.putExtra("studentId", students.get(position).getId());
                intent.putExtra("studentName", students.get(position).getName());
                intent.putExtra("studentPhoneNumber", students.get(position).getPhoneNumber());
                intent.putExtra("studentEmail", students.get(position).getEmail());
                startActivity(intent);
            }
        });
    }
}