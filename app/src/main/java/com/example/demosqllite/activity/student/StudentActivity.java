package com.example.demosqllite.activity.student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class StudentActivity extends AppCompatActivity {
    private EditText editTextStudentName;
    private EditText editTextStudentPhoneNumber;
    private EditText editTextStudentEmail;
    private Spinner spinnerCourse;
    private IStudentDao studentDao;
    private ICourseDao courseDao;
    private ImageButton buttonBack;
    private Button buttonDelete;
    private Button buttonEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        init();
        List<Course> courses = getAllCourses();
        List<String> courseNames = addCourseNameToList(courses);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courseNames);
        spinnerCourse.setAdapter(adapter);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editTextStudentName.setText(bundle.getString("studentName"));
            editTextStudentPhoneNumber.setText(bundle.getString("studentPhoneNumber"));
            editTextStudentEmail.setText(bundle.getString("studentEmail"));
            spinnerCourse.setSelection(getDefaultPosition(courses, bundle.getInt("studentCourse")));
            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StudentActivity.this, ListStudentActivity.class);
                    startActivity(intent);
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createPopup(bundle);
                }
            });
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = bundle.getInt("studentId");
                    String name = editTextStudentName.getText().toString();
                    String phoneNumber = editTextStudentPhoneNumber.getText().toString();
                    String email = editTextStudentEmail.getText().toString();
                    String courseName = spinnerCourse.getSelectedItem().toString();
                    Student student = new Student(name, phoneNumber, email);
                    Course course = courseDao.findByName(courseName);
                    if (course != null) {
                        student = new Student(name, phoneNumber, email, course.getId());
                    }
                    if (studentDao.updateById(id, student)) {
                        Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_UPDATE_SUCCESS, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void init() {
        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentPhoneNumber = findViewById(R.id.editTextStudentPhone);
        editTextStudentEmail = findViewById(R.id.editTextStudentEmail);
        spinnerCourse = findViewById(R.id.spinnerCourse);
        studentDao = new StudentDao(this);
        courseDao = new CourseDao(this);
        buttonBack = findViewById(R.id.buttonBack);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
    }

    private void createPopup(final Bundle bundle) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
        builder.setTitle("Xóa thông tin sinh viên");
        builder.setMessage("Bạn có chắc muốn xóa thông tin sinh viên này?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = bundle.getInt("studentId");
                if (studentDao.removeById(id)) {
                    Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_DELETE_SUCCESS, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(StudentActivity.this, ListStudentActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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

    private int getDefaultPosition(List<Course> courses, int courseId) {
        int position = 0;
        for (int i = 0; i < courses.size(); i++) {
            if (courseId == courses.get(i).getId()) {
                position = i + 1;
                break;
            }
        }
        return position;
    }
}
