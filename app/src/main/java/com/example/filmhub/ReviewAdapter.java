package com.example.filmhub;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ReviewAdapter extends FirestoreRecyclerAdapter<Review,ReviewAdapter.ReviewHolder> {

    private static final String TAG = "MonTag";
    private OnItemClickListener listener;

    public ReviewAdapter(@NonNull FirestoreRecyclerOptions<Review> _options) {
        super(_options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ReviewHolder holder, int position, @NonNull Review model) {
        holder.review_auteurTextView.setText(model.getAuteur());
        holder.review_filmTextView.setText(model.getFilm());
        holder.review_contentTextView.setText(model.getReview());
        holder.review_ratingRatingBar.setRating((float) model.getNote());
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "Create ViewHolder");
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_review,
                viewGroup,false);
        return new ReviewHolder(v);
    }

    public void setOnItemClickListener(FilmsAdapter.OnItemClickListener onItemClickListener) {
    }


    class ReviewHolder extends RecyclerView.ViewHolder {
        TextView review_auteurTextView;
        TextView review_contentTextView;
        TextView review_filmTextView;
        RatingBar review_ratingRatingBar;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            review_auteurTextView = itemView.findViewById(R.id.review_auteur);
            review_contentTextView = itemView.findViewById(R.id.review_content);
            review_ratingRatingBar = itemView.findViewById(R.id.review_rating);
            review_filmTextView = itemView.findViewById(R.id.nomFilmReview);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION && listener!= null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }

                }
            });

        }

    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(ReviewAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onError(FirebaseFirestoreException e){
        Log.d("Pierre", e.getMessage());
    }

}
