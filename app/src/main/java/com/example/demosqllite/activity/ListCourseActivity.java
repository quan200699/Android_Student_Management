package com.example.demosqllite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.demosqllite.R;
import com.example.demosqllite.adapter.ListCourseAdapter;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.impl.CourseDao;

import java.util.List;

public class ListCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);
        ListView listView = findViewById(R.id.listViewCourse);
        ICourseDao courseDao = new CourseDao(this);
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        Button buttonCreate = findViewById(R.id.buttonCreateCourse);
        final List<Course> courses = courseDao.findAll();
        ListCourseAdapter adapter = new ListCourseAdapter(ListCourseActivity.this, R.layout.activity_course_row, courses);
        listView.setAdapter(adapter);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCourseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
