package com.example.demosqllite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.demosqllite.R;
import com.example.demosqllite.model.Course;

import java.util.List;

public class ListCourseAdapter extends ArrayAdapter<Course> {
    public ListCourseAdapter(Context context, int resource, List<Course> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_course_row, null);
        }
        Course course = getItem(position);
        if (course != null) {
            TextView textViewName = view.findViewById(R.id.textViewCourseName);
            textViewName.setText(course.getName());
        }
        return view;
    }
}
