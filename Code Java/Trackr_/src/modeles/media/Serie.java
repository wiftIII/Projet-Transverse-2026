package modeles.media;

import modeles.user.Avis;

import java.util.*;

public class Serie extends Media {

    List<Episode> lesEpisodes;
    private int nombreEpisodes;

    public Serie(List<Avis> lesAvis, Categorie laCategorie, String titre, Date date, String realisateur, List<Episode> lesEpisodes, int nombreEpisodes) {
        super(lesAvis, laCategorie, titre, date, realisateur);
        this.lesEpisodes = lesEpisodes;
        this.nombreEpisodes = nombreEpisodes;
    }

    public void ajouterEpisode(Episode episode){
        this.lesEpisodes.add(episode);
    }

    @Override
    public String toString() {
        return "[SÉRIE] " + super.toString() + " | Épisodes: " + nombreEpisodes;
    }

    public List<Episode> getLesEpisodes() {
        return this.lesEpisodes;
    }

    public void setLesEpisodes(List<Episode> lesEpisodes) {
        this.lesEpisodes = lesEpisodes;
    }

    public int getNombreEpisodes() {
        return this.nombreEpisodes;
    }

    public void setNombreEpisodes(int nombreEpisodes) {
        this.nombreEpisodes = nombreEpisodes;
    }

}
