package modeles.user;

import modeles.media.Media;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Playlist {

    List<Media> lesMedias;
    private String nom;
    private Date dateCreation;
    private boolean estPrive;
    User createur;

    public Playlist(String nom, User createur, Boolean estPrive) {
        this.lesMedias = new ArrayList<>();
        this.nom = nom;
        this.dateCreation = new Date();
        this.estPrive = estPrive;
        this.createur = createur;
    }

    public Playlist(List<Media> lesMedias, String nom, Date dateCreation, boolean estPrive, User createur) {
        this.lesMedias = lesMedias;
        this.nom = nom;
        this.dateCreation = dateCreation;
        this.estPrive = estPrive;
        this.createur = createur;
    }

    public void ajouterMedia(Media media) {
        if (!lesMedias.contains(media)) {
            lesMedias.add(media);
            System.out.println("O" + media.getTitre() + "' a été ajouté à la playlist '" + this.nom);
        } else {
            System.out.println("!!! Ce média est déjà dans la playlist !!!");
        }
    }

    public void retirerMedia(Media media) {
        if (lesMedias.remove(media)) {
            System.out.println("X" + media.getTitre() + "' a été retiré de la playlist '" + this.nom);
        }
    }

    public void afficherContenu() {
        System.out.println("\n=== Playlist : " + nom + " (" + (estPrive ? "Privée" : "Publique") + ") ===");
        if (lesMedias.isEmpty()) {
            System.out.println("(Vide)");
        } else {
            for (Media m : lesMedias) {
                System.out.println("---> " + m.getTitre());
            }
        }
        System.out.println("===============================\n");
    }

    public void tooglePrive(){
        this.estPrive = !this.estPrive;
    }

    public List<Media> getLesMedias() {
        return this.lesMedias;
    }

    public void setLesMedias(List<Media> lesMedias) {
        this.lesMedias = lesMedias;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isEstPrive() {
        return this.estPrive;
    }

    public void setEstPrive(boolean estPrive) {
        this.estPrive = estPrive;
    }

    public User getCreateur() {
        return this.createur;
    }

    public void setCreateur(User createur) {
        this.createur = createur;
    }

}