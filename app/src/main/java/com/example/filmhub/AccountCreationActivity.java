package com.example.filmhub;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AccountCreationActivity extends AppCompatActivity {

    private String TAG = "MonTag";

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "in account creation on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountcreation);

        TextView TextLinkCreation = (TextView) findViewById(R.id.TextLinkCreation);
        TextLinkCreation.setPaintFlags(TextLinkCreation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextLinkCreation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
    }
}
