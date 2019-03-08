package com.example.filmhub;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ReviewAdapter extends FirestoreRecyclerAdapter<Films,FilmsAdapter.FilmsHolder> {



    public ReviewAdapter(@NonNull FirestoreRecyclerOptions<Films> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ReviewHolder holder, int position, @NonNull Films model) {
        holder.nomFilm.setText(model.getNomFilm());
        Picasso.get().load(model.getImageFilm()).into(holder.imageFilm);
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup,false);
        return new ReviewHolder(v);
    }

    class ReviewHolder extends RecyclerView.ViewHolder {
        ImageView imageFilm;
        TextView nomFilm,link;
        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            nomFilm = itemView.findViewById(R.id.nomFilm);
            imageFilm = itemView.findViewById(R.id.imageFilm);



        }
    }

}
