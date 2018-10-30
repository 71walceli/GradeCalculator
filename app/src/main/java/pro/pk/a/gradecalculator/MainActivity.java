package pro.pk.a.gradecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView Q1P1;
    private TextView Q1P2;
    private TextView Q1P3;
    private TextView Q2P1;
    private TextView Q2P2;
    private TextView Q2P3;
    private TextView EX1;
    private TextView EX2;
    private TextView A;

    private float q1p1;
    private float q1p2;
    private float q1p3;
    private float ex1;
    private float q2p1;
    private float q2p2;
    private float q2p3;
    private float ex2;
    private float a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Q1P1 = findViewById(R.id.Q1P1);
        Q1P2 = findViewById(R.id.Q1P2);
        Q1P3 = findViewById(R.id.Q1P3);
        Q2P1 = findViewById(R.id.Q2P1);
        Q2P2 = findViewById(R.id.Q2P2);
        Q2P3 = findViewById(R.id.Q2P3);
        EX1 = findViewById(R.id.E1);
        EX2 = findViewById(R.id.E2);
        A = findViewById(R.id.Result);
    }

    public void button(View view) {
        try {
            getN();
            if(chkN()) {
                a = setAGrade(q1p1, q1p2, q1p3, q2p1, q2p2, q2p3, ex1, ex2);
                A.setText(String.format(Locale.getDefault(),"%f", a));
            }
        } catch (Exception e) {
            findViewById(R.id.ShowErr).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.ShowErr)).setText(Log.getStackTraceString(e));
            Log.e(getString(R.string.app_name), Log.getStackTraceString(e));
        }
    }

    private void getN() {
        q1p1 = setG(Float.parseFloat(Q1P1.getText().toString()));
        q1p2 = setG(Float.parseFloat(Q1P2.getText().toString()));
        q1p3 = setG(Float.parseFloat(Q1P3.getText().toString()));
        q2p1 = setG(Float.parseFloat(Q2P1.getText().toString()));
        q2p2 = setG(Float.parseFloat(Q2P2.getText().toString()));
        q2p3 = setG(Float.parseFloat(Q2P3.getText().toString()));
        ex1 = setG(Float.parseFloat(EX1.getText().toString()));
        ex2 = setG(Float.parseFloat(EX2.getText().toString()));
    }

    public float setG(float n) {
        return n;
    }

    private float setAGrade(float q1p1, float q1p2, float q1p3, float q2p1, float q2p2, float q2p3, float ex1, float ex2) {
        return (setQGrade(q1p1, q1p2, q1p3, ex1) + setQGrade(q2p1, q2p2, q2p3, ex2)) / 2;
    }

    public float setQGrade(float q_1, float q_2, float q_3, float ex) {
        return (float) (( q_1+q_2+q_3 )/ 3 * .8 + ex*.2 );
    }

    public  boolean chkN() {
        int len = -1;
        float[] n = {q1p1, q1p2, q1p3, q2p1, q2p2, q2p3, ex1, ex2};
        TextView[] N = {Q1P1, Q1P2, Q1P3, Q2P1, Q2P2, Q2P3, EX1, EX2};
        float[] en = new float[8];
        for(int i = 0; i <= 7; i++) {
            if (!(n[i] >= 1 && n[i] <= 10) || N[i].getText().toString() == "") {
                len++;
                en[len] = Float.parseFloat(N[i].getText().toString());
                N[i].setText(null);
            }
        }
        if(len == -1) {
            (findViewById(R.id.ShowErr)).setVisibility(View.INVISIBLE);
            return true;
        }
        else {
            (findViewById(R.id.ShowErr)).setVisibility(View.VISIBLE);
            if(len == 0) {
                ((TextView)findViewById(R.id.ShowErr)).setText(getString(R.string.NotValidGrade, stringJoiner(en, ", ")));
            }
            else {
                ((TextView)findViewById(R.id.ShowErr)).setText(getString(R.string.NotValidGrades, stringJoiner(en, ", ")));
            }
            return false;
        }
    }

    public static String stringJoiner(float[] arr, String sep) {
        String str = "";
        int len = arr.length - 1;
        str += String.format(Locale.getDefault(), "&f", arr[0]);
        if(len > 0) {
            for(int i = 1; i <= len; i++) {
                if (arr[i] != 0) {
                    str += sep;
                    str += String.format(Locale.getDefault(), "&f", arr[i]);
                }
            }
        }
        return str;
    }
}
