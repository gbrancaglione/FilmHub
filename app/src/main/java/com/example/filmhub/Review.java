package com.example.filmhub;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    private String auteur;
    private String texte;
    private Integer note;

    public Review(String auteur, String texte, Integer note) {
        this.auteur = auteur;
        this.texte = texte;
        this.note = note;
    }

    protected Review(Parcel in) {
        auteur = in.readString();
        texte = in.readString();
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

    public String getTexte() {
        return texte;
    }

    public Integer getNote() {
        return note;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setTexte(String texte) {
        this.texte = texte;
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
        dest.writeString(texte);
        dest.writeInt(note);
    }
}