package com.example.demosqllite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demosqllite.R;
import com.example.demosqllite.model.Student;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

public class CreateActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private EditText editTextEmail;
    private IStudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonBack = findViewById(R.id.buttonBack);
        studentDao = new StudentDao(this);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStudent();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createStudent() {
        String name = editTextName.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String email = editTextEmail.getText().toString();
        Student student = new Student(name, phoneNumber, email);
        studentDao.insert(student);
        resetField();
    }

    private void resetField() {
        editTextName.setText("");
        editTextPhoneNumber.setText("");
        editTextEmail.setText("");
    }
}
