package com.example.mess_locator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_selection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        Button ownerlogin = findViewById(R.id.btn2);
        Button studentlogin = findViewById(R.id.btn1);
        ownerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_selection.this,owner_login.class);
                startActivity(intent);
            }
        });
        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_selection.this,user_login1.class);
                startActivity(intent);
            }
        });
    }

}