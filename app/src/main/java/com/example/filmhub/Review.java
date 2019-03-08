package com.example.filmhub;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    private String auteur;
    private String review;
    private Integer note;

    public Review() {

    }

    public Review(String auteur, String review, Integer note) {
        this.auteur = auteur;
        this.review = review;
        this.note = note;
    }

    protected Review(Parcel in) {
        auteur = in.readString();
        review = in.readString();
        if (in.readByte() == 0) {
            note = null;
        } else {
            note = in.readInt();
        }
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getAuteur() {
        return auteur;
    }

    public String getReview() {
        return review;
    }

    public Integer getNote() {
        return note;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setReview(String texte) {
        this.review = texte;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(auteur);
        dest.writeString(review);
        dest.writeInt(note);
    }
}