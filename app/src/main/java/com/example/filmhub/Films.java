package com.example.filmhub;

import android.os.Parcel;
import android.os.Parcelable;

public class Films implements Parcelable {
    private String nomFilm;
    private String realisateur;
    private String genre;
    private int annee;
    private String description;
    private String pays;
    private String imageFilm;

    protected Films(Parcel in) {
        nomFilm = in.readString();
        realisateur = in.readString();
        genre = in.readString();
        annee = in.readInt();
        description = in.readString();
        pays = in.readString();
        imageFilm = in.readString();
    }

    public static final Creator<Films> CREATOR = new Creator<Films>() {
        @Override
        public Films createFromParcel(Parcel in) {
            return new Films(in);
        }

        @Override
        public Films[] newArray(int size) {
            return new Films[size];
        }
    };

    public String getNomFilm() {
        return nomFilm;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public String getGenre() {
        return genre;
    }

    public int getAnnee() {
        return annee;
    }

    public String getDescription() {
        return description;
    }

    public String getPays() {
        return pays;
    }

    public String getImageFilm() {
        return imageFilm;
    }

    public Films(){

    }

    public Films(String nomFilm, String realisateur, String genre, int annee, String description, String pays, String imageFilm) {
        this.nomFilm = nomFilm;
        this.realisateur = realisateur;
        this.genre = genre;
        this.annee = annee;
        this.description = description;
        this.pays = pays;
        this.imageFilm = imageFilm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomFilm);
        dest.writeString(realisateur);
        dest.writeString(genre);
        dest.writeInt(annee);
        dest.writeString(description);
        dest.writeString(pays);
        dest.writeString(imageFilm);
    }
}
