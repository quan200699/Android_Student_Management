package com.example.demosqllite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demosqllite.R;
import com.example.demosqllite.activity.student.ListStudentActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonListStudent = findViewById(R.id.buttonListStudent);
        Button buttonListCourse = findViewById(R.id.buttonListClass);
        onClickEvent(buttonListStudent, ListStudentActivity.class);
        onClickEvent(buttonListCourse, ListCourseActivity.class);
    }

    public void onClickEvent(Button button, final Class<?> activity) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity);
                startActivity(intent);
            }
        });
    }
}
