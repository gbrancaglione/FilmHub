package com.example.filmhub;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    private String auteur;
    private String review;
    private Integer note;

    public Review() {

    }

    public Review(String auteur, Integer note, String review) {
        this.auteur = auteur;
        this.note = note;
        this.review = review;
    }

    protected Review(Parcel in) {
        auteur = in.readString();
        if (in.readByte() == 0) {
            note = null;
        } else {
            note = in.readInt();
        }
        review = in.readString();
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

    public String getAuteur() { return auteur; }

    public Integer getNote() {
        return note;
    }

    public String getReview() {
        return review;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public void setReview(String texte) {
        this.review = texte;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(auteur);
        dest.writeInt(note);
        dest.writeString(review);
    }
}