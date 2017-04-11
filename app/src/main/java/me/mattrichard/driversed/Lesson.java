package me.mattrichard.driversed;

import java.util.Calendar;
import java.util.Date;

public class Lesson {
    private static int prev_id = -1;

    public enum TimeOfDay { DAY, NIGHT }
    public enum LessonType { RESIDENTIAL, COMMERCIAL, HIGHWAY }
    public enum Weather {CLEAR, RAINING, SNOW_ICE }

    public Lesson() {
        if (prev_id < 0) {
            // TODO: Read prev_id from persistent storage
            prev_id = 0;
        }

        Calendar calendar = Calendar.getInstance();

        this.id = ++prev_id;
        this.numHours = 0;
        this.date = calendar.getTime();
        this.timeOfDay = calendar.get(Calendar.HOUR_OF_DAY) < 18 ? TimeOfDay.DAY : TimeOfDay.NIGHT;
        this.lessonType = LessonType.RESIDENTIAL;
        this.weather = Weather.CLEAR;
    }

    public int id;
    public float numHours;
    public Date date;
    public TimeOfDay timeOfDay;
    public LessonType lessonType;
    public Weather weather;

    public static String formatEnumString(String s) {
        return s.toLowerCase().replace('_', '/');
    }

    public static Lesson load(int id) {
        // return lesson in db
        return new Lesson();
    }

    public void save() {
        // save lesson to database, creating new one (and assigning id) if necessary
    }
}
