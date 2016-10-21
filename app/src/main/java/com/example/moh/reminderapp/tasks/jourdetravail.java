package com.example.moh.reminderapp.tasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.moh.reminderapp.R;

public class jourdetravail extends AppCompatActivity {
    Switch s;
    LinearLayout l11;
   CheckBox lundi1,mardi1,mercredi1,jeudi1,vendredi1,samedi1,dimanche1;
    CheckBox lundi2,mardi2,mercredi2,jeudi2,vendredi2,samedi2,dimanche2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jourdetravail);
        /////////////////////////////////////////////
        l11=(LinearLayout) findViewById(R.id.l99);
        l11.setVisibility(l11.INVISIBLE);
        /////////////////////////////////////////////
                lundi1=(CheckBox) findViewById(R.id.Lundi1);
                mardi1=(CheckBox) findViewById(R.id.mardi1);
                mercredi1=(CheckBox) findViewById(R.id.mercredi1);
                jeudi1=(CheckBox) findViewById(R.id.jeudi1);
                vendredi1=(CheckBox) findViewById(R.id.vendredi1);
                samedi1=(CheckBox) findViewById(R.id.samedi1);
                dimanche1=(CheckBox) findViewById(R.id.dimanche1);

        lundi2=(CheckBox) findViewById(R.id.lundi2);
        mardi2=(CheckBox) findViewById(R.id.mardi2);
        mercredi2=(CheckBox) findViewById(R.id.mercredi2);
        jeudi2=(CheckBox) findViewById(R.id.jeudi2);
        vendredi2=(CheckBox) findViewById(R.id.vendredi2);
        samedi2=(CheckBox) findViewById(R.id.samedi2);
        dimanche2=(CheckBox) findViewById(R.id.dimanche2);



    }

    public  void afficher1(View v) {
            if(lundi1.isChecked()){lundi2.setVisibility(lundi2.INVISIBLE);}
        else {lundi2.setVisibility(lundi2.VISIBLE);}
            if(mardi1.isChecked()){mardi2.setVisibility(mardi2.INVISIBLE);}
        else {mardi2.setVisibility(mardi2.VISIBLE);}
            if(mercredi1.isChecked()){mercredi2.setVisibility(mercredi2.INVISIBLE);}
        else {mercredi2.setVisibility(mercredi2.VISIBLE);}
            if(jeudi1.isChecked()){jeudi2.setVisibility(jeudi2.INVISIBLE);}
        else {jeudi2.setVisibility(jeudi2.VISIBLE);}
            if(vendredi1.isChecked()){vendredi2.setVisibility(vendredi2.INVISIBLE);}
        else{vendredi2.setVisibility(vendredi2.VISIBLE);}
            if(samedi1.isChecked()){samedi2.setVisibility(samedi2.INVISIBLE);}
        else {samedi2.setVisibility(samedi2.VISIBLE);}
            if(dimanche1.isChecked()){dimanche2.setVisibility(dimanche2.INVISIBLE);}
        else {dimanche2.setVisibility(dimanche2.VISIBLE);}
    }

    public  void afficher(View v) {
        s = (Switch) findViewById(R.id.switch1);
        if (s.isChecked()) {

            l11.setVisibility(l11.VISIBLE);
            if(lundi1.isChecked()){lundi2.setVisibility(lundi2.INVISIBLE);}
            if(mardi1.isChecked()){lundi2.setVisibility(mardi2.INVISIBLE);}
            if(mercredi1.isChecked()){lundi2.setVisibility(mercredi2.INVISIBLE);}
            if(jeudi1.isChecked()){lundi2.setVisibility(jeudi2.INVISIBLE);}
            if(vendredi1.isChecked()){lundi2.setVisibility(vendredi2.INVISIBLE);}
            if(samedi1.isChecked()){lundi2.setVisibility(samedi2.INVISIBLE);}
            if(dimanche1.isChecked()){lundi2.setVisibility(dimanche2.INVISIBLE);}

        } else {
            l11.setVisibility(l11.INVISIBLE);
        }
    }
    public void enregistrer(View v){
        Intent i = new Intent(jourdetravail.this, jourderepos.class);
        startActivity(i);
    }
}
