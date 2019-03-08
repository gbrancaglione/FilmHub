package com.example.filmhub;

public class Films {
    private String nomFilm;
    private String realisateur;
    private String genre;
    private int annee;
    private String description;
    private String pays;
    private String imageFilm;

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
}
