package com.example.demosqllite.activity.course;

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
import com.example.demosqllite.activity.student.ListStudentByCourseActivity;
import com.example.demosqllite.model.Course;
import com.example.demosqllite.sqlite.ICourseDao;
import com.example.demosqllite.sqlite.impl.CourseDao;

import static com.example.demosqllite.config.StaticVariable.*;

public class CourseActivity extends AppCompatActivity {
    private EditText editTextCourseName;
    private ICourseDao courseDao;
    private Button buttonDelete;
    private Button buttonEdit;
    private Button buttonListStudentByCourse;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        init();
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final int courseId = bundle.getInt("courseId");
            final String courseName = bundle.getString("courseName");
            editTextCourseName.setText(courseName);
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
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = editTextCourseName.getText().toString();
                    Course course = new Course(courseId, name);
                    if (courseDao.updateById(courseId, course)) {
                        Toast.makeText(getApplicationContext(), MESSAGE_UPDATE_SUCCESS, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            buttonListStudentByCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CourseActivity.this, ListStudentByCourseActivity.class);
                    intent.putExtra("courseId", courseId);
                    intent.putExtra("courseName", courseName);
                    startActivity(intent);
                }
            });
        }
    }

    private void init() {
        editTextCourseName = findViewById(R.id.editTextCourseName);
        buttonBack = findViewById(R.id.buttonBack);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonListStudentByCourse = findViewById(R.id.buttonListStudentByCourse);
        courseDao = new CourseDao(this);
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
                    Toast.makeText(getApplicationContext(), MESSAGE_DELETE_SUCCESS, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
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
