package com.example.mateu_000.funkcje;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView wzor;
    private Bundle budlencja;
    private Intent sendIntent;
    MyDraw draw;

    Bitmap bmp;
    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String valueA;
        String valueB;
        String valueC;

        budlencja = getIntent().getExtras();
        valueA = budlencja.getString("elo1");
        valueB = budlencja.getString("elo2");
        valueC = budlencja.getString("elo3");

        wzor = (TextView) findViewById(R.id.wzor);

        wzor.setText(valueA + "x^2" + " + " + valueB + "x" + " + " + valueC);

        //getIntent().removeExtra((String) wzor.getText());

        //customCanvas = (View) findViewById(R.id.wykres);



        bmp = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bmp);





        //draw.onDraw();



        sendIntent = new Intent();

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, wzor.getText());
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);

            }
        });

    }




}

class MyDraw extends View {



    public MyDraw(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);


        Path path = new Path();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        paint.setColor(Color.parseColor("#CD5C5C"));


        path.moveTo(0, -130);
        path.lineTo(-50, 60);
        path.lineTo(0, 30);
        path.lineTo(50, 60);
        path.close();
        path.offset(50, 80);

        canvas.drawPath(path, paint);




    }
}
