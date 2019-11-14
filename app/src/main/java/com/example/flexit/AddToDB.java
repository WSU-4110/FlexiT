package com.example.flexit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddToDB {
    private static final AddToDB instance = new AddToDB();
    private FirebaseDatabase myfirebaseDB;
    private DatabaseReference myRef = null;

    private AddToDB(){
        myfirebaseDB = FirebaseDatabase.getInstance();
    }

    public void getTable(String tableName){
        myRef = myfirebaseDB.getReference(tableName);
    }

    public void pushToTable(String userID, String key, String value){
        if(myRef == null){
            System.out.println("Not referencing any table");
        }

        myRef.child(userID).child(key).setValue(value);
    }
    public void pushToTable(String userID, String key, double value){
        if(myRef == null){
            System.out.println("Not referencing any table");
        }

        myRef.child(userID).child(key).setValue(value);
    }

    public static AddToDB getInstance(){
        return instance;
    }

}
