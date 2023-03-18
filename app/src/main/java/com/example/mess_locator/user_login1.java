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

public class user_login1 extends AppCompatActivity {
   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mess-locator-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login1);
        Button btn = findViewById(R.id.signUpStudent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_login1.this,SignupStudent.class);
                startActivity(intent);
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
                    Toast.makeText(user_login1.this,"Please Enter Email or Password",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(emailTxt)){
                                final String getPassword = snapshot.child(emailTxt).child("password").getValue(String.class);
                                if(getPassword.equals(passTxt)){
                                    Toast.makeText(user_login1.this,"Sucessfully Logged in",Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    Toast.makeText(user_login1.this,"Incorrect  Email or Password!!!!!!!!!",Toast.LENGTH_SHORT).show();
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