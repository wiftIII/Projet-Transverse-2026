package modeles.media;

import modeles.user.Avis;

import java.util.Date;
import java.util.List;

public class Film extends Media {

    Film filmPrecedent;
    Film filmSuivant;
    private String duree;


    public Film(Categorie laCategorie, String titre, Date date, String realisateur, Film precedent, Film suivant, String duree) {
        super(laCategorie, titre, date, realisateur);
        this.filmPrecedent = precedent;
        this.filmSuivant = suivant;
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "[FILM] " + super.toString() + " | Durée: " + duree;
    }

    public Film getFilmPrecedent() {
        return this.filmPrecedent;
    }

    public void setFilmPrecedent(Film filmPrecedent) {
        this.filmPrecedent = filmPrecedent;
    }

    public Film getFilmSuivant() {
        return this.filmSuivant;
    }

    public void setFilmSuivant(Film filmSuivant) {
        this.filmSuivant = filmSuivant;
    }

    public String getDuree() {
        return this.duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

}