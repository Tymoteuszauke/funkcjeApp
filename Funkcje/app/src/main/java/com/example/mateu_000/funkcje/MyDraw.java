package com.example.mateu_000.funkcje;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Matek on 2015-12-29.
 */
class MyDraw extends View {

    private Paint paint = new Paint();
    private Paint wykres = new Paint();

    private ArrayList<Double> tab;

    private int height = 900;
    private int width = 900;

    public MyDraw(Context context, ArrayList<Double> wykladnicza) {
        super(context);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        paint.setColor(Color.parseColor("#fff0f5"));
        wykres.setColor(Color.parseColor("#000000"));
        wykres.setStrokeWidth(5);

        tab = new ArrayList<>();

        for(int i = 0; i < wykladnicza.size(); i++) {
            tab.add(wykladnicza.get(i));
        }
    }

    private float scale = 15;

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawLine(0, getsHeight()/2, getsWidth(), getsHeight()/2, wykres);
        canvas.drawLine(getsWidth()/2, 0, getsWidth()/2, getsHeight(), wykres);

        float prevx = -1, prevy = -1;

        for (float x = 0; x < getsWidth(); x++) {

            float x1 = (x - getsHeight()/2) / scale;

            float y = 0;
            for(int i = 0; i < tab.size(); i++) {
                y += tab.get(i) * Math.pow(x1, i);
            }

            float y1 = getsWidth()/2 - y * scale;

            if (prevx != -1 && prevy != -1) {
                canvas.drawLine(prevx, prevy, x, y1, paint);
            }

            prevx = x;
            prevy = y1;
        }
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float value) {

        if (value > 1) {
            scale = value;
        }
    }

    public int getsHeight() {
        return height;
    }

    public int getsWidth() {
        return width;
    }
}
