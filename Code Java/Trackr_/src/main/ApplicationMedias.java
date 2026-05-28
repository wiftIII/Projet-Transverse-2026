package main;

import modeles.media.Categorie;
import modeles.media.Film;
import modeles.media.Media;
import modeles.user.Avis;
import modeles.user.Playlist;
import modeles.user.User;
import vues.FactoryView;
import vues.LogView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationMedias {

    User antoine;
    User selma;
    User alana;
    User jordan;

    User USER_LOGGED;

    List<Media> mediaEnVrac;

    public static ApplicationMedias factoryMedia;
    public static FactoryView factoryview;

    public ApplicationMedias() {

        factoryMedia = this;

        Playlist playlist_1 = new Playlist(new ArrayList<>(), "Mes Films trop trop <3", new Date(), false, null);
        Playlist playlist_2 = new Playlist(new ArrayList<>(), "Playlist n2", new Date(), false, null);
        Playlist playlist_3 = new Playlist(new ArrayList<>(), "Playlist n3", new Date(), false, null);
        Playlist playlist_4 = new Playlist(new ArrayList<>(), "Playlist n4", new Date(), false, null);
        Playlist playlist_5 = new Playlist(new ArrayList<>(), "Playlist n5", new Date(), false, null);
        Playlist playlist_6 = new Playlist(new ArrayList<>(), "Playlist n6", new Date(), false, null);
        Playlist playlist_7 = new Playlist(new ArrayList<>(), "Playlist n7", new Date(), false, null);
        Playlist playlist_8 = new Playlist(new ArrayList<>(), "Playlist n8", new Date(), false, null);

        Film filmcute1 = new Film(new ArrayList<>(), Categorie.ROMANCE, "About Time", new Date(113, 8, 4), "Richard Curtis");
        Film filmcute2 = new Film(new ArrayList<>(), Categorie.ROMANCE, "Past Lives", new Date(123, 5, 2), "Celine Song");
        Film filmcute3 = new Film(new ArrayList<>(), Categorie.ROMANCE, "Pride & Prejudice", new Date(105, 8, 16), "Joe Wright");
        Film filmcute4 = new Film(new ArrayList<>(), Categorie.ROMANCE, "La La Land", new Date(116, 11, 9), "Damien Chazelle");
        Film filmcute5 = new Film(new ArrayList<>(), Categorie.ROMANCE, "Before Sunrise", new Date(95, 0, 27), "Richard Linklater");

        mediaEnVrac = new ArrayList<>();
        mediaEnVrac.add(filmcute1);
        mediaEnVrac.add(filmcute2);
        mediaEnVrac.add(filmcute3);
        mediaEnVrac.add(filmcute4);
        mediaEnVrac.add(filmcute5);

        playlist_1.ajouterMedia(mediaEnVrac.get(0));
        playlist_1.ajouterMedia(mediaEnVrac.get(1));
        playlist_1.ajouterMedia(mediaEnVrac.get(4));

        List mesPlaylist = new ArrayList<Playlist>();
        mesPlaylist.add(playlist_1);
        mesPlaylist.add(playlist_2);
        mesPlaylist.add(playlist_3);
        mesPlaylist.add(playlist_4);
        mesPlaylist.add(playlist_5);
        mesPlaylist.add(playlist_6);
        mesPlaylist.add(playlist_7);
        mesPlaylist.add(playlist_8);

        selma = new User(new ArrayList<>(), mesPlaylist, "Selma Syone", "Selma.Syone@gmail.com", new ArrayList<>(), new ArrayList<>(), playlist_1, playlist_2);
        antoine = new User(null, null, "Antoine Stadler", "AntoineStadler@gmail.com", new ArrayList<>(), new ArrayList<>(), playlist_3, playlist_4);
        alana = new User(null, null, "Alana Babibel", "alanabibeldu92@gmail.com", new ArrayList<>(), new ArrayList<>(), playlist_5, playlist_6);
        jordan = new User(null, null, "Jordan Bartoila", "jordinooooooo@yahou.com", new ArrayList<>(), new ArrayList<>(), playlist_7, playlist_8);

        USER_LOGGED = new User("USER LOGGED", "USER_LOGGED@gmail.com");

        playlist_1.setCreateur(selma);
        playlist_2.setCreateur(selma);
        playlist_3.setCreateur(antoine);
        playlist_4.setCreateur(antoine);
        playlist_5.setCreateur(alana);
        playlist_6.setCreateur(alana);
        playlist_7.setCreateur(jordan);
        playlist_8.setCreateur(jordan);

        selma.getSuivi().add(antoine);
        selma.getSuivi().add(jordan);
        selma.getSuivi().add(alana);

        selma.getFollower().add(antoine);
        selma.getFollower().add(jordan);
        selma.getFollower().add(alana);

        antoine.getFollower().add(selma);

        Avis avisSelma1 = new Avis(getSelma(),
                filmcute2,
                new Date(126, 7, 4),
                "Ce film m'a trop fait pleurer... Mon chien et moi avons passé des soirees memorable... ",
                5);

        Avis avisSelma2 = new Avis(getSelma(),
                filmcute4,
                new Date(126, 8, 1),
                "Ils sont trop trop mignonnnnn !!!! <3 <3 <3 ",
                4);

        Avis avisSelma3 = new Avis(getSelma(),
                filmcute1,
                new Date(126, 6, 9),
                "Pas mes gouts pour le coup... ",
                2);

        selma.getSesAvis().add(avisSelma1);
        selma.getSesAvis().add(avisSelma2);
        selma.getSesAvis().add(avisSelma3);

        JFrame frame = new JFrame("Trackr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        LogView logview = new LogView();
        frame.setContentPane(logview);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        FactoryView accueilView = new FactoryView(this, true);
        factoryview = accueilView;

    }


    public List<Media> getMediaEnVrac() {
        return mediaEnVrac;
    }


    public static void main(String[] args) {
        new ApplicationMedias();
    }


    public static FactoryView getFactoryview() {
        return factoryview;
    }

    public User getAntoine() {
        return antoine;
    }

    public void setAntoine(User antoine) {
        this.antoine = antoine;
    }

    public User getSelma() {
        return selma;
    }

    public void setSelma(User selma) {
        this.selma = selma;
    }

    public User getAlana() {
        return alana;
    }

    public void setAlana(User alana) {
        this.alana = alana;
    }

    public User getJordan() {
        return jordan;
    }

    public void setJordan(User jordan) {
        this.jordan = jordan;
    }

    public static ApplicationMedias getFactoryMedia() {
        return factoryMedia;
    }

    public User getUserLogged() {return USER_LOGGED;}

    public void setUserLogged(User user) {this.USER_LOGGED.setPseudo(String.valueOf(user));}

    public void setUserLoggedWithSelma() {this.USER_LOGGED = selma;}
}