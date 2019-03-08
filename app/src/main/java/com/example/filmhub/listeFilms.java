package com.example.filmhub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class listeFilms extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ref=db.collection("Films");
    private FilmsAdapter adapter;
    View v;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance ){
        super.onCreate(savedInstance);


         v = inflater.inflate(R.layout.activity_liste_films,container,false);
        setUpRecyclerView();

        return v;

    }

   private void setUpRecyclerView(){
       Query query = ref.orderBy("nomFilm",Query.Direction.DESCENDING);
       FirestoreRecyclerOptions<Films> options = new FirestoreRecyclerOptions.Builder<Films>()
               .setQuery(query,Films.class)
               .build();
       adapter = new FilmsAdapter(options);
       RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
       recyclerView.setHasFixedSize(true);
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








