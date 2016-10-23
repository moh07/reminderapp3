package com.example.moh.reminderapp.inscription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.moh.reminderapp.R;
import com.example.moh.reminderapp.tasks.jourdetravail;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class inscription extends AppCompatActivity {

private static final int RC_SIGN_IN =0;
    private FirebaseAuth auth ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        auth =FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null)
        {

        }
        else {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setProviders(
                            AuthUI.EMAIL_PROVIDER,
                            AuthUI.FACEBOOK_PROVIDER,
                            AuthUI.GOOGLE_PROVIDER)
                    .build(), RC_SIGN_IN);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RC_SIGN_IN){
            Log.d("AUTH",auth.getCurrentUser().getEmail());
        }
    }

}
