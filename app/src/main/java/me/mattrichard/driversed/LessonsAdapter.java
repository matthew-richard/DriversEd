package me.mattrichard.driversed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Matt on 3/22/2017.
 */

public class LessonsAdapter extends ArrayAdapter<Lesson> {

    public LessonsAdapter(Context context, ArrayList<Lesson> lessons) {
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

        TextView date = (TextView) view.findViewById(R.id.textDate);
        date.setText(new SimpleDateFormat("MM/dd/yyyy").format(lesson.date));

        TextView t = (TextView) view.findViewById(R.id.textTime);
        if (lesson.timeOfDay == Lesson.TimeOfDay.DAY) {
            t.setText("day");
            t.setCompoundDrawables(null,
                    ContextCompat.getDrawable(getContext(), R.drawable.ic_sun_black_32dp),
                    null, null);

        }
        else {
            t.setText("night");
            t.setCompoundDrawables(null,
                    ContextCompat.getDrawable(getContext(), R.drawable.ic_night_black_32dp),
                    null, null);
        }

        // Out of time :(

        if (lesson.lessonType == Lesson.LessonType.HIGHWAY) {

        }
        else if (lesson.lessonType == Lesson.LessonType.RESIDENTIAL) {

        }
        else {
            // commercial
        }

        if (lesson.weather == Lesson.Weather.CLEAR) {

        }
        else if (lesson.weather == Lesson.Weather.RAINING) {

        }
        else {
            // snow/ice
        }

        return view;
    }
}
