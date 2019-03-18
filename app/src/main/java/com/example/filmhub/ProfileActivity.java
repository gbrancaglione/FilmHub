package com.example.filmhub;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ProfileActivity extends Fragment {
    public Button buttonEditProfile;

    @Nullable
    JSONArray jsonArray = new JSONArray();
    public TextView nomUtilisateur;
    private List<Reviews> userReviews;
    private FirebaseAuth mAuth;
    private View v;
    private String id;
    private ReviewAdapter adapter;

    public  final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference ref;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstance);
        v = inflater.inflate(R.layout.activity_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        nomUtilisateur = v.findViewById(R.id.nomUtilisateurProfil);
        ref = db.collection("Utilisateurs");

        ref.whereEqualTo("adresseEmail", mAuth.getCurrentUser().getEmail()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override

                public void onSuccess (QuerySnapshot queryDocumentSnapshots){
                String idj = "";
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Log.d("holi", documentSnapshot.getId());
                        idj += "ID +" +documentSnapshot.getId();
                        getReviews(idj);
                    ref =FirebaseFirestore.getInstance().collection("Utilisateurs").document(documentSnapshot.getId()).collection("Review");

                    Query query = ref.orderBy("note",Query.Direction.DESCENDING);
                    FirestoreRecyclerOptions<Review> options = new FirestoreRecyclerOptions.Builder<Review>()
                            .setQuery(query,Review.class)
                            .build();
                    adapter = new ReviewAdapter(options);
                    adapter.notifyDataSetChanged();
                    RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.ReviewFilmProfilRecyclerView);
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                    adapter.startListening();


                }

            }

            });

        return v;

    }
    void getReviews(String document){
        id = document;
        nomUtilisateur.setText(mAuth.getCurrentUser().getEmail());


    }
    private void setUpRecyclerView(){



    }

    @Override
    public void onStart(){


        super.onStart();

    }
    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}



