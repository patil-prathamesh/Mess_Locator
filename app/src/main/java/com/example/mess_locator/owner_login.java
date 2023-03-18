package com.example.mess_locator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class owner_login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://console.firebase.google.com/u/0/project/mess-locator/database/mess-locator-default-rtdb/data/~2F ");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);
        Button btn = findViewById(R.id.signupowner);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_login.this,Owner_signup.class);
                startActivity(i);
            }
        });


        final EditText email = findViewById(R.id.inputEmail);
        final EditText password= findViewById(R.id.inputPassword);
        final Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailTxt = email.getText().toString();
                final String passTxt = password.getText().toString();
                if(emailTxt.isEmpty()||passTxt.isEmpty()){
                    Toast.makeText(owner_login.this,"Please Enter Email or Password",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(emailTxt)){
                                final String getPassword = snapshot.child(emailTxt).child("password").getValue(String.class);
                                if(getPassword.equals(passTxt)){
                                    Toast.makeText(owner_login.this,"Sucessfully Logged in",Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    Toast.makeText(owner_login.this,"Incorrect  Email or Password!!!!!!!!!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });




    }
}