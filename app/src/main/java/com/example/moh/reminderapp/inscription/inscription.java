package com.example.moh.reminderapp.inscription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moh.reminderapp.R;
import com.example.moh.reminderapp.tasks.jourdetravail;

public class inscription extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Intent i = new Intent(inscription.this, jourdetravail.class);
        startActivity(i);

    }

}
