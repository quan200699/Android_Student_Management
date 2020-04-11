package com.example.demosqllite.activity.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.demosqllite.R;
import com.example.demosqllite.activity.MainActivity;
import com.example.demosqllite.adapter.ListStudentAdapter;
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

import java.util.List;

public class ListStudentActivity extends AppCompatActivity {
    private ListView listViewStudent;
    private IStudentDao studentDao;
    private ImageButton buttonBack;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        init();
        final List<Student> students = studentDao.findAll();
        ListStudentAdapter adapter = new ListStudentAdapter(ListStudentActivity.this, R.layout.activity_student_row, students);
        listViewStudent.setAdapter(adapter);
        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListStudentActivity.this, StudentActivity.class);
                intent.putExtra("studentId", students.get(position).getId());
                intent.putExtra("studentName", students.get(position).getName());
                intent.putExtra("studentPhoneNumber", students.get(position).getPhoneNumber());
                intent.putExtra("studentEmail", students.get(position).getEmail());
                intent.putExtra("studentCourse", students.get(position).getCourseId());
                startActivity(intent);
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListStudentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        onClickEvent(buttonCreate, CreateStudentActivity.class);
    }

    private void init() {
        listViewStudent = findViewById(R.id.listViewStudent);
        studentDao = new StudentDao(this);
        buttonBack = findViewById(R.id.buttonBack);
        buttonCreate = findViewById(R.id.buttonCreateStudent);
    }

    private void onClickEvent(Button button, final Class<?> activity) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListStudentActivity.this, activity);
                startActivity(intent);
            }
        });
    }
}
