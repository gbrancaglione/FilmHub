package com.example.filmhub;

public class Reviews {

    private String auteur;
    private String commentaire;
    private int note;

    public String getAuteur() { return auteur;}
    public String getCommentaire(){return commentaire;}
    public int getNote() { return note; }

    public Reviews(String auteur, String commentaire, int note){
        this.auteur=auteur;
        this.commentaire=commentaire;
        this.note=note;
    }
}
