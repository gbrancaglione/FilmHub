package com.example.filmhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

import javax.annotation.Nullable;

public class FilmActivity extends AppCompatActivity {

    static final String TAG = "MonTag";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ref=db.collection("Films").document("7FGtyF0zAAnAwCBvK7uD").collection("Review");
    private ReviewAdapter adapter;
    View v;

    @Nullable
    JSONArray jsonArray = new JSONArray();
    public TextView list;
    private List<Review> reviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (i != null){

            String nomFilm;
            String realisateur;
            String genre;
            int annee;
            String description;
            String pays;
            String imageFilm;
            if (i.hasExtra("film")) {
                Log.d(TAG,"recup film");
                Films film = bundle.getParcelable("film");
                nomFilm = film.getNomFilm();
                realisateur = film.getRealisateur();
                genre = film.getGenre();
                annee = film.getAnnee();
                description = film.getDescription();
                pays = film.getPays();
                imageFilm = film.getImageFilm();
                TextView film_titreTextView = (TextView) findViewById(R.id.film_titre);
                film_titreTextView.setText(nomFilm);
                TextView film_realisateurTextView = (TextView) findViewById(R.id.film_realisateur);
                film_realisateurTextView.setText(realisateur);
                TextView film_genreTextView = (TextView) findViewById(R.id.film_genre);
                film_genreTextView.setText(genre);
                TextView film_anneeTextView = (TextView) findViewById(R.id.film_annee);
                film_anneeTextView.setText(""+annee);
                TextView film_descriptionTextView = (TextView) findViewById(R.id.film_description);
                film_descriptionTextView.setText(description);
                TextView film_paysTextView = (TextView) findViewById(R.id.film_pays);
                film_paysTextView.setText(pays);
                ImageView film_afficheImageView = (ImageView) findViewById(R.id.film_affiche);
                Picasso.get().load(imageFilm).into(film_afficheImageView);
            }

        }

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

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = ref.orderBy("note",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Review> options = new FirestoreRecyclerOptions.Builder<Review>()
                .setQuery(query,Review.class)
                .build();

        adapter = new ReviewAdapter(options);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ReviewFilmRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        /*adapter.setOnItemClickListener(new FilmsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Films film = documentSnapshot.toObject(Films.class);
                String id = documentSnapshot.getId();
                Toast.makeText(this,"Position : " +position + " ID : "+id,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, FilmActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("film", film);
                i.putExtras(bundle);
                startActivity(i);
            }
        });*/

    }

}
