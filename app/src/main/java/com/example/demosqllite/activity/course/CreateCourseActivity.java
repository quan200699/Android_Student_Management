package com.example.demosqllite.activity.course;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demosqllite.R;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.impl.CourseDao;

import static com.example.demosqllite.config.StaticVariable.*;

public class CreateCourseActivity extends AppCompatActivity {
    private EditText editTextName;
    private ICourseDao courseDao;
    private Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        init();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
                Intent intent = new Intent(CreateCourseActivity.this, ListCourseActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        editTextName = findViewById(R.id.editTextCourseName);
        courseDao = new CourseDao(this);
        buttonSave = findViewById(R.id.buttonSave);
        courseDao = new CourseDao(this);
    }

    private void createStudent() {
        String name = editTextName.getText().toString();
        Course course = new Course(name);
        course = courseDao.insert(course);
        if (course != null) {
            Toast.makeText(this, MESSAGE_CREATE_SUCCESS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
        }
        resetField();
    }

    private void resetField() {
        editTextName.setText("");
    }
}
