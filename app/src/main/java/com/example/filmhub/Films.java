package com.example.filmhub;

public class Films {
    private String nomFilm;
    private String realisateur;
    private String genre;
    private String annee;
    private String description;
    private String pays;
    private String image;

    public String getNomFilm() {
        return nomFilm;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public String getGenre() {
        return genre;
    }

    public String getAnnee() {
        return annee;
    }

    public String getDescription() {
        return description;
    }

    public String getPays() {
        return pays;
    }

    public String getImage() {
        return image;
    }

    public Films(String nomFilm, String realisateur, String genre, String annee, String description, String pays, String image) {
        this.nomFilm = nomFilm;
        this.realisateur = realisateur;
        this.genre = genre;
        this.annee = annee;
        this.description = description;
        this.pays = pays;
        this.image = image;
    }
}
