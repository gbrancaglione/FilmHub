package com.example.filmhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;

import java.util.List;

import javax.annotation.Nullable;

public class FilmActivity extends Fragment {

    static final String TAG = "MonTag";

    @Nullable
    JSONArray jsonArray = new JSONArray();
    public TextView list;
    private List<Review> reviews;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.film_bouton_ecrire_review);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Touched ecrire une review");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
                //Intent i = new Intent(MainActivity.this, FilmActivity.class);
                //Log.d(TAG,"Go to film reviews");
                //startActivity(i);
            }
        });

    } */


    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstance ){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstance);

        View view = inflater.inflate(R.layout.activity_movie_review, container, false);

        Button button = (Button) view.findViewById(R.id.film_bouton_ecrire_review);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Touched ecrire une review");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Intent i = new Intent(MainActivity.this, FilmActivity.class);
                //Log.d(TAG,"Go to film reviews");
                //startActivity(i);
            }
        });

        return inflater.inflate(R.layout.activity_movie_review,container,false);}
}
