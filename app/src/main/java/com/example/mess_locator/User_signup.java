package com.example.mess_locator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class User_signup extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mess-locator-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);
        final EditText name = findViewById(R.id.inputname);
        final EditText email = findViewById(R.id.inputemail);
        final EditText mobno = findViewById(R.id.inputmobile);
        final EditText password= findViewById(R.id.inputpassword);
        final Button loginButton = findViewById(R.id.btnsignup);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nameTxt = name.getText().toString();

                final String mobNoTxt =mobno.getText().toString();
                final String emailTxt = email.getText().toString();
                final String emailTxtEncoded=emailTxt.replace(".", "/");
                System.out.println("emailTxtEncoded=  "+emailTxtEncoded);
                final String passTxt = password.getText().toString();
                if(emailTxtEncoded.isEmpty()||passTxt.isEmpty()||nameTxt.isEmpty()||mobNoTxt.isEmpty()||emailTxt.isEmpty()||passTxt.isEmpty()){
                    Toast.makeText(User_signup.this,"Please Enter All The Details",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        //    if(snapshot.hasChild(emailTxtEncoded)){
//                        //        Toast.makeText(Owner_signup.this,"Registration Unsucessfull!!!!!",Toast.LENGTH_SHORT).show();
//                        //
//                        //    else{
                            databaseReference.child("user").child(emailTxtEncoded).child("name").setValue(nameTxt);
                            databaseReference.child("user").child(emailTxtEncoded).child("mobno").setValue(mobNoTxt);
                            databaseReference.child("user").child(emailTxtEncoded).child("email").setValue(emailTxtEncoded);
                            databaseReference.child("user").child(emailTxtEncoded).child("password").setValue(passTxt);
                            Toast.makeText(User_signup.this,"Sucessfully Registered",Toast.LENGTH_SHORT).show();
                            //    }
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