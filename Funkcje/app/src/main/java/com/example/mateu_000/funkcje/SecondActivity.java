package com.example.mateu_000.funkcje;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity {

    private TextView wzor;

    private Button plus;
    private Button minus;

    private ArrayList<Double> tablica;
    private StringBuilder wzorek;

    private MyDraw funkcja;

    private ScaleGestureDetector SGD;

    private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tablica = new ArrayList<>();

        tablica = (ArrayList<Double>) getIntent().getSerializableExtra("numbers");

        wzorek = new StringBuilder(tablica.size()*10);

        setTitle("Wielomian " + (tablica.size()-1) + " stopnia");

        for (int i = tablica.size()-1; i >= 0 ; i--) {

            if (i != 1 && i != 0){
                wzorek.append(tablica.get(i)).append("x").append("^").append(i).append(" ");
            }
            if (i == 1) {
                wzorek.append(tablica.get(i)).append("x").append(" ");
            }
            if (i == 0) {
                wzorek.append(tablica.get(i));
            }
            if (i != 0) {
                wzorek.append(" + ");
            }

        }

        String x_1 = wzorek.toString();

        wzor = (TextView) findViewById(R.id.wzor);

        wzor.setText(x_1);

        funkcja = new MyDraw(this, tablica);

        SGD = new ScaleGestureDetector(this, new ScaleListener());

        LinearLayout drawLayout = (LinearLayout)this.findViewById(R.id.linear_layout_draw);

        drawLayout.addView(funkcja);

        drawLayout.setOnTouchListener(new LinearLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                SGD.onTouchEvent(m);
                return true;
            }
        });

        plus = (Button) findViewById(R.id.plusik);
        minus = (Button) findViewById(R.id.minusik);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Runnable task = new Runnable() {
                    @Override
                    public void run() {

                        funkcja.setScale(funkcja.getScale() + 5);
                        funkcja.postInvalidate();

                    }
                };

                worker.schedule(task, 0, TimeUnit.SECONDS);


            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funkcja.setScale(funkcja.getScale() - 5);
                funkcja.invalidate();
            }
        });

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float scaleFactor = detector.getScaleFactor();

            funkcja.setScale(funkcja.getScale() * scaleFactor);

            funkcja.invalidate();



            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}


