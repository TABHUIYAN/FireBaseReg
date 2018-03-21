package com.example.bhuiyan.firebasereg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
private FirebaseAuth firebaseAuth;
private TextView textViewUserEmail;
private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
       //firebase user objecty
        FirebaseUser user=firebaseAuth.getCurrentUser();

        textViewUserEmail=(TextView)findViewById(R.id.textViewUserEmail);

        textViewUserEmail.setText("Welcome: "+user.getEmail());
        btnLogout=(Button)findViewById(R.id.btnLogout);
        //listener Attached
        btnLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
if (view==btnLogout){
    firebaseAuth.signOut();
startActivity(new Intent(this,LoginActivity.class));
}
    }
}
