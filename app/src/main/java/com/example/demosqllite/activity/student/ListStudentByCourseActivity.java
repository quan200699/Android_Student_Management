package com.example.demosqllite.activity.student;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demosqllite.R;
import com.example.demosqllite.activity.course.CourseActivity;
import com.example.demosqllite.adapter.ListStudentAdapter;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

import java.util.List;

public class ListStudentByCourseActivity extends AppCompatActivity {
    private ListView listViewStudent;
    private IStudentDao studentDao;
    private TextView textViewListStudent;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student_by_course);
        init();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            course = getCourseInfo(bundle);
            String title = textViewListStudent.getText().toString() + " " + course.getName();
            textViewListStudent.setText(title);
            final List<Student> students = studentDao.findAllByCourse(course.getId());
            ListStudentAdapter adapter = new ListStudentAdapter(ListStudentByCourseActivity.this, R.layout.activity_student_row, students);
            listViewStudent.setAdapter(adapter);
            listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ListStudentByCourseActivity.this, StudentActivity.class);
                    intent.putExtra("studentId", students.get(position).getId());
                    intent.putExtra("studentName", students.get(position).getName());
                    intent.putExtra("studentPhoneNumber", students.get(position).getPhoneNumber());
                    intent.putExtra("studentEmail", students.get(position).getEmail());
                    intent.putExtra("studentCourse", students.get(position).getCourseId());
                    startActivity(intent);
                }
            });
        }
    }

    private Course getCourseInfo(Bundle bundle) {
        int courseId = bundle.getInt("courseId");
        String courseName = bundle.getString("courseName");
        return new Course(courseId, courseName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ListStudentByCourseActivity.this, CourseActivity.class);
                intent.putExtra("courseId", course.getId());
                intent.putExtra("courseName", course.getName());
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        listViewStudent = findViewById(R.id.listViewStudent);
        studentDao = new StudentDao(this);
        textViewListStudent = findViewById(R.id.textViewListStudent);
    }
}
