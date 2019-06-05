package com.tecent.chatapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDataBase= FirebaseDatabase.getInstance().getReference("Message");
        final TextView textView=findViewById(R.id.textView);
        myDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textView.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                textView.setText("CANCELLED");
            }
        });
    }
    public void sendMessage(View view){
        EditText editText=findViewById(R.id.editText);
        myDataBase.push().setValue(editText.getText().toString());
//        myDataBase.child("Name").setValue(editText.getText().toString());
        editText.setText("");
    }
}
