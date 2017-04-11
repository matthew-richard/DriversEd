package me.mattrichard.driversed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matt on 3/22/2017.
 */

public class LessonsArrayAdapter extends ArrayAdapter<Lesson> {

    public LessonsArrayAdapter(Context context, ArrayList<Lesson> lessons) {
        super(context, 0, lessons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lesson lesson = getItem(position);
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.lesson_listview, parent, false);
        } else {
            view = convertView;
        }

        TextView hours = (TextView) view.findViewById(R.id.textHours);
        hours.setText(String.format("%.2f", lesson.numHours));
        return view;
    }
}
