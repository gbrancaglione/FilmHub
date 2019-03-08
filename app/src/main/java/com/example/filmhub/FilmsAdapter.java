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

public class FilmsAdapter extends FirestoreRecyclerAdapter<Films,FilmsAdapter.FilmsHolder> {



    public FilmsAdapter(@NonNull FirestoreRecyclerOptions<Films> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FilmsHolder holder, int position, @NonNull Films model) {
        holder.nomFilm.setText(model.getNomFilm());
        Picasso.get().load(model.getImageFilm()).into(holder.imageFilm);
    }

    @NonNull
    @Override
    public FilmsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup,false);
        return new FilmsHolder(v);
    }

    class FilmsHolder extends RecyclerView.ViewHolder {
        ImageView imageFilm;
        TextView nomFilm,link;
        public FilmsHolder(@NonNull View itemView) {
            super(itemView);
            nomFilm = itemView.findViewById(R.id.nomFilm);
            imageFilm = itemView.findViewById(R.id.imageFilm);



        }
    }

}
