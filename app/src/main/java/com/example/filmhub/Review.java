package com.example.filmhub;

public class Review {
    private String auteur;
    private String texte;
    private Integer note;

    public Review(String auteur, String texte, Integer note) {
        this.auteur = auteur;
        this.texte = texte;
        this.note = note;
    }

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
}