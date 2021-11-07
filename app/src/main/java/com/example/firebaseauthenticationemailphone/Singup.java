package com.example.firebaseauthenticationemailphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Singup extends AppCompatActivity {

    EditText ee;
    EditText pp;
    Button bm;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        ee = findViewById(R.id.email2);
        pp = findViewById(R.id.phno2);
        bm = findViewById(R.id.btt);

        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Useremail = ee.getText().toString();
                String UserPassword = pp.getText().toString();
                signupfirebase(Useremail,UserPassword);
            }
        });
    }

    public void signupfirebase(String Useremail, String UserPassword)
    {

        auth.createUserWithEmailAndPassword(Useremail, UserPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(Singup.this, "Your Account is created Succesfully ", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Singup.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Singup.this, "Something went to wrong ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}