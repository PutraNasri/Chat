package com.example.byonge.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private ListView list;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.text);
        list = (ListView) findViewById(R.id.list);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void send(View v) {

        String kunci = myRef.push().getKey();
        DatabaseReference childData = myRef.child(kunci);
        DatabaseReference nama = childData.child("nama");
        nama.setValue("buyong");
        DatabaseReference pesan = childData.child("pesan");
        pesan.setValue(text.getText().toString());
    }
}