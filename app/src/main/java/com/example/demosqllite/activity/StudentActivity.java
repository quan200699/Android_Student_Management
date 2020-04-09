package com.example.demosqllite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.demosqllite.R;

public class StudentActivity extends AppCompatActivity {
    private TextView textViewStudentName;
    private TextView textViewStudentPhoneNumber;
    private TextView textViewStudentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        textViewStudentName = findViewById(R.id.textViewStudentName);
        textViewStudentPhoneNumber = findViewById(R.id.textViewStudentPhone);
        textViewStudentEmail = findViewById(R.id.textViewStudentEmail);
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            textViewStudentName.setText(bundle.getString("studentName"));
            textViewStudentPhoneNumber.setText(bundle.getString("studentPhoneNumber"));
            textViewStudentEmail.setText(bundle.getString("studentEmail"));
            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StudentActivity.this, ListStudentActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
