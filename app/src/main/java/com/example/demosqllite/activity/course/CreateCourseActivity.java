package com.example.demosqllite.activity.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demosqllite.R;
import com.example.demosqllite.config.StaticVariable;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.impl.CourseDao;

public class CreateCourseActivity extends AppCompatActivity {
    private EditText editTextName;
    private ICourseDao courseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        editTextName = findViewById(R.id.editTextCourseName);
        courseDao = new CourseDao(this);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonBack = findViewById(R.id.buttonBack);
        courseDao = new CourseDao(this);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStudent();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCourseActivity.this, ListCourseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createStudent() {
        String name = editTextName.getText().toString();
        Course course = new Course(name);
        course = courseDao.insert(course);
        if (course != null) {
            Toast.makeText(this, StaticVariable.MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, StaticVariable.MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
        resetField();
    }

    private void resetField() {
        editTextName.setText("");
    }
}
