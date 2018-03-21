package com.example.bhuiyan.firebasereg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnRegister;
private EditText editTextEmail;
private EditText editTextPassword;
private TextView txViewSignin;
private ProgressDialog progressDialog;
//defining firebaseAuth object
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start firebase auth object
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }



        //initializing views
        btnRegister=(Button)findViewById(R.id.btnRegister);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        txViewSignin=(TextView)findViewById(R.id.txViewSignin);
        progressDialog=new ProgressDialog(this);
//listener attaching to button
        btnRegister.setOnClickListener(this);
        txViewSignin.setOnClickListener(this);
    }
    private  void registerUser(){
        //from edit text getting email and password
String email=editTextEmail.getText().toString().trim();
String password=editTextPassword.getText().toString().trim();
if (TextUtils.isEmpty(email)){
    //email is empty
    Toast.makeText(this,"Please enteryour email",Toast.LENGTH_SHORT).show();
    //stopping function from excuting further
    return;
}
if(TextUtils.isEmpty(password)){
  //password is empty
    Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
    //stopping function from excuting further
    return;
}

//if validation are satisfied or ok
        //will show a progressbar first
    progressDialog.setMessage("Registering User....");
progressDialog.show();
//creating a new user
firebaseAuth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // if(firebaseAuth.getCurrentUser()!=null){
                    //profile activity here
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    //user is successfully registerd and logged in
                    //start profile activity here
                    //display a toast here
                    // Toast.makeText(MainActivity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Registered Failed, please try again", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();


            }
        });

    }


    @Override
    public void onClick(View view) {
     if(view==btnRegister){
         registerUser();
     }
     if(view==txViewSignin){
         //will open login activity here
         startActivity(new Intent(this,LoginActivity.class));
     }

    }
}
