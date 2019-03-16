package com.example.filmhub;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class listeFilms extends Fragment implements FilmsAdapter.OnItemClickListener {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ref=db.collection("Films");
    private FilmsAdapter adapter;
    private String orderBy;
    View v;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance ){
        super.onCreate(savedInstance);
        orderBy = getArguments().getString("orderby");

         v = inflater.inflate(R.layout.activity_liste_films,container,false);


        setUpRecyclerView();

        return v;

    }

   private void setUpRecyclerView(){
       Query query = ref.orderBy(orderBy,Query.Direction.DESCENDING);
       FirestoreRecyclerOptions<Films> options = new FirestoreRecyclerOptions.Builder<Films>()
               .setQuery(query,Films.class)
               .build();

       adapter = new FilmsAdapter(options);

       RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       recyclerView.setAdapter(adapter);

       adapter.setOnItemClickListener(new FilmsAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
               Films film = documentSnapshot.toObject(Films.class);
               String id = documentSnapshot.getId();
               Toast.makeText(getContext(),"Position : " +position + " ID : "+id,Toast.LENGTH_SHORT).show();
               Log.d("ID", "Le id : "+id+" fini");
               Fragment fragmentGet = new FilmActivity();
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.fragment_container, fragmentGet);
               fragmentTransaction.addToBackStack(null);
               fragmentTransaction.commit();
               Bundle bundle = new Bundle();
               bundle.putParcelable("film", film);
               bundle.putString("id", id);
               fragmentGet.setArguments(bundle);

           }
       });

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

    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

    }
}







