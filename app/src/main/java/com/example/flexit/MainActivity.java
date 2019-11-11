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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText password;
    private EditText email;
    private Button button_register;
    private Button button_login;
    private FirebaseDatabase dataBase;
    private DatabaseReference myRef;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.login_email_input);
        password =(EditText) findViewById(R.id.login_password_input);
        button_register = (Button)findViewById(R.id.button_register);
        button_login = (Button)findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        dataBase = FirebaseDatabase.getInstance();
        myRef = dataBase.getReference("Users");




        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button_register ){
                    RegisterUser();
                }
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button_login){
                    startActivity(new Intent(getApplicationContext(),
                            LoginActivity.class));
                }
            }
        });
    }
    public  void RegisterUser(){
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Email)){
            Toast.makeText(this, "A field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "A field is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        try{
                            //check if registration was successful
                            if(task.isSuccessful()){
                                //User registration is succcessful
                                //start profile activity here
                                Toast.makeText(MainActivity.this, "Registration successful",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Couldn't register, try again",
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
