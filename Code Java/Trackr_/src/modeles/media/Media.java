package modeles.media;

import modeles.user.Avis;
import modeles.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Media {

    List<Avis> lesAvis;
    Categorie laCategorie;
    private String titre;
    private Date date;
    private String realisateur;


    public Media(Categorie laCategorie, String titre, Date date, String realisateur) {
        this.lesAvis = new ArrayList<>();
        this.laCategorie = laCategorie;
        this.titre = titre;
        this.date = date;
        this.realisateur = realisateur;
    }

    public Media(List<Avis> lesAvis, Categorie laCategorie, String titre, Date date, String realisateur) {
        this.lesAvis = lesAvis;
        this.laCategorie = laCategorie;
        this.titre = titre;
        this.date = date;
        this.realisateur = realisateur;
    }

    public double getScoreMoyen() {
        if (lesAvis == null || lesAvis.isEmpty()) {
            return 0.0;
        }
        double somme = 0;
        for (Avis avis : lesAvis) {
            somme += avis.getNombreEtoiles();
        }
        return somme / lesAvis.size();
    }

    public void newAvis(User userCreateur, String commentaire, int nbEtoiles){
        new Avis(userCreateur, this, commentaire, nbEtoiles).publier();
    }

    @Override
    public String toString() {
        return String.format("%s (%tY) - %s | Genre: %s | Note: %.1f/5 (%d avis)",
                titre, date, realisateur, laCategorie, getScoreMoyen(), lesAvis != null ? lesAvis.size() : 0);
    }

    public List<Avis> getLesAvis() {
        return this.lesAvis;
    }

    public void setLesAvis(List<Avis> lesAvis) {
        this.lesAvis = lesAvis;
    }

    public Categorie getLaCategorie() {
        return this.laCategorie;
    }

    public void setLaCategorie(Categorie laCategorie) {
        this.laCategorie = laCategorie;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRealisateur() {
        return this.realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

}