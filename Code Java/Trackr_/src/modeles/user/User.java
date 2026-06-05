package modeles.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modeles.media.Media;

public class User {

    List<Avis> sesAvis;
    List<Playlist> mesPlaylists;
    private String pseudo;
    private String mail;
    List<User> follower;
    List<User> suivi;
    Playlist vu;
    Playlist like;


    public User(String pseudo, String mail) {
        this.pseudo = pseudo;
        this.mail = mail;

        this.sesAvis = new ArrayList<>();
        this.mesPlaylists = new ArrayList<>();
        this.follower = new ArrayList<>();
        this.suivi = new ArrayList<>();
        this.vu = new Playlist("Média Vu", this);
        this.like = new Playlist("Coup de Coeur", this);
    }

    public User(List<Avis> sesAvis, List<Playlist> mesPlaylists, String pseudo, String mail, List<User> follower, List<User> suivi, Playlist vu, Playlist like) {
        this.sesAvis = sesAvis;
        this.mesPlaylists = mesPlaylists;
        this.pseudo = pseudo;
        this.mail = mail;
        this.follower = follower;
        this.suivi = suivi;
        this.vu = vu;
        this.like = like;
    }

    public void toggleCoupDeCoeur(Media media) {
        if (this.like.getLesMedias().contains(media)) {
            this.like.retirerMedia(media);
            System.out.println("==> " + media.getTitre() + " retiré des coups de coeur.");
        } else {
            this.like.ajouterMedia(media);
            System.out.println("==> " + media.getTitre() + " ajouté aux coups de coeur.");
        }
    }

    public void toggleMediaVu(Media media) {
        if (this.vu.getLesMedias().contains(media)) {
            this.vu.retirerMedia(media);
            System.out.println("==> " + media.getTitre() + " retiré des médias vus.");
        } else {
            this.vu.ajouterMedia(media);
            System.out.println("==> " + media.getTitre() + " ajouté aux médias vus.");
        }
    }

    public void follow(User cible) {
        if (cible != null && cible != this && !this.suivi.contains(cible)) {
            this.suivi.add(cible);
            cible.getFollower().add(this);
            System.out.println("==> " + this.pseudo + " a commencé à suivre " + cible.getPseudo());
        }
    }

    public void unfollow(User cible) {
        if (cible != null && this.suivi.contains(cible)) {
            this.suivi.remove(cible);
            cible.getFollower().remove(this);
            System.out.println("==> " + this.pseudo + " ne suit plus " + cible.getPseudo());
        }
    }

    public void creerNouvellePlaylist(String nomPlaylist, boolean estPrive) {
        Playlist nouvelle = new Playlist(new ArrayList<>(), nomPlaylist, new Date(), estPrive, this);
        this.mesPlaylists.add(nouvelle);
        System.out.println("Playlist " + nomPlaylist + " créée avec succès par " + this.pseudo);
    }

    public void marquerCommeVu(Media media) {
        this.vu.ajouterMedia(media);
    }

    public void ajouterAuxFavoris(Media media) {
        this.like.ajouterMedia(media);
    }

    public void afficherProfil() {
        System.out.println("\n--- PROFIL DE " + pseudo.toUpperCase() + " ---");
        System.out.println("Email: " + mail);
        System.out.println("Abonnements: " + suivi.size() + " | Abonnés: " + follower.size());
        System.out.println("Médias vus: " + vu.getLesMedias().size());
        System.out.println("Playlists créées: " + mesPlaylists.size());
        System.out.println("----------------------------\n");
    }

    public List<Avis> getSesAvis() {
        return this.sesAvis;
    }

    public void setSesAvis(List<Avis> sesAvis) {
        this.sesAvis = sesAvis;
    }

    public List<Playlist> getMesPlaylists() {
        return this.mesPlaylists;
    }

    public void setMesPlaylists(List<Playlist> mesPlaylists) {
        this.mesPlaylists = mesPlaylists;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<User> getFollower() {
        return this.follower;
    }

    public void setFollower(List<User> follower) {
        this.follower = follower;
    }

    public List<User> getSuivi() {
        return this.suivi;
    }

    public void setSuivi(List<User> suivi) {
        this.suivi = suivi;
    }

    public Playlist getVu() {return vu;}

    public void setVu(Playlist vu) {this.vu = vu;}

    public Playlist getLike() {return like;}

    public void setLike(Playlist like) {this.like = like;}
}