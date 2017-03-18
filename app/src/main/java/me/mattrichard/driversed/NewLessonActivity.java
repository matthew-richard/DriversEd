package me.mattrichard.driversed;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewLessonActivity extends NavigationActivity {

    private Button startButton;
    private Button stopButton;
    private Spinner lessonTypeSpinner;
    private Spinner conditionsSpinner;
    private TextView dateText;
    private TextView hoursText;

    private Long startTime = null;

    private LessonFragment lessonFragment;

    @Override
    protected void setInnerContentView(ViewGroup root) {
        getLayoutInflater().inflate(R.layout.content_new_lesson, root);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startButton = (Button) findViewById(R.id.buttonStart);
        stopButton = (Button) findViewById(R.id.buttonStop);
        lessonTypeSpinner = (Spinner) findViewById(R.id.lessonTypeSpinner);
        conditionsSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        dateText = (TextView) findViewById(R.id.dateText);
        hoursText = (TextView) findViewById(R.id.hoursText);
        lessonFragment = (LessonFragment) findViewById(R.id.lesson_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return super.onNavigationItemSelected(item);
    }

    public void onPressStart(View v) {
        startTime = System.currentTimeMillis();
        dateText.setText(new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime()));
        hoursText.setText("In progress");
        stopButton.setEnabled(true);
        startButton.setEnabled(false);
    }

    public void onPressStop(View v) {
        double hours = (System.currentTimeMillis() - startTime) / (double) (1000 * 60 * 60);
        hoursText.setText(String.format("%.2f", hours));
        startTime = null;
        stopButton.setEnabled(false);
        startButton.setEnabled(true);
    }
}
