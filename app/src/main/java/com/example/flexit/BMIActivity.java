
//Import necessary package and file
package com.example.flexit;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.app.Activity;

//Main activity class start here
public class BMIActivity extends Activity {


    TextView height, weight, result, status;
    Button run;
    DatabaseReference reff;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        status= (TextView) findViewById(R.id.status);

        height = (TextView) findViewById(R.id.heightview);
        weight = (TextView) findViewById(R.id.weighttview);
        run = (Button) findViewById(R.id.run_bmi);
        result = (TextView) findViewById(R.id.resultView);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        final String userID = user.getUid();

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reff = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String Height = dataSnapshot.child("Height").getValue().toString();
                        String Weight = dataSnapshot.child("Weight").getValue().toString();

                        height.setText(Height);
                        weight.setText(Weight);


                        final int userweight = Integer.parseInt(Weight.toString());
                        final int userheight = Integer.parseInt(Height.toString());




//Calculate BMI value
                        final int bmiValue = getUserBMI(userweight, userheight);

                        String setBMI = showUserBMI(bmiValue);

                        status.setText(String.valueOf(getUserBMI(userweight,userheight)));
                        result.setText(String.valueOf(setBMI));



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


        //Calculate BMI
    }



    //Calculate BMI

    private int getUserBMI(final int userweight, final int userheight) {
        return (int) ((userweight * 703) / (userheight * userheight));
    }

    // Interpret what BMI means
    private String showUserBMI(float bmiValue) {

        if (bmiValue < 18.5) {
            return "underweight";
        } else if (bmiValue < 25) {

            return "normal";
        } else if (bmiValue < 30) {

            return "overweight";
        } else if (bmiValue < 40) {

            return "Obese";
        } else {
            return "Morebidly Obese";
        }
    }

}