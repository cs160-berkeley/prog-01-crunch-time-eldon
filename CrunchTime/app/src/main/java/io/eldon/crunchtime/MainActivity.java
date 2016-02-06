package io.eldon.crunchtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mKcalText;
    private Spinner mInputExerciseFrom;
    private Spinner mInputExerciseTo;
    private TextView mExerciseFrom;
    private TextView mExerciseTo;
    private EditText mInputUnits;
    private EditText mOutputUnits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKcalText = (TextView)findViewById(R.id.kcal_text);
        mInputExerciseFrom = (Spinner)findViewById(R.id.input_exercise_from);
        mInputExerciseTo = (Spinner)findViewById(R.id.input_exercise_to);
        mExerciseFrom = (TextView)findViewById(R.id.from_exercise);
        mExerciseTo = (TextView)findViewById(R.id.to_exercise);
        mInputUnits = (EditText)findViewById(R.id.input_units);
        mOutputUnits = (EditText)findViewById(R.id.output_units);

        populateSpinner(mInputExerciseFrom);
        populateSpinner(mInputExerciseTo);

        mInputExerciseFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertAndUpdate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mInputExerciseTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertAndUpdate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mInputUnits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                convertAndUpdate();
            }
        });
    }

    private void convertAndUpdate() {
        Integer inputUnits;
        try {
            inputUnits = Integer.parseInt(mInputUnits.getText().toString());
        } catch (NumberFormatException e){
            inputUnits = 0;
        }
        Exercise inputExercise = (Exercise)mInputExerciseFrom.getSelectedItem();
        Exercise outputExercise = (Exercise)mInputExerciseTo.getSelectedItem();
        Float kcalRequired = inputExercise.convertToKcal(inputUnits);
        int unitsRequired = outputExercise.convertToUnit(kcalRequired);

        mExerciseFrom.setText(inputExercise.toString());
        mExerciseTo.setText(outputExercise.toString());
        mOutputUnits.setText(String.valueOf(unitsRequired));
        mKcalText.setText(String.format("Equivalent to %.2f kcal.", kcalRequired));
    }

    private void clearViews() {
        mExerciseFrom.setText("");
        mExerciseTo.setText("");
        mOutputUnits.setText("");
        mKcalText.setText("Select exercises to convert.");
    }

    private void populateSpinner(Spinner spinner) {
        List<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(new Exercise("Push-ups", (float)(100.0/350)));
        exercises.add(new Exercise("Sit-ups", (float)(100.0/200)));
        exercises.add(new Exercise("Squats", (float)(100.0/225)));
        exercises.add(new Exercise("Mins. Leg Lifts", (float)(100.0/200)));
        exercises.add(new Exercise("Mins. Plank", (float)(100.0/25)));
        exercises.add(new Exercise("Mins. Jumping Jacks", (float)(100.0/10)));
        exercises.add(new Exercise("Pull-ups", (float)(100.0/100)));
        exercises.add(new Exercise("Mins. Cycling", (float)(100.0/12)));
        exercises.add(new Exercise("Mins. Walking", (float)(100.0/20)));
        exercises.add(new Exercise("Mins. Jogging", (float)(100.0/12)));
        exercises.add(new Exercise("Mins. Swimming", (float)(100.0/13)));
        exercises.add(new Exercise("Mins. Stairs", (float)(100.0/15)));



        ArrayAdapter userAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, exercises);

        spinner.setAdapter(userAdapter);
    }
}
