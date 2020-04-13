package com.example.demosqllite.activity.course;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.demosqllite.R;
import com.example.demosqllite.activity.MainActivity;
import com.example.demosqllite.adapter.ListCourseAdapter;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.impl.CourseDao;

import java.util.List;

public class ListCourseActivity extends AppCompatActivity {
    private ListView listViewCourse;
    private ICourseDao courseDao;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);
        init();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        final List<Course> courses = courseDao.findAll();
        ListCourseAdapter adapter = new ListCourseAdapter(ListCourseActivity.this, R.layout.activity_course_row, courses);
        listViewCourse.setAdapter(adapter);
        listViewCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListCourseActivity.this, CourseActivity.class);
                intent.putExtra("courseId", courses.get(position).getId());
                intent.putExtra("courseName", courses.get(position).getName());
                startActivity(intent);
            }
        });
        onClickEvent(buttonCreate, CreateCourseActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ListCourseActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        listViewCourse = findViewById(R.id.listViewCourse);
        courseDao = new CourseDao(this);
        buttonCreate = findViewById(R.id.buttonCreateCourse);
    }

    private void onClickEvent(Button button, final Class<?> activity) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCourseActivity.this, activity);
                startActivity(intent);
            }
        });
    }
}
