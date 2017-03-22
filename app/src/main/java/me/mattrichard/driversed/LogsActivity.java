package me.mattrichard.driversed;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LogsActivity extends NavigationActivity {

    public ListView listView;

    @Override
    protected void setInnerContentView(ViewGroup root) {
        getLayoutInflater().inflate(R.layout.content_logs, root);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = (ListView) findViewById(R.id.logs_list);

        // Generate hardcoded lessons, populate ListView with them
        ArrayList<Lesson> hardcodedLessons = generateHardcodedLessons();

        listView.setAdapter(new LessonsAdapter(this, hardcodedLessons));

    }

    private ArrayList<Lesson> generateHardcodedLessons() {
        ArrayList<Lesson> hardcodedLessons = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        Lesson a = new Lesson();
        a.numHours = 3.5f;
        a.timeOfDay = Lesson.TimeOfDay.NIGHT;
        hardcodedLessons.add(a);

        Lesson b = new Lesson();
        b.numHours = 1.7f;
        b.lessonType = Lesson.LessonType.HIGHWAY;
        hardcodedLessons.add(b);

        Lesson c = new Lesson();
        c.numHours = 3.4f;
        cal.setTime(c.date);
        cal.add(Calendar.DATE, 1);
        c.date = cal.getTime();
        hardcodedLessons.add(c);

        Lesson d = new Lesson();
        d.numHours = 0.36f;
        cal.setTime(d.date);
        cal.add(Calendar.DATE, -2);
        d.date = cal.getTime();
        d.weather = Lesson.Weather.SNOW_ICE;
        hardcodedLessons.add(d);

        return hardcodedLessons;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
