package com.example.demosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private EditText editTextEmail;
    private IStudentDao studentDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        Button buttonSave = findViewById(R.id.buttonSave);
        studentDao = new StudentDao(this);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String email = editTextEmail.getText().toString();
                Student student = new Student(name, phoneNumber, email);
                studentDao.insert(student);
            }
        });
    }
}
