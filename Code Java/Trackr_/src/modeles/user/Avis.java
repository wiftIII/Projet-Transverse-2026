package modeles.user;

import modeles.media.Media;

import java.util.Date;

public class Avis {

    User createur;
    Media mediaAssocie;
    private Date dateDeCreation;
    private String commentaire;
    private int nombreEtoiles;

    public Avis(User createur, Media mediaAssocie, String commentaire, int nombreEtoiles) {
        this.createur = createur;
        this.mediaAssocie = mediaAssocie;
        this.dateDeCreation = new Date();
        this.commentaire = commentaire;
        this.nombreEtoiles = nombreEtoiles;
    }

    public Avis(User createur, Media mediaAssocie, Date dateDeCreation, String commentaire, int nombreEtoiles) {
        this.createur = createur;
        this.mediaAssocie = mediaAssocie;
        this.dateDeCreation = dateDeCreation;
        this.commentaire = commentaire;
        this.nombreEtoiles = nombreEtoiles;
    }

    public void publier() {
        if (!mediaAssocie.getLesAvis().contains(this)) {
            mediaAssocie.getLesAvis().add(this);
        }
        if (!createur.getSesAvis().contains(this)) {
            createur.getSesAvis().add(this);
        }
        System.out.println("Avis publié par " + createur.getPseudo() + " sur '" + mediaAssocie.getTitre() + "' (" + nombreEtoiles + "/5)");
    }

    @Override
    public String toString() {
        return createur.getPseudo() + " [" + nombreEtoiles + "étoiles : " + commentaire;
    }

    public User getCreateur() { return this.createur; }

    public void setCreateur(User createur) { this.createur = createur; }

    public Media getMediaAssocie() {
        return this.mediaAssocie;
    }

    public void setMediaAssocie(Media mediaAssocie) {
        this.mediaAssocie = mediaAssocie;
    }

    public Date getDateDeCreation() {
        return this.dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNombreEtoiles() {
        return this.nombreEtoiles;
    }

    public void setNombreEtoiles(int nombreEtoiles) {
        this.nombreEtoiles = nombreEtoiles;
    }

}