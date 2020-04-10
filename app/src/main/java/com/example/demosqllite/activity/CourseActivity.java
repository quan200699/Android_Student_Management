package com.example.demosqllite.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createPopup(bundle);
                }
            });
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
    private void createPopup(final Bundle bundle) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
        builder.setTitle("Xóa thông tin lớp học");
        builder.setMessage("Bạn có chắc muốn xóa thông tin lớp này?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = bundle.getInt("courseId");
                if (courseDao.removeById(id)) {
                    Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_DELETE_SUCCESS, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(CourseActivity.this, ListCourseActivity.class);
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
}
