package com.example.moh.reminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class AcceuilAppActivity extends AppCompatActivity {
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil_app);
        getSupportActionBar().hide();
        charge();

    }


    private void charge() {

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        new CountDownTimer(2500, 1000) {
            public void onTick(long millisUntilFinished) {
                spinner.setVisibility(View.VISIBLE);
            }

            public void onFinish() {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }

}
