package com.example.moh.reminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {
    ImageView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        btnLogin = (ImageView) findViewById(R.id.idlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ilog = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(ilog);
            }
        });
    }


}
