package com.example.mateu_000.funkcje;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button aButton;
    private TextView display;

    private EditText editTextA;
    private EditText editTextB;
    private EditText editTextC;

    private Intent intencja;
    private Bundle budlencja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        aButton = (Button) findViewById(R.id.aButton);
        display = (TextView) findViewById(R.id.quadric_a);

        editTextA = (EditText)findViewById(R.id.quadric_a);
        editTextB = (EditText)findViewById(R.id.quadric_b);
        editTextC = (EditText)findViewById(R.id.quadric_c);

        intencja =  new Intent(this, SecondActivity.class);

        budlencja = new Bundle();




        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.generating, Toast.LENGTH_SHORT).show();
                budlencja.putString("elo1", editTextA.getText().toString());
                budlencja.putString("elo2", editTextB.getText().toString());
                budlencja.putString("elo3", editTextC.getText().toString());

                intencja.putExtras(budlencja);
                startActivity(intencja);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
