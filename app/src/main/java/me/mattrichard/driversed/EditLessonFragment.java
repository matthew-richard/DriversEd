package me.mattrichard.driversed;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EditLessonFragment extends Fragment {

    private OnLessonSaveListener mListener;

    private Button startButton;
    private Button stopButton;
    private Button clearButton;
    private MenuItem saveAction;
    private Spinner lessonTypeSpinner;
    private Spinner conditionsSpinner;
    private TextView dateText;
    private TextView hoursText;
    private Long startTime = null;

    private Lesson lesson;

    private boolean modified = false;
    private boolean timing = false;

    public EditLessonFragment() {
        // Required empty public constructor
        this.lesson = new Lesson();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_edit_lesson, container, false);

        // Find views
        startButton = (Button) layout.findViewById(R.id.buttonStart);
        stopButton = (Button) layout.findViewById(R.id.buttonStop);
        clearButton = (Button) layout.findViewById(R.id.buttonClear);
        lessonTypeSpinner = (Spinner) layout.findViewById(R.id.lessonTypeSpinner);
        conditionsSpinner = (Spinner) layout.findViewById(R.id.conditionSpinner);
        dateText = (TextView) layout.findViewById(R.id.dateText);
        hoursText = (TextView) layout.findViewById(R.id.hoursText);

        // Hook up buttons to onClick callbacks
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPressStart(view);
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPressStop(view);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPressClear(view);
            }
        });

        // Tell activity that this fragment has action bar items to add
        setHasOptionsMenu(true);

        return layout;
    }

    public void onPressStart(View v) {
        startTime = System.currentTimeMillis();
        timing = true;

        updateViews();
    }

    public void onPressStop(View v) {
        lesson.numHours += (System.currentTimeMillis() - startTime) / (double) (1000 * 60 * 60);
        startTime = null;
        timing = false;
        modified = true;

        updateViews();
    }

    public void onPressClear(View v) {
        setLesson(new Lesson());
    }

    public void onPressSave() {
        String toast;
        if (lesson.numHours > 0) {
            toast = "Drive saved to logs";
            lesson.save();
            modified = false;
        } else {
            toast = "Won't save 0-hour drive";
        }
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();

        // pass control to activity
        mListener.onLessonSave();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.action_bar_edit_lesson, menu);
        saveAction = menu.findItem(R.id.action_save);

        // Initialize view states. This is in onCreateOptionsMenu because
        // saveAction can't be set until this stage in the Fragment's
        // lifecycle.
        setLesson(new Lesson());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_save:
                onPressSave(); break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLessonSaveListener) {
            mListener = (OnLessonSaveListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Replaces the Lesson represented by this fragment.
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
        modified = false;
        updateViews();
    }

    public void updateViews() {
        saveAction.setEnabled(modified && !timing);

        startButton.setEnabled(!timing);
        stopButton.setEnabled(timing);
        clearButton.setEnabled(!timing);

        dateText.setText(new SimpleDateFormat("MM/dd/yyyy").format(lesson.date));
        hoursText.setText(timing ? "In progress" : lesson.numHours <= 0 ? "-" : String.format("%.2f",lesson.numHours));
    }

    public interface OnLessonSaveListener {
        void onLessonSave();
    }
}
