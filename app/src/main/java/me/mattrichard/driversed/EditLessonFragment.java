package me.mattrichard.driversed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EditLessonFragment extends Fragment {
    private static final String ARG_LESSON_NUM = "lessonNum";

    private int mLessonNum;

    private OnFragmentInteractionListener mListener;

    private Button startButton;
    private Button stopButton;
    private Spinner lessonTypeSpinner;
    private Spinner conditionsSpinner;
    private TextView dateText;
    private TextView hoursText;
    private Long startTime = null;
    private float numHours = 0;

    public EditLessonFragment() {
        // Required empty public constructor
    }

    public static EditLessonFragment newInstance(int lessonNum, boolean editing) {
        EditLessonFragment fragment = new EditLessonFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LESSON_NUM, lessonNum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLessonNum = getArguments().getInt(ARG_LESSON_NUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_edit_lesson, container, false);

        // Find views
        startButton = (Button) layout.findViewById(R.id.buttonStart);
        stopButton = (Button) layout.findViewById(R.id.buttonStop);
        lessonTypeSpinner = (Spinner) layout.findViewById(R.id.lessonTypeSpinner);
        conditionsSpinner = (Spinner) layout.findViewById(R.id.conditionSpinner);
        dateText = (TextView) layout.findViewById(R.id.dateText);
        hoursText = (TextView) layout.findViewById(R.id.hoursText);

        // Initialize text
        dateText.setText(new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime()));
        hoursText.setText("-");

        // Hook up buttons and onClick callbacks
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

        return layout;
    }

    public void onPressStart(View v) {
        startTime = System.currentTimeMillis();
        hoursText.setText("In progress");

        stopButton.setEnabled(true);
        startButton.setEnabled(false);
        //clearButton.setEnabled(false);
        //saveButton.setEnabled(false);
    }

    public void onPressStop(View v) {
        numHours += (System.currentTimeMillis() - startTime) / (double) (1000 * 60 * 60);
        hoursText.setText(String.format("%.2f", numHours));
        startTime = null;

        stopButton.setEnabled(false);
        startButton.setEnabled(true);
        //clearButton.setEnabled(true);
        //saveButton.setEnabled(true);
    }

    public void onPressClear(View v) {
        numHours = 0;
        hoursText.setText("-");
        dateText.setText(new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime()));
    }

    public void onPressSave(View v) {
        Toast.makeText(getActivity(), "Drive saved", Toast.LENGTH_SHORT).show();
        // TODO: pass control to containing activity
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {
        //void onFragmentInteraction(Uri uri);
    }
}
