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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    EditText mail;
    EditText phoneno;
    Button singup;
    Button singin;
    Button forgetpassword;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail = findViewById(R.id.email1);
        phoneno = findViewById(R.id.no1);
        singup = findViewById(R.id.bt2);
        singin = findViewById(R.id.bt1);
        forgetpassword = findViewById(R.id.btforget);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usermail = mail.getText().toString();
                String userPassword = phoneno.getText().toString();
                signinfirebase(usermail, userPassword);

            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Singup.class);
                startActivity(i);
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Forgetactivity.class);
                startActivity(i);
            }
        });
    }
    public void signinfirebase (String usermail, String userpassword)
    {

        mAuth.signInWithEmailAndPassword(usermail, userpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          Intent i = new Intent(MainActivity.this, HOmePage.class);
                          startActivity(i);
                          finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Mail or password incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null)
        {
            Intent i = new Intent(MainActivity.this, HOmePage.class);
            startActivity(i);
            finish();
        }
    }
}