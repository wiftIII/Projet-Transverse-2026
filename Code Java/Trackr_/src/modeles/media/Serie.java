package modeles.media;

import modeles.user.Avis;

import java.util.*;

public class Serie extends Media {

    List<Episode> lesEpisodes;
    private int nombreEpisodes;

    public Serie(Categorie laCategorie, String titre, Date date, String realisateur, int nombreEpisodes) {
        super(laCategorie, titre, date, realisateur);
        this.lesEpisodes = new ArrayList<>();
        this.nombreEpisodes = nombreEpisodes;
    }

    public void ajouterEpisode(Episode episode){
        this.lesEpisodes.add(episode);
        this.nombreEpisodes ++;
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
