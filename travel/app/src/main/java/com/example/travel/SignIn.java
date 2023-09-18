package com.example.travel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SignIn extends AppCompatActivity {
    EditText phone_in, pass_in;
    Button btnSignIn;
    TextView signUpRedict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        pass_in = findViewById(R.id.password_signin);
        phone_in = findViewById(R.id.phone_signin);
        btnSignIn = findViewById(R.id.btnSignIn);
        signUpRedict = findViewById(R.id.signUpRedict);

        btnSignIn.setOnClickListener(v -> {
            if(!validatePassword() | !validatePhone()){

            } else {
                checkUser();
            }
        });

        signUpRedict.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        });

    }

    public boolean validatePhone(){
        String val = phone_in.getText().toString();
        if(val.isEmpty()){
            phone_in.setError("Phone number cannot be empty");
            return false;
        } else {
            phone_in.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String val = pass_in.getText().toString();
        if(val.isEmpty()){
            pass_in.setError("Password cannot be empty");
            return false;
        } else {
            pass_in.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String phone = phone_in.getText().toString().trim();
        String pass = pass_in.getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUserDatabase = databaseReference.orderByChild("phone").equalTo(phone);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    phone_in.setError(null);
                    String passDatabase = snapshot.child(phone).child("password").getValue(String.class);

                    if(passDatabase.equals(pass)){
                        phone_in.setError(null);

                        String nameDatabase = snapshot.child(phone).child("name").getValue(String.class);
                        String emailDatabase = snapshot.child(phone).child("email").getValue(String.class);
                        String phoneDatabase = snapshot.child(phone).child("phone").getValue(String.class);

                        Intent intent = new Intent(SignIn.this, BottomNavigation.class);

                        intent.putExtra("name", nameDatabase);
                        intent.putExtra("email", emailDatabase);
                        intent.putExtra("phone", phoneDatabase);
                        intent.putExtra("password", passDatabase);

                        startActivity(intent);

                    } else {
                        pass_in.setError("Invalid credentials");
                        pass_in.requestFocus();
                    }
                } else {
                    phone_in.setError("User does not exist");
                    phone_in.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}