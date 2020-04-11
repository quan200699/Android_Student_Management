package com.example.demosqllite.activity.student;

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
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

public class StudentActivity extends AppCompatActivity {
    private EditText editTextStudentName;
    private EditText editTextStudentPhoneNumber;
    private EditText editTextStudentEmail;
    private IStudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentPhoneNumber = findViewById(R.id.editTextStudentPhone);
        editTextStudentEmail = findViewById(R.id.editTextStudentEmail);
        studentDao = new StudentDao(this);
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonEdit = findViewById(R.id.buttonEdit);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editTextStudentName.setText(bundle.getString("studentName"));
            editTextStudentPhoneNumber.setText(bundle.getString("studentPhoneNumber"));
            editTextStudentEmail.setText(bundle.getString("studentEmail"));
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
                    int courseId = 0;
                    Student student = new Student(id, name, phoneNumber, email, courseId);
                    if (studentDao.updateById(id, student)) {
                        Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_UPDATE_SUCCESS, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), StaticVariable.MESSAGE_FAIL, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

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
}
