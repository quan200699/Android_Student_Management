package com.example.demosqllite.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demosqllite.R;
import com.example.demosqllite.config.StaticVariable;
import com.example.demosqllite.sqlite.IStudentDao;
import com.example.demosqllite.sqlite.impl.StudentDao;

public class StudentActivity extends AppCompatActivity {
    private TextView textViewStudentName;
    private TextView textViewStudentPhoneNumber;
    private TextView textViewStudentEmail;
    private IStudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        textViewStudentName = findViewById(R.id.textViewStudentName);
        textViewStudentPhoneNumber = findViewById(R.id.textViewStudentPhone);
        textViewStudentEmail = findViewById(R.id.textViewStudentEmail);
        studentDao = new StudentDao(this);
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        final Bundle bundle = getIntent().getExtras();
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
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createPopup(bundle);
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
