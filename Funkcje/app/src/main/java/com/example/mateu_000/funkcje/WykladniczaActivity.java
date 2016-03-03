package com.example.mateu_000.funkcje;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WykladniczaActivity extends AppCompatActivity {

    ArrayList<EditText> xList;
    ArrayList<Double> tab;

    LinearLayout root;

    Button generate;
    Button increase;
    Button decrease;

    Intent intencja_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wykladnicza);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        setTitle("Rysowanie funkcji");

        root = (LinearLayout) findViewById(R.id.wykladnicza_fields);

        xList = new ArrayList<>();

        increase = (Button) findViewById(R.id.zwiekszenie_stopnia);
        decrease = (Button) findViewById(R.id.zmniejszenie_stopnia);

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (xList.size() > 0) {

                    if (xList.size() != 1) {

                        TextView txt = nowyText("x ^ " + xList.size() + " + ");
                        root.addView(txt, 0);

                    } else {

                        TextView txt = nowyText("x" + " + ");
                        root.addView(txt, 0);
                    }
                }

                EditText edit = nowyField();

                xList.add(edit);
                root.addView(edit, 0);

                Snackbar.make(v, "Zwiekszam stopień wielomianu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                root.removeAllViews();
                xList.clear();

            }
        });

        generate = (Button) findViewById(R.id.generate_wykladnicza);


        intencja_3 = new Intent(this, SecondActivity.class);

        generate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                tab = new ArrayList<>();

                for(int i = 0; i < xList.size(); i++) {

                    double tmp;
                    String tp = xList.get(i).getText().toString();

                    try {
                        tmp = Double.parseDouble(tp);

                    } catch(NumberFormatException e) {
                        tmp = 0;
                    }

                    tab.add(tmp);

                }

                intencja_3.putExtra("numbers", tab);

                startActivity(intencja_3);

                Toast.makeText(WykladniczaActivity.this, "Drawing" , Toast.LENGTH_SHORT).show();

            }
        });





    }

    EditText nowyField(){

        EditText nowyField = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 5f);

        nowyField.setLayoutParams(params);
        nowyField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        nowyField.setGravity(Gravity.BOTTOM);

        return nowyField;

    }

    TextView nowyText(String text) {

        TextView nowyText = new TextView(this);
        nowyText.setText(text);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        nowyText.setLayoutParams(params);
        nowyText.setTextSize(16);
        nowyText.setGravity(Gravity.CENTER);

        return nowyText;

    }


}
