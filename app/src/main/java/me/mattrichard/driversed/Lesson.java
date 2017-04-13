package me.mattrichard.driversed;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Lesson {
    private static SQLiteDatabase mDb = null;

    private static final String DB_PATH = Environment.getDataDirectory().getName()
            + "//data/me.mattrichard.driversed//databases//DriversEd.db";

    public enum TimeOfDay { DAY, NIGHT }
    public enum LessonType { RESIDENTIAL, COMMERCIAL, HIGHWAY }
    public enum Weather {CLEAR, RAINING, SNOW_ICE }

    public int id;
    public float numHours;
    public Date date;
    public TimeOfDay timeOfDay;
    public LessonType lessonType;
    public Weather weather;

    public Lesson() {
        Calendar calendar = Calendar.getInstance();

        this.id = -1;
        this.numHours = 0;
        this.date = calendar.getTime();
        this.timeOfDay = calendar.get(Calendar.HOUR_OF_DAY) < 18 ? TimeOfDay.DAY : TimeOfDay.NIGHT;
        this.lessonType = LessonType.RESIDENTIAL;
        this.weather = Weather.CLEAR;
    }

    private Lesson(Cursor c) {
        this.id = c.getInt(c.getColumnIndex("id"));
        this.numHours = c.getFloat(c.getColumnIndex("hours"));
        // l.date = c.getString(c.getColumnIndex("date")); // TODO: Parse date
        this.date = Calendar.getInstance().getTime();
        this.timeOfDay = TimeOfDay.valueOf(c.getString(c.getColumnIndex("timeOfDay")));
        this.lessonType = LessonType.valueOf(c.getString(c.getColumnIndex("lessonType")));
        this.weather = Weather.valueOf(c.getString(c.getColumnIndex("weather")));
    }

    public static Lesson load(int id) {
        // return lesson in db
        String[] args = {Integer.toString(id)};
        Cursor result = getDb().query("lessons", null, "id = ?", args, null, null, null);

        if (result.isAfterLast()) return null;
        else return new Lesson(result);
    }

    public void save() {
        // save lesson to database, creating new one (and assigning id) if necessary
        ContentValues values = new ContentValues();
        values.put("hours", this.numHours);
        values.put("date", this.date.toString());
        values.put("timeOfDay", this.timeOfDay.toString());
        values.put("lessonType", this.lessonType.toString());
        values.put("weather", this.weather.toString());

        if (this.id < 0) {
            getDb().insert("lessons", null, values);
        }
        else {
            String[] args = {Integer.toString(id)};
            getDb().update("lessons", values, "id = ?", args);
        }
    }

    public static ArrayList<Lesson> all() {
        ArrayList<Lesson> list = new ArrayList<>();
        Cursor c = getDb().query("lessons", null, null, null, null, null, null);

        c.moveToFirst();
        for(; !c.isAfterLast(); c.moveToNext()) {
            list.add(new Lesson(c));
        }
        c.close();

        return list;
    }

    private static SQLiteDatabase getDb() {
        if (mDb != null) {
            return mDb;
        }

        try {
            mDb = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        }
        catch (SQLiteException e) {
            // Create database if it doesn't exist
            mDb = SQLiteDatabase.openOrCreateDatabase(DB_PATH, null);
            mDb.execSQL("create table if not exists lessons (id integer primary key autoincrement, "
                    + "hours float not null, date varchar(25) not null, timeOfDay varchar(25) not null, "
                    + "lessonType varchar(25) not null, weather varchar(25) not null)");
        }

        return mDb;
    }
}
