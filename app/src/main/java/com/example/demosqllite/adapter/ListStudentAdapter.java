package com.example.demosqllite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.demosqllite.R;
import com.example.demosqllite.model.Student;

import java.util.List;

public class ListStudentAdapter extends ArrayAdapter<Student> {
    public ListStudentAdapter(Context context, int resource, List<Student> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_student, null);
        }
        Student student = getItem(position);
        if (student != null) {
            TextView textViewName = view.findViewById(R.id.textViewName);
            textViewName.setText(student.getName());
            TextView textViewPhoneNumber = view.findViewById(R.id.textViewPhoneNumber);
            textViewPhoneNumber.setText(student.getPhoneNumber());
            TextView textViewEmail = view.findViewById(R.id.textViewEmail);
            textViewEmail.setText(student.getEmail());
        }
        return view;
    }
}
