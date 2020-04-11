package com.example.demosqllite.activity.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demosqllite.R;
import com.example.demosqllite.config.StaticVariable;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.CourseDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class CreateStudentActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private EditText editTextEmail;
    private Spinner spinnerCourse;
    private IStudentDao studentDao;
    private ICourseDao courseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonBack = findViewById(R.id.buttonBack);
        spinnerCourse = findViewById(R.id.spinnerCourse);
        studentDao = new StudentDao(this);
        courseDao = new CourseDao(this);
        List<Course> courses = getAllCourses();
        List<String> courseNames = addCourseNameToList(courses);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courseNames);
        spinnerCourse.setAdapter(adapter);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStudent();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateStudentActivity.this, ListStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<String> addCourseNameToList(List<Course> courses) {
        List<String> courseNames = new ArrayList<>();
        courseNames.add("");
        for (Course course : courses) {
            courseNames.add(course.getName());
        }
        return courseNames;
    }

    private List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    private void createStudent() {
        String name = editTextName.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String email = editTextEmail.getText().toString();
        String courseName = spinnerCourse.getSelectedItem().toString();
        Student student = new Student(name, phoneNumber, email);
        Course course = courseDao.findByName(courseName);
        if (course != null) {
            student = new Student(name, phoneNumber, email, course.getId());
        }
        student = studentDao.insert(student);
        if (student != null) {
            Toast.makeText(this, StaticVariable.MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, StaticVariable.MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
        resetField();
    }

    private void resetField() {
        editTextName.setText("");
        editTextPhoneNumber.setText("");
        editTextEmail.setText("");
    }
}
