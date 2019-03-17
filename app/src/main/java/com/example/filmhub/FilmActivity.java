package com.example.filmhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.List;

public class FilmActivity extends Fragment {

    static final String TAG = "MonTag";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ref;//= db.collection("Films").document("7FGtyF0zAAnAwCBvK7uD").collection("Review");
    private String id;
    View v;
    private ReviewAdapter adapter;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance ){
        super.onCreate(savedInstance);

        v = inflater.inflate(R.layout.activity_movie_review,container,false);

        final Bundle bundle = this.getArguments();

        if (bundle != null){

            String nomFilm;
            String realisateur;
            String genre;
            int annee;
            String description;
            String pays;
            String imageFilm;
            float note;
                Log.d(TAG,"recup film");
                Films film = bundle.getParcelable("film");
                nomFilm = film.getNomFilm();
                realisateur = film.getRealisateur();
                genre = film.getGenre();
                annee = film.getAnnee();
                description = film.getDescription();
                pays = film.getPays();
                imageFilm = film.getImageFilm();
                note = film.getNote();
                TextView film_titreTextView = (TextView) v.findViewById(R.id.film_titre);
                film_titreTextView.setText(nomFilm);
                TextView film_realisateurTextView = (TextView) v.findViewById(R.id.film_realisateur);
                film_realisateurTextView.setText(realisateur);
                TextView film_genreTextView = (TextView) v.findViewById(R.id.film_genre);
                film_genreTextView.setText(genre);
                TextView film_anneeTextView = (TextView) v.findViewById(R.id.film_annee);
                film_anneeTextView.setText(""+annee);
                TextView film_descriptionTextView = (TextView) v.findViewById(R.id.film_description);
                film_descriptionTextView.setText(description);
                TextView film_paysTextView = (TextView) v.findViewById(R.id.film_pays);
                film_paysTextView.setText(pays);
                ImageView film_afficheImageView = (ImageView) v.findViewById(R.id.film_affiche);
                Picasso.get().load(imageFilm).into(film_afficheImageView);
                RatingBar ratingBar = v.findViewById(R.id.film_noteGlobale);
                ratingBar.setRating(note);

                Log.d(TAG, "recup id");
                id = bundle.getString("id");


        }

        final Button button = v.findViewById(R.id.film_bouton_ecrire_review);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Touched ecrire une review");
                AddReview newFrag = new AddReview();
                Bundle bundleReview = new Bundle();
                newFrag.setArguments(bundleReview);

                Films film = bundle.getParcelable("film");

                bundleReview.putString("nomFilm", film.getNomFilm());
                bundleReview.putString("description", film.getDescription());
                bundleReview.putString("image", film.getImageFilm());
                bundleReview.putString("id", bundle.getString("id"));

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, newFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        setUpRecyclerView();

        return v;

    }

    private void setUpRecyclerView(){
        Log.d("Pierre","setUpRecyclerView");
        ref =db.collection("Films").document(id).collection("Review");
        Query query = ref.orderBy("note",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Review> options = new FirestoreRecyclerOptions.Builder<Review>()
                .setQuery(query,Review.class)
                .build();
        adapter = new ReviewAdapter(options);
        adapter.startListening();
        adapter.notifyDataSetChanged();
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.ReviewFilmRecyclerView);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }

}
