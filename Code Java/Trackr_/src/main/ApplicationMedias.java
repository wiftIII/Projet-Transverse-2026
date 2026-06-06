package main;

import modeles.media.*;
import modeles.user.Avis;
import modeles.user.Playlist;
import modeles.user.User;
import utils.Utils;
import vues.FactoryView;
import vues.LogView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utils.Utils.genererEpisodesPourSerie;

public class ApplicationMedias {

    User selma;
    User USER_LOGGED;
    List<Media> mediaEnVrac;
    List<User> tousLesUtilisateurs;

    public static ApplicationMedias factoryMedia;
    public static FactoryView factoryview;

    public ApplicationMedias() {

        factoryMedia = this;

        //=================================================================//
        //                    CREATION DES DONNEES                         //
        //=================================================================//

        //Création des users
        selma = new User("Selma Syone", "Selma.Syone@gmail.com");
        User steve = new User("Steve", "steve.craft@gmail.com");
        User leo = new User("Léo Chronos", "leo.chronos@yahoo.fr");
        User alice = new User("Alice Copper", "alice.engrenage@gmail.com");
        User paul = new User("Paul Castor", "paul.louveteau@hotmail.fr");
        User victor = new User("Victor", "victor.docker@gmail.com");
        User clara = new User("Clara", "clara.sql@yahoo.com");
        User hugo = new User("Hugo Swing", "hugo.ui@gmail.com");
        User juliette = new User("Juliette", "juliette.trackr@gmail.com");
        User maxime = new User("Maxime", "max.blockbench@gmail.com");

        USER_LOGGED = new User("USER LOGGED", "USER_LOGGED@gmail.com");

        tousLesUtilisateurs = new ArrayList<>();
        tousLesUtilisateurs.add(selma);
        tousLesUtilisateurs.add(steve);
        tousLesUtilisateurs.add(leo);
        tousLesUtilisateurs.add(alice);
        tousLesUtilisateurs.add(paul);
        tousLesUtilisateurs.add(victor);
        tousLesUtilisateurs.add(clara);
        tousLesUtilisateurs.add(hugo);
        tousLesUtilisateurs.add(juliette);
        tousLesUtilisateurs.add(maxime);

        tousLesUtilisateurs.add(USER_LOGGED);

        //Création des films
        // === GROUPE 1 : Science-Fiction, Tech & Cyberpunk ===
        Film f1 = new Film(Categorie.SF, "Matrix", new Date(99, 4, 24), "Lilly & Lana Wachowski", null, null, "2h16");
        Film f2 = new Film(Categorie.SF, "Blade Runner", new Date(82, 5, 25), "Ridley Scott", null, null, "1h57");
        Film f3 = new Film(Categorie.SF, "Blade Runner 2049", new Date(117, 9, 4), "Denis Villeneuve", f2, null, "2h44"); // f2 est le film précédent
        Film f4 = new Film(Categorie.SF, "Le Cinquième Élément", new Date(97, 4, 7), "Luc Besson", null, null, "2h06");
        Film f5 = new Film(Categorie.SF, "Interstellar", new Date(114, 10, 5), "Christopher Nolan", null, null, "2h49");
        Film f6 = new Film(Categorie.SF, "Inception", new Date(110, 6, 21), "Christopher Nolan", null, null, "2h28");
        Film f7 = new Film(Categorie.SF, "Dune", new Date(121, 8, 15), "Denis Villeneuve", null, null, "2h35");
        Film f8 = new Film(Categorie.SF, "Dune - Deuxième Partie", new Date(124, 1, 28), "Denis Villeneuve", f7, null, "2h46");
        Film f9 = new Film(Categorie.THRILLER, "Hackers", new Date(95, 8, 15), "Iain Softley", null, null, "1h45");
        Film f10 = new Film(Categorie.BIOPIC, "The Social Network", new Date(110, 9, 13), "David Fincher", null, null, "2h00");

        // === GROUPE 2 : Mécanique, Steampunk & Animation 3D ===
        Film f11 = new Film(Categorie.ANIMATION, "Le Château Ambulant", new Date(104, 10, 20), "Hayao Miyazaki", null, null, "1h59");
        Film f12 = new Film(Categorie.SF, "Mortal Engines", new Date(118, 11, 12), "Christian Rivers", null, null, "2h08");
        Film f13 = new Film(Categorie.FAMILIAL, "Hugo Cabret", new Date(111, 11, 14), "Martin Scorsese", null, null, "2h06");
        Film f14 = new Film(Categorie.ANIMATION, "Steamboy", new Date(104, 6, 17), "Katsuhiro Otomo", null, null, "2h06");
        Film f15 = new Film(Categorie.FANTASTIQUE, "La Cité des Enfants Perdus", new Date(95, 4, 17), "Marc Caro & Jean-Pierre Jeunet", null, null, "1h52");
        Film f16 = new Film(Categorie.ANIMATION, "Le Géant de Fer", new Date(99, 11, 8), "Brad Bird", null, null, "1h26");
        Film f17 = new Film(Categorie.ANIMATION, "Toy Story", new Date(95, 10, 22), "John Lasseter", null, null, "1h21");
        Film f18 = new Film(Categorie.ANIMATION, "Toy Story 2", new Date(99, 10, 24), "John Lasseter", f17, null, "1h32");
        Film f19 = new Film(Categorie.ANIMATION, "Spider-Man - New Generation", new Date(118, 11, 12), "Bob Persichetti", null, null, "1h57");
        Film f20 = new Film(Categorie.ANIMATION, "Wall-E", new Date(108, 6, 30), "Andrew Stanton", null, null, "1h38");

        // === GROUPE 3 : Aventure, Groupes & Familial ===
        Film f21 = new Film(Categorie.AUTEUR, "Moonrise Kingdom", new Date(112, 4, 16), "Wes Anderson", null, null, "1h34");
        Film f22 = new Film(Categorie.AVENTURE, "Les Goonies", new Date(85, 11, 4), "Richard Donner", null, null, "1h54");
        Film f23 = new Film(Categorie.DRAME, "Stand by Me", new Date(86, 7, 8), "Rob Reiner", null, null, "1h29");
        Film f24 = new Film(Categorie.AVENTURE, "Jumanji", new Date(95, 11, 15), "Joe Johnston", null, null, "1h44");
        Film f25 = new Film(Categorie.FAMILIAL, "E.T., l'extra-terrestre", new Date(82, 5, 11), "Steven Spielberg", null, null, "1h55");
        Film f26 = new Film(Categorie.AVENTURE, "Jurassic Park", new Date(93, 9, 20), "Steven Spielberg", null, null, "2h07");
        Film f27 = new Film(Categorie.AVENTURE, "Le Monde Perdu - Jurassic Park", new Date(97, 9, 22), "Steven Spielberg", f26, null, "2h09");
        Film f28 = new Film(Categorie.SF, "Retour vers le futur", new Date(85, 9, 23), "Robert Zemeckis", null, null, "1h56");

        Film f29 = new Film(Categorie.SF, "Retour vers le futur II", new Date(89, 10, 22), "Robert Zemeckis", f28, null, "1h48");
        Film f30 = new Film(Categorie.SF, "Retour vers le futur III", new Date(90, 4, 25), "Robert Zemeckis", f29, null, "1h58");

        // === GROUPE 4 : Action & Thriller ===
        Film f31 = new Film(Categorie.ACTION, "Die Hard", new Date(88, 8, 21), "John McTiernan", null, null, "2h12");
        Film f32 = new Film(Categorie.ACTION, "Mad Max- Fury Road", new Date(115, 4, 14), "George Miller", null, null, "2h00");
        Film f33 = new Film(Categorie.ACTION, "John Wick", new Date(114, 9, 24), "Chad Stahelski", null, null, "1h41");
        Film f34 = new Film(Categorie.ACTION, "The Dark Knight", new Date(108, 7, 13), "Christopher Nolan", null, null, "2h32");
        Film f35 = new Film(Categorie.THRILLER, "Fight Club", new Date(99, 10, 10), "David Fincher", null, null, "2h19");
        Film f36 = new Film(Categorie.THRILLER, "Seven", new Date(95, 8, 22), "David Fincher", null, null, "2h07");
        Film f37 = new Film(Categorie.THRILLER, "Parasite", new Date(119, 5, 5), "Bong Joon Ho", null, null, "2h12");
        Film f38 = new Film(Categorie.THRILLER, "Le Silence des Agneaux", new Date(91, 3, 10), "Jonathan Demme", null, null, "1h58");
        Film f39 = new Film(Categorie.MYSTERE, "Shutter Island", new Date(110, 1, 24), "Martin Scorsese", null, null, "2h18");
        Film f40 = new Film(Categorie.THRILLER, "Prisoners", new Date(113, 9, 9), "Denis Villeneuve", null, null, "2h33");

        // === GROUPE 5 : Fantastique ===
        Film f41 = new Film(Categorie.FANTASTIQUE, "Le Seigneur des Anneaux - La Communauté de l'Anneau", new Date(101, 11, 19), "Peter Jackson", null, null, "2h58");
        Film f42 = new Film(Categorie.FANTASTIQUE, "Le Seigneur des Anneaux - Les Deux Tours", new Date(102, 11, 18), "Peter Jackson", f41, null, "2h59");
        Film f43 = new Film(Categorie.FANTASTIQUE, "Le Seigneur des Anneaux - Le Retour du Roi", new Date(103, 11, 17), "Peter Jackson", f42, null, "3h21");
        Film f44 = new Film(Categorie.FANTASTIQUE, "Harry Potter à l'école des sorciers", new Date(101, 10, 16), "Chris Columbus", null, null, "2h32");
        Film f45 = new Film(Categorie.FANTASTIQUE, "Harry Potter et la Chambre des secrets", new Date(102, 10, 15), "Chris Columbus", f44, null, "2h41");
        Film f46 = new Film(Categorie.FANTASTIQUE, "Harry Potter et le Prisonnier d'Azkaban", new Date(104, 5, 4), "Alfonso Cuarón", f45, null, "2h22");
        Film f47 = new Film(Categorie.FANTASTIQUE, "Le Labyrinthe de Pan", new Date(106, 10, 1), "Guillermo del Toro", null, null, "1h58");
        Film f48 = new Film(Categorie.FANTASTIQUE, "Edward aux mains d'argent", new Date(90, 11, 7), "Tim Burton", null, null, "1h45");
        Film f49 = new Film(Categorie.SF, "Avatar", new Date(109, 11, 16), "James Cameron", null, null, "2h42");
        Film f50 = new Film(Categorie.SF, "Avatar - La Voie de l'eau", new Date(122, 11, 14), "James Cameron", f49, null, "3h12");

        // === GROUPE 6 : Comédie & Musical ===
        Film f51 = new Film(Categorie.COMEDIE, "La Cité de la Peur", new Date(94, 2, 9), "Alain Berbérian", null, null, "1h33");
        Film f52 = new Film(Categorie.COMEDIE, "Astérix & Obélix - Mission Cléopâtre", new Date(102, 0, 30), "Alain Chabat", null, null, "1h47");
        Film f53 = new Film(Categorie.COMEDIE, "Kaamelott - Premier Volet", new Date(121, 6, 21), "Alexandre Astier", null, null, "2h00");
        Film f54 = new Film(Categorie.COMEDIE, "The Grand Budapest Hotel", new Date(114, 1, 26), "Wes Anderson", null, null, "1h39");
        Film f55 = new Film(Categorie.COMEDIE, "Intouchables", new Date(111, 10, 2), "Olivier Nakache & Éric Toledano", null, null, "1h52");
        Film f56 = new Film(Categorie.MUSICAL, "La La Land", new Date(116, 11, 9), "Damien Chazelle", null, null, "2h08");
        Film f57 = new Film(Categorie.MUSICAL, "Moulin Rouge!", new Date(101, 9, 3), "Baz Luhrmann", null, null, "2h07");
        Film f58 = new Film(Categorie.MUSICAL, "The Blues Brothers", new Date(80, 5, 20), "John Landis", null, null, "2h13");
        Film f59 = new Film(Categorie.MUSICAL, "West Side Story", new Date(61, 9, 18), "Robert Wise", null, null, "2h33");
        Film f60 = new Film(Categorie.DRAME, "Whiplash", new Date(114, 9, 10), "Damien Chazelle", null, null, "1h46");

        // === GROUPE 7 : Drame, Historique & Guerre ===
        Film f61 = new Film(Categorie.HISTORIQUE, "La Liste de Schindler", new Date(93, 11, 15), "Steven Spielberg", null, null, "3h15");
        Film f62 = new Film(Categorie.GUERRE, "Il faut sauver le soldat Ryan", new Date(98, 6, 24), "Steven Spielberg", null, null, "2h49");
        Film f63 = new Film(Categorie.GUERRE, "1917", new Date(119, 11, 25), "Sam Mendes", null, null, "1h59");
        Film f64 = new Film(Categorie.GUERRE, "Dunkerque", new Date(117, 6, 19), "Christopher Nolan", null, null, "1h46");
        Film f65 = new Film(Categorie.DRAME, "Forrest Gump", new Date(94, 6, 6), "Robert Zemeckis", null, null, "2h22");
        Film f66 = new Film(Categorie.DRAME, "La Ligne Verte", new Date(99, 11, 10), "Frank Darabont", null, null, "3h09");
        Film f67 = new Film(Categorie.DRAME, "Les Évadés", new Date(94, 8, 23), "Frank Darabont", null, null, "2h22");
        Film f68 = new Film(Categorie.ROMANCE, "Titanic", new Date(97, 11, 19), "James Cameron", null, null, "3h14");
        Film f69 = new Film(Categorie.HISTORIQUE, "Gladiator", new Date(100, 4, 5), "Ridley Scott", null, null, "2h35");
        Film f70 = new Film(Categorie.BIOPIC, "Oppenheimer", new Date(123, 6, 19), "Christopher Nolan", null, null, "3h00");

        // === GROUPE 8 : Western, Horreur, Policier & Mystère ===
        Film f71 = new Film(Categorie.WESTERN, "Le Bon, la Brute et le Truand", new Date(66, 11, 23), "Sergio Leone", null, null, "2h41");
        Film f72 = new Film(Categorie.WESTERN, "Django Unchained", new Date(112, 11, 25), "Quentin Tarantino", null, null, "2h45");
        Film f73 = new Film(Categorie.WESTERN, "Il était une fois dans l'Ouest", new Date(68, 11, 21), "Sergio Leone", null, null, "2h45");
        Film f74 = new Film(Categorie.HORREUR, "Shining", new Date(80, 4, 23), "Stanley Kubrick", null, null, "2h26");
        Film f75 = new Film(Categorie.HORREUR, "Alien, le huitième passager", new Date(79, 4, 25), "Ridley Scott", null, null, "1h57");
        Film f76 = new Film(Categorie.HORREUR, "The Thing", new Date(82, 5, 25), "John Carpenter", null, null, "1h49");
        Film f77 = new Film(Categorie.POLICIER, "Pulp Fiction", new Date(94, 9, 14), "Quentin Tarantino", null, null, "2h34");
        Film f78 = new Film(Categorie.POLICIER, "Le Parrain", new Date(72, 2, 24), "Francis Ford Coppola", null, null, "2h55");
        Film f79 = new Film(Categorie.POLICIER, "Les Affranchis", new Date(90, 8, 19), "Martin Scorsese", null, null, "2h26");
        Film f80 = new Film(Categorie.MYSTERE, "À couteaux tirés", new Date(119, 10, 27), "Rian Johnson", null, null, "2h10");

        mediaEnVrac = new ArrayList<>();
        mediaEnVrac.addAll(java.util.Arrays.asList(
                f1, f2, f3, f4, f5, f6, f7, f8, f9, f10,
                f11, f12, f13, f14, f15, f16, f17, f18, f19, f20,
                f21, f22, f23, f24, f25, f26, f27, f28, f29, f30,
                f31, f32, f33, f34, f35, f36, f37, f38, f39, f40,
                f41, f42, f43, f44, f45, f46, f47, f48, f49, f50,
                f51, f52, f53, f54, f55, f56, f57, f58, f59, f60,
                f61, f62, f63, f64, f65, f66, f67, f68, f69, f70,
                f71, f72, f73, f74, f75, f76, f77, f78, f79, f80
        ));

        //Création des séries
        Serie s1 = new Serie(Categorie.HISTORIQUE, "Chernobyl", new Date(119, 4, 6), "Craig Mazin", 5);
        Serie s2 = new Serie(Categorie.ANIMATION, "Arcane", new Date(121, 10, 6), "Pascal Charrue & Arnaud Delord", 9);
        Serie s3 = new Serie(Categorie.THRILLER, "Mr. Robot", new Date(115, 5, 24), "Sam Esmail", 45);
        Serie s4 = new Serie(Categorie.DRAME, "Breaking Bad", new Date(108, 0, 20), "Vince Gilligan", 62);
        Serie s5 = new Serie(Categorie.SF, "Black Mirror", new Date(111, 11, 4), "Charlie Brooker", 27);
        Serie s6 = new Serie(Categorie.MYSTERE, "Dark", new Date(117, 11, 1), "Baran bo Odar", 26);
        Serie s7 = new Serie(Categorie.FANTASTIQUE, "Stranger Things", new Date(116, 6, 15), "The Duffer Brothers", 34);
        Serie s8 = new Serie(Categorie.GUERRE, "Band of Brothers", new Date(101, 8, 9), "Steven Spielberg & Tom Hanks", 10);
        Serie s9 = new Serie(Categorie.DRAME, "Le Jeu de la Dame", new Date(120, 9, 23), "Scott Frank", 7);
        Serie s10 = new Serie(Categorie.POLICIER, "Peaky Blinders", new Date(113, 8, 12), "Steven Knight", 36);
        Serie s11 = new Serie(Categorie.SF, "The Mandalorian", new Date(119, 10, 12), "Jon Favreau", 24);
        Serie s12 = new Serie(Categorie.HORREUR, "The Last of Us", new Date(123, 0, 15), "Craig Mazin & Neil Druckmann", 9);
        Serie s13 = new Serie(Categorie.COMEDIE, "The Office", new Date(105, 2, 24), "Greg Daniels", 201);
        Serie s14 = new Serie(Categorie.MYSTERE, "Sherlock", new Date(110, 6, 25), "Mark Gatiss & Steven Moffat", 13);
        Serie s15 = new Serie(Categorie.ANIMATION, "Cyberpunk- Edgerunners", new Date(122, 8, 13), "Hiroyuki Imaishi", 10);
        Serie s16 = new Serie(Categorie.THRILLER, "Mindhunter", new Date(117, 9, 13), "David Fincher", 19);
        Serie s17 = new Serie(Categorie.SF, "Severance", new Date(122, 1, 18), "Dan Erickson", 9);
        Serie s18 = new Serie(Categorie.POLICIER, "True Detective", new Date(114, 0, 12), "Nic Pizzolatto", 24);
        Serie s19 = new Serie(Categorie.ACTION, "The Boys", new Date(119, 6, 26), "Eric Kripke", 32);
        Serie s20 = new Serie(Categorie.DRAME, "Succession", new Date(118, 5, 3), "Jesse Armstrong", 39);

        mediaEnVrac.addAll(java.util.Arrays.asList(
                s1, s2, s3, s4, s5, s6, s7, s8, s9, s10,
                s11, s12, s13, s14, s15, s16, s17, s18, s19, s20
        ));

        // Génération automatique des épisodes pour toutes les séries créées
        genererEpisodesPourSerie(s1, "1h00", 3); // Chernobyl
        genererEpisodesPourSerie(s2, "0h40", 3); // Arcane
        genererEpisodesPourSerie(s3, "0h45", 3); // Mr. Robot
        genererEpisodesPourSerie(s4, "0h47", 3); // Breaking Bad
        genererEpisodesPourSerie(s5, "1h00", 3); // Black Mirror
        genererEpisodesPourSerie(s6, "0h50", 3); // Dark
        genererEpisodesPourSerie(s7, "0h50", 3); // Stranger Things
        genererEpisodesPourSerie(s8, "1h00", 3); // Band of Brothers
        genererEpisodesPourSerie(s9, "0h55", 3); // Le Jeu de la Dame
        genererEpisodesPourSerie(s10, "0h55", 3); // Peaky Blinders
        genererEpisodesPourSerie(s11, "0h40", 3); // The Mandalorian
        genererEpisodesPourSerie(s12, "0h50", 3); // The Last of Us
        genererEpisodesPourSerie(s13, "0h22", 3); // The Office
        genererEpisodesPourSerie(s14, "1h30", 3); // Sherlock
        genererEpisodesPourSerie(s15, "0h24", 3); // Cyberpunk
        genererEpisodesPourSerie(s16, "0h50", 3); // Mindhunter
        genererEpisodesPourSerie(s17, "0h45", 3); // Severance
        genererEpisodesPourSerie(s18, "0h55", 3); // True Detective
        genererEpisodesPourSerie(s19, "1h00", 3); // The Boys
        genererEpisodesPourSerie(s20, "1h00", 3); // Succession

        for (Media e : mediaEnVrac) {
            selma.ajouterAuxFavoris(e);
        }

        //Création des follow/followed
        selma.follow(leo);
        selma.follow(alice);
        selma.follow(juliette);
        selma.follow(hugo);
        leo.follow(selma);
        alice.follow(selma);
        steve.follow(maxime);
        maxime.follow(steve);
        hugo.follow(clara);
        clara.follow(hugo);
        victor.follow(clara);
        victor.follow(steve);
        clara.follow(victor);
        hugo.follow(selma);
        maxime.follow(alice);
        leo.follow(alice);
        alice.follow(leo);
        paul.follow(selma);
        paul.follow(steve);
        juliette.follow(hugo);
        juliette.follow(maxime);

        //Ajout des Médias dans Vu et Coup de coeur
        selma.marquerCommeVu(f56); // La La Land
        selma.marquerCommeVu(f68); // Titanic
        selma.marquerCommeVu(s9);  // Le Jeu de la Dame
        selma.marquerCommeVu(s13); // The Office
        selma.ajouterAuxFavoris(f56);
        selma.ajouterAuxFavoris(s9);

        steve.marquerCommeVu(f24); // Jumanji
        steve.marquerCommeVu(f41); // Le Seigneur des Anneaux 1
        steve.marquerCommeVu(f42); // Le Seigneur des Anneaux 2
        steve.marquerCommeVu(s12); // The Last of Us
        steve.ajouterAuxFavoris(f41);
        steve.ajouterAuxFavoris(s12);

        leo.marquerCommeVu(f28); // Retour vers le futur
        leo.marquerCommeVu(f29); // Retour vers le futur II
        leo.marquerCommeVu(f5);  // Interstellar
        leo.marquerCommeVu(s6);  // Dark
        leo.ajouterAuxFavoris(f28);
        leo.ajouterAuxFavoris(s6);

        alice.marquerCommeVu(f11); // Le Château Ambulant
        alice.marquerCommeVu(f12); // Mortal Engines
        alice.marquerCommeVu(f14); // Steamboy
        alice.marquerCommeVu(s2);  // Arcane
        alice.ajouterAuxFavoris(f11);
        alice.ajouterAuxFavoris(f14);

        paul.marquerCommeVu(f21); // Moonrise Kingdom
        paul.marquerCommeVu(f23); // Stand by Me
        paul.marquerCommeVu(f22); // Les Goonies
        paul.marquerCommeVu(s7);  // Stranger Things
        paul.ajouterAuxFavoris(f21);
        paul.ajouterAuxFavoris(f23);

        victor.marquerCommeVu(f1); // Matrix
        victor.marquerCommeVu(f9); // Hackers
        victor.marquerCommeVu(s3); // Mr. Robot
        victor.ajouterAuxFavoris(f1);
        victor.ajouterAuxFavoris(s3);

        clara.marquerCommeVu(f6);  // Inception
        clara.marquerCommeVu(f39); // Shutter Island
        clara.marquerCommeVu(s14); // Sherlock
        clara.ajouterAuxFavoris(f6);
        clara.ajouterAuxFavoris(s14);

        hugo.marquerCommeVu(f54); // The Grand Budapest Hotel
        hugo.marquerCommeVu(s5);  // Black Mirror
        hugo.marquerCommeVu(s17); // Severance
        hugo.ajouterAuxFavoris(f54);
        hugo.ajouterAuxFavoris(s17);

        juliette.marquerCommeVu(f80); // À couteaux tirés
        juliette.marquerCommeVu(s20); // Succession
        juliette.marquerCommeVu(s13); // The Office
        juliette.ajouterAuxFavoris(f80);
        juliette.ajouterAuxFavoris(s20);

        maxime.marquerCommeVu(f17); // Toy Story
        maxime.marquerCommeVu(f19); // Spider-Man: New Generation
        maxime.marquerCommeVu(f20); // Wall-E
        maxime.marquerCommeVu(s15); // Cyberpunk: Edgerunners
        maxime.ajouterAuxFavoris(f19);
        maxime.ajouterAuxFavoris(s15);

        //Création des playlists et de leur contenu
        selma.creerNouvellePlaylist("Soirée Plaid & Larmes", false);
        Playlist pSelma1 = selma.getMesPlaylists().get(selma.getMesPlaylists().size() - 1);
        pSelma1.ajouterMedia(f68); // Titanic
        pSelma1.ajouterMedia(f56); // La La Land
        pSelma1.ajouterMedia(f65); // Forrest Gump

        selma.creerNouvellePlaylist("Plaisirs Coupables", true);
        Playlist pSelma2 = selma.getMesPlaylists().get(selma.getMesPlaylists().size() - 1);
        pSelma2.ajouterMedia(f52); // Astérix
        pSelma2.ajouterMedia(s13); // The Office

        steve.creerNouvellePlaylist("Aventures Cubiques & Survie", false);
        Playlist pSteve1 = steve.getMesPlaylists().get(steve.getMesPlaylists().size() - 1);
        pSteve1.ajouterMedia(f24); // Jumanji
        pSteve1.ajouterMedia(s12); // The Last of Us
        pSteve1.ajouterMedia(f64); // Dunkerque

        steve.creerNouvellePlaylist("Exploration", true);
        Playlist pSteve2 = steve.getMesPlaylists().get(steve.getMesPlaylists().size() - 1);
        pSteve2.ajouterMedia(f41); // SDA 1
        pSteve2.ajouterMedia(f42); // SDA 2
        pSteve2.ajouterMedia(f43); // SDA 3

        leo.creerNouvellePlaylist("Projet Chronos", false);
        Playlist pLeo1 = leo.getMesPlaylists().get(leo.getMesPlaylists().size() - 1);
        pLeo1.ajouterMedia(f28); // BTTF 1
        pLeo1.ajouterMedia(f29); // BTTF 2
        pLeo1.ajouterMedia(f30); // BTTF 3
        pLeo1.ajouterMedia(s6);  // Dark

        leo.creerNouvellePlaylist("Futur & Dystopie", false);
        Playlist pLeo2 = leo.getMesPlaylists().get(leo.getMesPlaylists().size() - 1);
        pLeo2.ajouterMedia(f5);  // Interstellar
        pLeo2.ajouterMedia(f12); // Mortal Engines

        alice.creerNouvellePlaylist("La Cité des Engrenages", false);
        Playlist pAlice1 = alice.getMesPlaylists().get(alice.getMesPlaylists().size() - 1);
        pAlice1.ajouterMedia(f11); // Le Château Ambulant
        pAlice1.ajouterMedia(f14); // Steamboy
        pAlice1.ajouterMedia(s2);  // Arcane

        alice.creerNouvellePlaylist("Rouages & Mécanique", false);
        Playlist pAlice2 = alice.getMesPlaylists().get(alice.getMesPlaylists().size() - 1);
        pAlice2.ajouterMedia(f15); // La Cité des Enfants Perdus
        pAlice2.ajouterMedia(f32); // Mad Max

        paul.creerNouvellePlaylist("Pour les Louveteaux", false);
        Playlist pPaul1 = paul.getMesPlaylists().get(paul.getMesPlaylists().size() - 1);
        pPaul1.ajouterMedia(f21); // Moonrise Kingdom
        pPaul1.ajouterMedia(f17); // Toy Story
        pPaul1.ajouterMedia(f20); // Wall-E

        paul.creerNouvellePlaylist("Veillée Scoute", true);
        Playlist pPaul2 = paul.getMesPlaylists().get(paul.getMesPlaylists().size() - 1);
        pPaul2.ajouterMedia(f22); // Les Goonies
        pPaul2.ajouterMedia(f23); // Stand by Me
        pPaul2.ajouterMedia(f25); // E.T.

        victor.creerNouvellePlaylist("Réseaux & Matrices", false);
        Playlist pVictor1 = victor.getMesPlaylists().get(victor.getMesPlaylists().size() - 1);
        pVictor1.ajouterMedia(f1);  // Matrix
        pVictor1.ajouterMedia(f9);  // Hackers
        pVictor1.ajouterMedia(s3);  // Mr. Robot

        victor.creerNouvellePlaylist("Pause Déploiement", true);
        Playlist pVictor2 = victor.getMesPlaylists().get(victor.getMesPlaylists().size() - 1);
        pVictor2.ajouterMedia(s5);  // Black Mirror
        pVictor2.ajouterMedia(f10); // The Social Network

        clara.creerNouvellePlaylist("Requêtes & Mystères", false);
        Playlist pClara1 = clara.getMesPlaylists().get(clara.getMesPlaylists().size() - 1);
        pClara1.ajouterMedia(s14); // Sherlock
        pClara1.ajouterMedia(f80); // À couteaux tirés
        pClara1.ajouterMedia(f39); // Shutter Island

        clara.creerNouvellePlaylist("Indexation Criminelle", false);
        Playlist pClara2 = clara.getMesPlaylists().get(clara.getMesPlaylists().size() - 1);
        pClara2.ajouterMedia(f36); // Seven
        pClara2.ajouterMedia(s16); // Mindhunter
        pClara2.ajouterMedia(f40); // Prisoners

        hugo.creerNouvellePlaylist("Masterclass UI & Design", false);
        Playlist pHugo1 = hugo.getMesPlaylists().get(hugo.getMesPlaylists().size() - 1);
        pHugo1.ajouterMedia(f54); // Grand Budapest Hotel
        pHugo1.ajouterMedia(f49); // Avatar
        pHugo1.ajouterMedia(f50); // Avatar 2

        hugo.creerNouvellePlaylist("Esthétique Sombre", false);
        Playlist pHugo2 = hugo.getMesPlaylists().get(hugo.getMesPlaylists().size() - 1);
        pHugo2.ajouterMedia(f2);  // Blade Runner
        pHugo2.ajouterMedia(f3);  // Blade Runner 2049
        pHugo2.ajouterMedia(s17); // Severance

        juliette.creerNouvellePlaylist("Binge-Watching Détente", false);
        Playlist pJuliette1 = juliette.getMesPlaylists().get(juliette.getMesPlaylists().size() - 1);
        pJuliette1.ajouterMedia(s13); // The Office
        pJuliette1.ajouterMedia(f53); // Kaamelott
        pJuliette1.ajouterMedia(f51); // La Cité de la Peur

        juliette.creerNouvellePlaylist("Drames Familiaux", false);
        Playlist pJuliette2 = juliette.getMesPlaylists().get(juliette.getMesPlaylists().size() - 1);
        pJuliette2.ajouterMedia(s20); // Succession
        pJuliette2.ajouterMedia(f78); // Le Parrain

        maxime.creerNouvellePlaylist("Références 3D & Animation", false);
        Playlist pMaxime1 = maxime.getMesPlaylists().get(maxime.getMesPlaylists().size() - 1);
        pMaxime1.ajouterMedia(f19); // Spider-Man
        pMaxime1.ajouterMedia(s15); // Cyberpunk
        pMaxime1.ajouterMedia(f16); // Le Géant de Fer

        maxime.creerNouvellePlaylist("Modélisation & Effets Visuels", false);
        Playlist pMaxime2 = maxime.getMesPlaylists().get(maxime.getMesPlaylists().size() - 1);
        pMaxime2.ajouterMedia(f26); // Jurassic Park
        pMaxime2.ajouterMedia(f4);  // Le Cinquième Élément

        //Création des avis
        f1.newAvis(victor, "La base de tout. Un chef-d'oeuvre du cyberpunk.", 5);
        f1.newAvis(leo, "Le concept de la matrice est fascinant, très en avance sur son temps.", 5);
        f1.newAvis(hugo, "Visuellement incroyable, les effets spéciaux ont révolutionné le cinéma.", 4);
        f1.newAvis(steve, "Beaucoup d'action, j'adore les scènes d'arts martiaux.", 4);

        f56.newAvis(selma, "La fin m'a brisé le cœur. Les musiques sont magnifiques.", 5);
        f56.newAvis(juliette, "Une belle romance, très colorée. Ça donne le sourire.", 4);
        f56.newAvis(steve, "Un peu trop de chansons à mon goût, ça manque de rythme.", 2);
        f56.newAvis(paul, "Belles chorégraphies, même si ce n'est pas mon genre habituel.", 3);

        s2.newAvis(alice, "L'univers visuel, le style steampunk... tout est absolument parfait !", 5);
        s2.newAvis(maxime, "En tant que modeleur 3D, c'est une immense claque. L'animation hybride est folle.", 5);
        s2.newAvis(hugo, "Une direction artistique phénoménale. Le chara-design est masterclass.", 5);
        s2.newAvis(victor, "L'histoire est bonne, mais je suis surtout impressionné par la technique.", 4);

        s13.newAvis(selma, "Je la regarde en boucle, Michael Scott est le meilleur.", 5);
        s13.newAvis(juliette, "C'est ma série confort par excellence. Impossible de s'en lasser.", 5);
        s13.newAvis(clara, "Un humour très particulier, ça m'a pris quelques épisodes pour accrocher.", 4);
        s13.newAvis(paul, "Sympa pour se détendre, mais il y a parfois des longueurs.", 3);

        f5.newAvis(leo, "La représentation de la relativité du temps est magistrale. Un classique.", 5);
        f5.newAvis(steve, "Les scènes dans l'espace sont stressantes, très bon film de survie spatiale.", 4);
        f5.newAvis(victor, "Le scénario est complexe comme je les aime. Hans Zimmer au sommet.", 5);
        f5.newAvis(selma, "L'histoire d'amour entre un père et sa fille au-delà de l'espace... sublime.", 4);

        f41.newAvis(steve, "La plus grande aventure jamais portée à l'écran.", 5);
        f41.newAvis(paul, "Les paysages de la Nouvelle-Zélande sont grandioses, ça donne envie de voyager.", 5);
        f41.newAvis(maxime, "Les effets spéciaux numériques et pratiques se marient à merveille.", 4);
        f41.newAvis(alice, "Un peu long par moments, mais la création de l'univers est dingue.", 4);

        s15.newAvis(maxime, "L'animation du studio Trigger est nerveuse, frénétique, parfaite.", 5);
        s15.newAvis(victor, "Une excellente adaptation de l'univers du jeu vidéo.", 4);
        s15.newAvis(alice, "Les implants cybernétiques et le design global sont très inspirants.", 4);

        s3.newAvis(victor, "Enfin une série qui montre du vrai code et des vraies requêtes. Réaliste à 100%.", 5);
        s3.newAvis(clara, "Le traitement psychologique d'Elliot est captivant. Un scénario en béton.", 5);
        s3.newAvis(leo, "Sombre et complexe, avec de superbes retournements de situation.", 4);
        s3.newAvis(hugo, "Le cadrage asymétrique est un choix visuel audacieux et brillant.", 4);

        f24.newAvis(steve, "Un classique de l'aventure ! Les effets ont un peu vieilli mais ça reste top.", 4);
        f24.newAvis(paul, "Idéal à regarder avec les enfants, ça fait toujours son petit effet.", 5);
        f24.newAvis(juliette, "Robin Williams est incroyable, même si le film me faisait peur quand j'étais petite.", 3);

        s17.newAvis(hugo, "La direction artistique des bureaux est d'une froideur hypnotique. Génial.", 5);
        s17.newAvis(clara, "Un mystère qui s'épaissit à chaque épisode. Vivement la suite !", 5);
        s17.newAvis(victor, "Le concept de diviser sa mémoire en deux est effrayant et fascinant.", 4);
        s17.newAvis(selma, "L'ambiance est très oppressante, mais on veut savoir la fin.", 4);

        f39.newAvis(clara, "Un thriller psychologique brillant, tout est dans les détails de la mise en scène.", 5);
        f39.newAvis(juliette, "La fin m'a laissée sans voix. Leonardo DiCaprio est fantastique.", 4);
        f39.newAvis(leo, "On perd complètement nos repères avec le protagoniste. Très fort.", 4);

        f52.newAvis(selma, "Je connais toutes les répliques par cœur, c'est le meilleur film français.", 5);
        f52.newAvis(juliette, "Un casting légendaire et des blagues qui font toujours mouche.", 5);
        f52.newAvis(paul, "Parfait pour une soirée en famille. Edouard Baer est hilarant.", 4);
        f52.newAvis(victor, "C'est une bonne situation ça scribe ?", 5);

        f32.newAvis(alice, "Les véhicules rafistolés, le métal hurlant... Un chef d'oeuvre de design post-apo.", 5);
        f32.newAvis(steve, "Une course-poursuite de 2 heures sans aucun temps mort. Incroyable.", 5);
        f32.newAvis(hugo, "La colorimétrie orange/teal est tellement maîtrisée, chaque plan est un tableau.", 4);
        f32.newAvis(clara, "Trop de bruit et d'explosions pour moi, je n'ai pas pu finir.", 2);

        f2.newAvis(hugo, "L'esthétique néon et les jeux d'ombres sont une leçon de design. Magistral.", 5);
        f2.newAvis(victor, "La question de l'intelligence artificielle et de la conscience est brillamment traitée.", 5);
        f2.newAvis(alice, "Un univers poisseux, mécanique et sombre. Une référence absolue.", 4);
        f2.newAvis(paul, "Un peu trop lent pour moi, j'ai eu du mal à rester éveillé.", 2);

        f7.newAvis(leo, "L'échelle des vaisseaux et la maîtrise du temps dans ce film sont époustouflantes.", 5);
        f7.newAvis(maxime, "Les effets spéciaux VFX sont parfaitement intégrés aux décors réels. Une prouesse.", 5);
        f7.newAvis(juliette, "L'image est belle, mais l'histoire met vraiment beaucoup de temps à démarrer.", 3);
        f7.newAvis(steve, "L'univers désertique et la gestion des ressources (l'épice) rendent le monde très crédible.", 4);

        f10.newAvis(victor, "Intéressant, mais la partie sur la gestion des bases de données et l'architecture réseau est un peu trop survolée à mon goût.", 3);
        f10.newAvis(clara, "Les dialogues vont à 100 à l'heure, la structure narrative est brillante.", 5);
        f10.newAvis(hugo, "L'interface d'époque m'a piqué les yeux, mais le film est génial.", 4);

        f14.newAvis(alice, "L'omniprésence du cuivre, des rouages et des machines à vapeur est un régal visuel. Une direction artistique incroyable.", 5);
        f14.newAvis(maxime, "L'animation traditionnelle couplée aux décors est impressionnante pour l'époque.", 4);
        f14.newAvis(leo, "Le rythme est un peu inégal sur la fin, mais l'univers rattrape le tout.", 3);

        f22.newAvis(paul, "Un incontournable. C'est exactement le genre de grande aventure et d'esprit d'équipe que j'essaie de transmettre quand j'anime des jeux de piste pour les plus jeunes.", 5);
        f22.newAvis(juliette, "Toute mon enfance ! Ça n'a pas pris une ride.", 5);
        f22.newAvis(selma, "Un super film familial, plein d'énergie et de bonne humeur.", 4);

        f26.newAvis(steve, "Gérer un enclos géant, optimiser la sécurité et la survie... ça me rappelle étrangement mes sessions d'administration de serveurs. Un classique indémodable !", 5);
        f26.newAvis(maxime, "Les dinosaures animatroniques et 3D sont encore plus beaux que dans les films d'aujourd'hui.", 5);
        f26.newAvis(clara, "Le système informatique de Dennis Nedry aurait dû être mieux sécurisé. Très bon film cependant.", 4);

        f35.newAvis(clara, "Un puzzle psychologique parfait. J'adore analyser ce genre d'intrigue.", 5);
        f35.newAvis(victor, "La critique de la société de consommation tape dans le mille.", 4);
        f35.newAvis(selma, "Un peu trop violent par moments, l'ambiance est vraiment crasseuse.", 3);
        f35.newAvis(hugo, "Le montage subliminal est une idée de génie.", 5);

        f44.newAvis(selma, "La magie opère à chaque visionnage, parfait pour l'hiver.", 5);
        f44.newAvis(juliette, "La bande originale de John Williams me donne toujours des frissons.", 5);
        f44.newAvis(paul, "Une très belle histoire d'amitié, très inspirante.", 4);
        f44.newAvis(steve, "Le château regorge de secrets à explorer, j'adore cet aspect découverte.", 4);

        f49.newAvis(maxime, "Une révolution absolue en matière de CGI et de création d'environnement 3D.", 5);
        f49.newAvis(hugo, "Les choix de couleurs bioluminescentes sur Pandora sont à couper le souffle.", 5);
        f49.newAvis(clara, "Visuellement bluffant, mais le scénario reste très classique et prévisible.", 3);
        f49.newAvis(paul, "Le message écologique est puissant et magnifiquement illustré.", 4);

        f65.newAvis(selma, "Impossible de ne pas verser une larme. Un chef-d'œuvre de tendresse.", 5);
        f65.newAvis(juliette, "Tom Hanks livre la prestation de sa vie. Une belle leçon d'optimisme.", 5);
        f65.newAvis(paul, "Le personnage de Forrest montre qu'avec un bon fond, on peut accomplir des miracles.", 5);
        f65.newAvis(leo, "La façon dont il traverse l'histoire des États-Unis est fascinante.", 4);

        f74.newAvis(clara, "Kubrick maîtrise l'espace et le malaise comme personne. Brillant.", 5);
        f74.newAvis(hugo, "Les motifs géométriques de la moquette, la symétrie des plans... esthétiquement irréprochable.", 5);
        f74.newAvis(juliette, "Beaucoup trop angoissant pour moi, j'ai fermé les yeux la moitié du temps !", 2);
        f74.newAvis(victor, "L'isolement total dans cet hôtel vide rend fou, très bien retranscrit.", 4);

        f77.newAvis(leo, "La chronologie déstructurée est osée mais fonctionne à la perfection.", 5);
        f77.newAvis(clara, "Les dialogues sont d'une efficacité redoutable. Chaque scène est culte.", 5);
        f77.newAvis(maxime, "Le twist au restaurant à la fin boucle l'histoire d'une façon magistrale.", 4);
        f77.newAvis(selma, "Un classique, même si certaines scènes sont un peu dures.", 4);


        //=================================================================//
        //                 CREATION DE LA FENETRE PRINCIPALE               //
        //=================================================================//

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

    public User getSelma() {
        return selma;
    }

    public void setSelma(User selma) {
        this.selma = selma;
    }

    public static ApplicationMedias getFactoryMedia() {
        return factoryMedia;
    }

    public User getUserLogged() {return USER_LOGGED;}

    public void setUserLogged(User user) {this.USER_LOGGED.setPseudo(String.valueOf(user));}

    public void setUserLoggedWithSelma() {this.USER_LOGGED = selma;}
}