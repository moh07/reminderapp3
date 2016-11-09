package com.example.moh.reminderapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity implements View.OnClickListener {
   Button btnRegister;
    EditText edittextemail,edittextpassword;
    TextView  textsign;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();



        progressDialog=new ProgressDialog(this);
        btnRegister=(Button)findViewById(R.id.buttonSignup);

        edittextemail=(EditText)findViewById(R.id.editTextEmail);
        edittextpassword=(EditText)findViewById(R.id.editTextPassword);

        textsign=(TextView)findViewById(R.id.textViewSignin);
        btnRegister.setOnClickListener(this);
        textsign.setOnClickListener(this);
    }
    private void registerUser(){
        String email=edittextemail.getText().toString().trim();
        String password=edittextpassword.getText().toString().trim();



        if(TextUtils.isEmpty(email)){
            //email is impty
            Toast.makeText(this,"Please entrer email",Toast.LENGTH_SHORT).show();
            //stopping the function  execution further
            return;
        }

        if(TextUtils.isEmpty(password)){
            //email is impty
            Toast.makeText(this,"Please entrer password",Toast.LENGTH_SHORT).show();
            //stopping the function  execution further
            return;
        }
        progressDialog.setMessage("Registering User ....");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            //we will start the profile activity here
                            //rignt now lets  display a toast only
                            finish();
                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Could not register .please try again",Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v==btnRegister){
            registerUser();
        }
        if(v==textsign){
    //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


}
