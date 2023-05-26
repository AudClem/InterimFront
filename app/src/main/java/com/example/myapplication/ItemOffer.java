package com.example.myapplication;

public class ItemOffer {

    String sous_titre;
    String titre;
    String salaire;
    String lieu;
    int affiche;
    int etoile;

    public ItemOffer(String sous_titre, String titre, int affiche, String salaire, String lieu, int etoile) {
        this.sous_titre = sous_titre;
        this.titre = titre;
        this.affiche = affiche;
        this.salaire = salaire;
        this.lieu = lieu;
        this.etoile = etoile;
    }

    public String getsous_titre() {
        return sous_titre;
    }

    public void setsous_titre(String sous_titre) {
        this.sous_titre = sous_titre;
    }

    public String getsalaire() {
        return salaire;
    }

    public void setsalaire(String salaire) {
        this.sous_titre = salaire;
    }

    public String gettitre() {
        return titre;
    }

    public void settitre(String titre) {
        this.titre = titre;
    }

    public String getlieu() {
        return lieu;
    }

    public void setlieu(String lieu) {
        this.lieu = lieu;
    }

    public int getaffiche() {
        return affiche;
    }

    public void setaffiche(int affiche) {
        this.affiche = affiche;
    }

    public int getetoile() {
        return etoile;
    }

    public void setetoile(int etoile) {
        this.etoile = etoile;
    }
}
