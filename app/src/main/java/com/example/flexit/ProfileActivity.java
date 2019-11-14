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


//https://www.youtube.com/watch?v=hoF5A30fbu4
//https://www.youtube.com/watch?v=4DTgE7qbD_8&list=LLxw5sEAJDjSBQ3uBuqFI8XQ&index=2&t=105s
public class ProfileActivity extends AppCompatActivity {

        private FirebaseAuth mAuth;
        private FirebaseUser user;
/*        private FirebaseDatabase myFireBaseDB;
        private DatabaseReference myRef;*/
        private AddToDB DBinstance;


        private EditText fName;
        private EditText lName;
        private EditText user_age;
        private EditText user_height;
        private EditText user_weight;
        private EditText user_description;
        private Button next;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);
            mAuth = FirebaseAuth.getInstance();
/*            myFireBaseDB = FirebaseDatabase.getInstance();*/
            user = mAuth.getCurrentUser();

            fName = (EditText) findViewById(R.id.text_fName);
            lName = (EditText) findViewById(R.id.text_lName);
            user_age = (EditText) findViewById(R.id.text_age);
            user_weight = (EditText) findViewById(R.id.text_weight);
            user_height = (EditText) findViewById(R.id.text_height);
            user_description = (EditText) findViewById(R.id.text_description);

            next = (Button) findViewById(R.id.button_next);

/*            myRef = myFireBaseDB.getReference("Users");*/
            DBinstance = AddToDB.getInstance();
            DBinstance.getTable("Users");


            next.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    final String userID = user.getUid();
                    if(userID == null)return;
                    final String first = fName.getText().toString();
                    final String last = lName.getText().toString();
                    final int age = Integer.parseInt(user_age.getText().toString());
                    final int height = Integer.parseInt(user_height.getText().toString());
                    final int weight = Integer.parseInt(user_weight.getText().toString());
                    final String description = user_description.getText().toString();

                    User user = new User(userID,first,last,age,height,weight,description);

                    if(age < 0 || age > 120){
                        Toast.makeText(ProfileActivity.this, "Invalid Age", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(first) || TextUtils.isEmpty(last) || TextUtils.isEmpty(description)) {
                        Toast.makeText(ProfileActivity.this, "Complete all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }


                        //Put user profile information into database
/*                        myRef.child(user.getUserID()).child("First Name").setValue(user.getfName());
                        myRef.child(user.getUserID()).child("Last Name").setValue(user.getlName());
                        myRef.child(user.getUserID()).child("Age").setValue(user.getAge());
                        myRef.child(user.getUserID()).child("Height").setValue(user.getHeight());
                        myRef.child(user.getUserID()).child("Weight").setValue(user.getWeight());
                        myRef.child(user.getUserID()).child("Description").setValue(user.getDescription());*/
                        DBinstance.pushToTable(user.getUserID(), "First Name", user.getfName());
                        DBinstance.pushToTable(user.getUserID(), "Last Name", user.getlName());
                        DBinstance.pushToTable(user.getUserID(), "Age", user.getAge());
                        DBinstance.pushToTable(user.getUserID(), "Height", user.getHeight());
                        DBinstance.pushToTable(user.getUserID(), "Weight", user.getWeight());
                        DBinstance.pushToTable(user.getUserID(), "Description", user.getDescription());






                    finish();
                        startActivity(new Intent(getApplicationContext(),
                                FeedActivity.class));
                }
            });


        }

}

