package pro.pk.a.gradecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Calendar;

import pro.pk.a.gradeUtils.Grade;
import pro.pk.a.gradeUtils.GradeSet;
import pro.pk.a.gradeUtils.SchoolSemester;
import pro.pk.a.gradeUtils.SchoolYear;

import static pro.pk.a.gradeUtils.Grade.isGrade;
import static pro.pk.a.gradeUtils.Grade.makeGrades;

public class SchoolYearAvgActivity extends AppCompatActivity {
    private final int schoolYear = Calendar.getInstance().get(Calendar.YEAR);
    private final int schoolYear1 = schoolYear + 1;
    private final String yearString = Integer.toString(schoolYear) + " - " + Integer.toString(schoolYear1);
    private EditText[] gradeEditTexts;
    private Grade[] grades = makeGrades(8);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gradeEditTexts = new EditText[]{findViewById(R.id.Q1P1), findViewById(R.id.Q1P2), findViewById(R.id.Q1P3), findViewById(R.id.E1),
                findViewById(R.id.Q2P1), findViewById(R.id.Q2P2), findViewById(R.id.Q2P3), findViewById(R.id.E2)};
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus && !checkField(view)) {
                    ((TextView) view).setText(null);
                }
            }
        };

        for (EditText editText : gradeEditTexts) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }

    public void button(View view) {
        try {
            if(assignAllFields()) {
                SchoolYear year = new SchoolYear(yearString,
                        new SchoolSemester(new GradeSet(new Grade[]{grades[0], grades[1], grades[2], grades[3]})),
                        new SchoolSemester(new GradeSet(new Grade[]{grades[4], grades[5], grades[6], grades[7]})));
                ((TextView) findViewById(R.id.Result)).setText(Float.toString(year.getAverage().getGrade()));
            } else {
                ((TextView) findViewById(R.id.info)).setText(R.string.NotValidGrades);
            }
        } catch (Exception e) {
            reportError(e);
        }
    }

    private boolean assignAllFields() {
        for (int index = 0; index < grades.length || index < gradeEditTexts.length; index++) {
            try {
                grades[index].setGrade(Float.parseFloat(((TextView) gradeEditTexts[index]).getText().toString()));
            } catch (NumberFormatException e) {
                gradeEditTexts[index].setText(null);
                reportError(e);
                return false;
            }
        }
        return true;
    }

    private boolean assignField(View view) {
        int index = ArrayUtils.indexOf(gradeEditTexts, view);
        if (checkField(view));{
            try {
                grades[index].setGrade(Float.parseFloat(((TextView) view).getText().toString()));
                return true;
            } catch (Exception e) {
                ((TextView) view).setText(null);
                reportError(e);
                return false;
            }
        }
    }

    private boolean checkField(View view) {
        try {
            return isGrade(Float.parseFloat(((TextView) view).getText().toString()));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void reportError(Exception exception) {
        findViewById(R.id.ShowErr).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.ShowErr)).setText(Log.getStackTraceString(exception));
        Log.e(getString(R.string.app_name), Log.getStackTraceString(exception));
    }
}