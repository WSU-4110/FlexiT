package com.example.flexit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText password;
    private EditText email;
    private Button button_register;

    private FirebaseDatabase dataBase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.signup_email_input);
        password =(EditText) findViewById(R.id.signup_password_input);
        button_register = (Button)findViewById(R.id.button_register);

        mAuth = FirebaseAuth.getInstance();

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button_register ){
                    RegisterUser();
                }
            }
        });
    }

    public void RegisterUser(){
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        //If both fields are empty
        if(TextUtils.isEmpty(Email) && TextUtils.isEmpty(Password)){
            Toast.makeText(RegisterActivity.this, "Both fields are empty",
                            Toast.LENGTH_SHORT).show();
            return;
        }
        //IF Email field is empty
        if(TextUtils.isEmpty(Email)){
            Toast.makeText(RegisterActivity.this, "Email cannot be empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //IF Password field is empty
        if(TextUtils.isEmpty(Password)){
            Toast.makeText(RegisterActivity.this, "Please enter a password",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Registering the user
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            if(task.isSuccessful()){
                                //User registration is successful
                                //start profile activity here
                                Toast.makeText(RegisterActivity.this, "Registration Successful",
                                                Toast.LENGTH_SHORT).show();
                                finish(); //finish activity
                                //and start ProfileActivity
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Couldn't register, try again",
                                                Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
}
