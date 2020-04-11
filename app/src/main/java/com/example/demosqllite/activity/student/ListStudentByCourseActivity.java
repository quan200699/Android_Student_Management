package com.example.demosqllite.activity.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demosqllite.R;
import com.example.demosqllite.activity.course.CourseActivity;
import com.example.demosqllite.adapter.ListStudentAdapter;
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

import java.util.List;

public class ListStudentByCourseActivity extends AppCompatActivity {
    private ListView listViewStudent;
    private IStudentDao studentDao;
    private ImageButton buttonBack;
    private TextView textViewListStudent;
    private Button buttonAddStudentToCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student_by_course);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final int courseId = bundle.getInt("courseId");
            final String courseName = bundle.getString("courseName");
            String title = textViewListStudent.getText().toString() + " " + courseName;
            textViewListStudent.setText(title);
            final List<Student> students = studentDao.findAllByCourse(courseId);
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
            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListStudentByCourseActivity.this, CourseActivity.class);
                    intent.putExtra("courseId",courseId);
                    intent.putExtra("courseName",courseName);
                    startActivity(intent);
                }
            });
        }
    }

    private void init() {
        listViewStudent = findViewById(R.id.listViewStudent);
        studentDao = new StudentDao(this);
        buttonBack = findViewById(R.id.buttonBack);
        textViewListStudent = findViewById(R.id.textViewListStudent);
        buttonAddStudentToCourse = findViewById(R.id.buttonCreateStudent);
    }
}
