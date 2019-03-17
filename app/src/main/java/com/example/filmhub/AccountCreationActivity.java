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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"unused", "Convert2Lambda"})
public class AccountCreationActivity extends AppCompatActivity {

    EditText email, password, passwordVerifi, nameCrea;
    Button registerButton, loginButton;
    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private String TAG = "MonTag";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private void writeNewUser(String name, String email) {

        Map<String, Object> user = new HashMap<>();
        user.put("adresseEmail", email);
        user.put("nomUtilisateur", name);

        db.collection("Utilisateurs")
                .document(email)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.w(TAG, "User registered successfully !");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding user", e);
                    }
                });

    }

    public void setup() {
        // [START get_firestore_instance]
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // [END get_firestore_instance]

        // [START set_firestore_settings]
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        // [END set_firestore_settings]
    }


    protected void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "in account creation on create");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_accountcreation);

    mDatabase = FirebaseDatabase.getInstance().getReference();

    email = (EditText) findViewById(R.id.EmailCreation);
    password = (EditText) findViewById(R.id.PasswordCreation);
    registerButton = (Button) findViewById(R.id.register_button);
    nameCrea = (EditText) findViewById(R.id.NameCreation);

    firebaseAuth = FirebaseAuth.getInstance();

    TextView TextLinkCreation = (TextView) findViewById(R.id.TextLinkCreation);
    TextLinkCreation.setPaintFlags(TextLinkCreation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    TextLinkCreation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    });

    registerButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String emails = email.getText().toString();
            String passwords = password.getText().toString();

            if (TextUtils.isEmpty(emails)) {
                Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(passwords)) {
                Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
            }

            if (passwords.length() < 6) {
                Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            }

            firebaseAuth.createUserWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String names = nameCrea.getText().toString();
                                writeNewUser(names,emails);
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            }
                        }
                    });


        }
    });


}
}

