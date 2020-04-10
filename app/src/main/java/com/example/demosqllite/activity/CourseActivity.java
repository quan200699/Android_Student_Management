package com.example.demosqllite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.demosqllite.R;
import com.example.demosqllite.config.StaticVariable;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.impl.CourseDao;

public class CourseActivity extends AppCompatActivity {
    private EditText editTextCourseName;
    private ICourseDao courseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        editTextCourseName = findViewById(R.id.editTextCourseName);
        courseDao = new CourseDao(this);
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonEdit = findViewById(R.id.buttonEdit);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editTextCourseName.setText(bundle.getString("courseName"));
            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CourseActivity.this, ListCourseActivity.class);
                    startActivity(intent);
                }
            });
//            buttonDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    createPopup(bundle);
//                }
//            });
//            buttonEdit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int id = bundle.getInt("studentId");
//                    String name = editTextStudentName.getText().toString();
//                    String phoneNumber = editTextStudentPhoneNumber.getText().toString();
//                    String email = editTextStudentEmail.getText().toString();
//                    Student student = new Student(id, name, phoneNumber, email);
//                    if (studentDao.updateById(id, student)) {
//                        Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_UPDATE_SUCCESS, Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
    }
}
