package com.example.filmhub;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;


public class listeFilms extends Fragment {
    @Nullable
    JSONArray jsonArray = new JSONArray();
    public TextView list;
    private List<Films> films;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,  Bundle savedInstance ){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstance);

        db.collection("Films")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            JSONArray jsonArray = new JSONArray();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                jsonArray.put(document.getData());
                            }
                            processData(jsonArray);
                        } else {
                            Log.d(TAG, "Error getting documents.", task.getException());
                        }

                    }

                });
        return inflater.inflate(R.layout.activity_liste_films,container,false);

    }
    void processData (JSONArray obj){
        jsonArray=obj;
         String nomFilm;
         String realisateur;
         String genre;
         String annee;
         String description;
         String pays;
         String image;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                nomFilm= jsonObject.getString("nomFilm");
                realisateur = jsonObject.getString("name");
                genre = jsonObject.getString("genre");
                annee = jsonObject.getString("annee");
                pays = jsonObject.getString("pays");
                image = jsonObject.getString("image");
                description = jsonObject.getString("description");
                films.add(
                            new Films(nomFilm,realisateur,  genre,  annee,  description,  pays,  image));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Log.d(TAG,jsonArray.toString());
        list = getView().findViewById(R.id.reviewsList);
        list.setText(jsonArray.toString());

    }

    }


