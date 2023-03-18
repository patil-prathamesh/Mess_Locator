package com.example.mess_locator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHome extends AppCompatActivity {
    ListView listView;
    ArrayList<Users> arrayList;
    Adapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        listView = findViewById(R.id.listview1);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();

//        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(UserHome.this, android.R.layout.simple_list_item_1,myArrayList);

        arrayList=new ArrayList<Users>();
        adapter=new Adapter(UserHome.this,R.layout.items,arrayList);
        reference.child("owner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    String mname = ds.child("messName").getValue(String.class);
                    String maddress = ds.child("address").getValue(String.class);
                    arrayList.add(new Users(""+mname,""+maddress));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setAdapter(adapter);

    }
}