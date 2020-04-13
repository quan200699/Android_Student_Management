package com.example.demosqllite.activity.student;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demosqllite.R;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.CourseDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

import java.util.ArrayList;
import java.util.List;

import static com.example.demosqllite.config.StaticVariable.*;

public class CreateStudentActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private EditText editTextEmail;
    private Spinner spinnerCourse;
    private IStudentDao studentDao;
    private ICourseDao courseDao;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        init();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CreateStudentActivity.this, ListStudentActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSave = findViewById(R.id.buttonSave);
        spinnerCourse = findViewById(R.id.spinnerCourse);
        studentDao = new StudentDao(this);
        courseDao = new CourseDao(this);
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
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
        resetField();
    }

    private void resetField() {
        editTextName.setText("");
        editTextPhoneNumber.setText("");
        editTextEmail.setText("");
    }
}
