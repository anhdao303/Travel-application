package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText name_up, email_up, phone_up, password_up;
    Button btnSignUp;
    TextView signInRedict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name_up = findViewById(R.id.username);
        email_up = findViewById(R.id.email);
        phone_up = findViewById(R.id.phone_signup);
        password_up = findViewById(R.id.password_signup);
        btnSignUp = findViewById(R.id.btnSignUp);
        signInRedict = findViewById(R.id.signInRedict);


        btnSignUp.setOnClickListener(v -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("user");

            String name = name_up.getText().toString();
            String email = email_up.getText().toString();
            String phone = phone_up.getText().toString();
            String pass = password_up.getText().toString();

            if(!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !pass.isEmpty()){

            }
            User users = new User(name, email, phone, pass);
            reference.child(name).setValue(users);

            Toast.makeText(SignUp.this, "You have sign up successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUp.this, BottomNavigation.class);
            startActivity(intent);

        });

        signInRedict.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });
    }
}