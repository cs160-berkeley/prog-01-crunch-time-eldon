package io.eldon.crunchtime;

/**
 * Created by eldon on 1/30/2016.
 */
public class Exercise {
    private String mExerciseName;
    private Float mKcalPerUnit;

    public Exercise(String name, Float kcal_per_unit) {
        mExerciseName = name;
        mKcalPerUnit = kcal_per_unit;
    }

    public Float convertToKcal(Integer exercise_units) {
        return exercise_units * mKcalPerUnit;
    }

    public Integer convertToUnit(Float kcal) {
        return (Integer)Math.round(kcal * ((float)1.0 / mKcalPerUnit));
    }

    @Override
    public String toString() {
        return (String)mExerciseName;            // What to display in the Spinner list.
    }
}
