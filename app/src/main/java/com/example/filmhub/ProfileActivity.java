package com.example.filmhub;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ProfileActivity extends Fragment {

    @Nullable
    JSONArray jsonArray = new JSONArray();
    public TextView list;
    private List<Reviews> userReviews;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,  Bundle savedInstance ){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstance);


        /* get datas from FireBase part*/
        String email = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            email = user.getEmail();
        }
        Query userReviews = db.collection("Review").whereEqualTo(email, true);


        db.collection("Review")
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


        return inflater.inflate(R.layout.activity_profile,container,false);

    }

    void processData (JSONArray obj){
        jsonArray=obj;
        String auteur;
        String commentaire;
        int note;

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d(TAG, "json Object = json Array ");

                auteur= jsonObject.getString("auteur");
                commentaire = jsonObject.getString("commentaire");
                note= jsonObject.getInt("note");
                Log.d(TAG, "get auteur, commentaire and note from jsonObject");

                userReviews.add(
                        new Reviews(auteur,commentaire,note));
                    Log.d(TAG, "start new Review adding to userReviews list");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Log.d(TAG,jsonArray.toString());
        list = getView().findViewById(R.id.reviewsList);
        Log.d(TAG, "list = to reviewsList layout");

        list.setText(jsonArray.toString());
        Log.d(TAG, "set text jsonArray to list");

    }

}
