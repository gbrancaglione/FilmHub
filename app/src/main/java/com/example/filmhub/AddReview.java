package com.example.filmhub;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class AddReview extends Fragment {

    private String TAG = "MonTag";
    View v;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText reviewEcriture;
    RatingBar note;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance ){
        super.onCreate(savedInstance);
        v = inflater.inflate(R.layout.add_review,container,false);


            Log.d(TAG,"recup film");

            String nomFilm =  getArguments().getString("nomFilm");
            String description = getArguments().getString("description");
            String imageFilm = getArguments().getString("image");

            TextView film_titreTextView = (TextView) v.findViewById(R.id.filmTitle);
            film_titreTextView.setText(nomFilm);

            TextView film_descriptionTextView = (TextView) v.findViewById(R.id.filmDescription);
            film_descriptionTextView.setText(description);

            ImageView film_afficheImageView = (ImageView) v.findViewById(R.id.filmImage);
            Picasso.get().load(imageFilm).into(film_afficheImageView);


        final Button button = v.findViewById(R.id.add_review);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Pierre","Touched add une review");
                reviewEcriture = (EditText) getView().findViewById(R.id.review_descrip);
                note = (RatingBar) getView().findViewById(R.id.note_review);
                Log.d("Pierre","Passed1");
                final String reviews = reviewEcriture.getText().toString();
                Log.d("Pierre","Passed2" + reviews);
                String noted = String.valueOf(note.getRating());

                int taille = noted.length();

                noted = noted.substring(0, taille - 2);

                int not = Integer.parseInt(noted);

                Log.d("Pierre","Passed3 "+ noted);
                writeNewReview(not , reviews);
                Log.d("Pierre","Note" + not);

                getActivity().onBackPressed();
            }
        });

        return v;


    }

    private void writeNewReview(int note, String reviewEcriture) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String nomFilm =  getArguments().getString("nomFilm");
            String emailUser = user.getEmail();

            Map<String, Object> review = new HashMap<>();
            review.put("auteur", emailUser);
            review.put("film", nomFilm);
            review.put("note", note);
            review.put("review", reviewEcriture);

            db.collection("Utilisateurs")
                    .document(emailUser)
                    .collection("Review")
                    .add(review)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Pierre", "Review written with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Pierre", "Error adding review", e);
                        }
                    });

            Map<String, Object> review2 = new HashMap<>();
            review.put("auteur", emailUser);
            review.put("note", note);
            review.put("review", reviewEcriture);

            String idFilm = getArguments().getString("id");

            db.collection("Films")
                    .document(idFilm)
                    .collection("Review")
                    .add(review)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Pierre", "Review written with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Pierre", "Error adding review", e);
                        }
                    });


        }
    }

}
