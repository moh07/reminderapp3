package com.example.moh.reminderapp.tasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.moh.reminderapp.R;
import com.example.moh.reminderapp.event.Evenement;

public class jourderepos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jourderepos);
    }
    public void enregistrer(View v){
        Intent i = new Intent(jourderepos.this, Evenement.class);
        startActivity(i);
    }
}
