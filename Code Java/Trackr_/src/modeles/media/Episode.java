package modeles.media;

import modeles.user.Avis;

import java.util.Date;
import java.util.List;

public class Episode extends Media {

    Serie serieMere;
    Episode episodeSuivant;
    Episode episodePrecedent;
    private int numeroDeSaison;
    private String duree;


    public Episode(Categorie laCategorie, String titre, Date date, String realisateur, Serie serieMere, Episode episodeSuivant, Episode episodePrecedent, int numeroDeSaison, String duree) {
        super(laCategorie, titre, date, realisateur);
        this.serieMere = serieMere;
        this.episodeSuivant = episodeSuivant;
        this.episodePrecedent = episodePrecedent;
        this.numeroDeSaison = numeroDeSaison;
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "[ÉPISODE] S" + numeroDeSaison + " - " + super.getTitre() + " | Durée: " + duree;
    }

    public Serie getSerieMere() {
        return this.serieMere;
    }

    public void setSerieMere(Serie serieMere) {
        this.serieMere = serieMere;
    }

    public Episode getEpisodeSuivant() {
        return this.episodeSuivant;
    }

    public void setEpisodeSuivant(Episode episodeSuivant) {
        this.episodeSuivant = episodeSuivant;
    }

    public Episode getEpisodePrecedent() {
        return this.episodePrecedent;
    }

    public void setEpisodePrecedent(Episode episodePrecedent) {
        this.episodePrecedent = episodePrecedent;
    }

    public int getNumeroDeSaison() {
        return this.numeroDeSaison;
    }

    public void setNumeroDeSaison(int numeroDeSaison) {
        this.numeroDeSaison = numeroDeSaison;
    }

    public String getDuree() {
        return this.duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

}