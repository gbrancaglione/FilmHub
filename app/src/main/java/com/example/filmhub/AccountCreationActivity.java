package com.example.filmhub;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AccountCreationActivity extends AppCompatActivity {

    private String TAG = "MonTag";
    EditText email,password, passwordVerifi;
    Button registerButton,loginButton;
    FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "in account creation on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountcreation);

        email = (AutoCompleteTextView) findViewById(R.id.EmailCreation);
        password = (AutoCompleteTextView) findViewById(R.id.PasswordCreation);
        passwordVerifi = (AutoCompleteTextView) findViewById(R.id.PasswordVerif);
        registerButton = (Button) findViewById(R.id.registerButton);

        firebaseAuth = FirebaseAuth.getInstance();

        TextView TextLinkCreation = (TextView) findViewById(R.id.TextLinkCreation);
        TextLinkCreation.setPaintFlags(TextLinkCreation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString();
                String passwords = password.getText().toString();
                String passwordVerif = passwordVerifi.getText().toString();

                if(TextUtils.isEmpty(emails)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passwords)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                }

                if(passwords.length()<6){
                    Toast.makeText(getApplicationContext(),"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                }
                if(passwords.equals(passwordVerifi) != true) {
                    
                }



                firebaseAuth.createUserWithEmailAndPassword(emails,passwords)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });


    }
}
