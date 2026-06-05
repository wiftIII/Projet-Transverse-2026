package utils;

import modeles.media.Episode;
import modeles.media.Serie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Utils {

    public static String COLOR = "DARK";

    // constantes utilisées
    public static Color COLOR_BACKGROUND_DARK = new Color(18, 22, 28);
    public static Color COLOR_SIDEBAR_BACKGROUND = new Color(25, 30, 38);
    public static Color COLOR_TEXT_LIGHT = new Color(230, 230, 230);
    public static Color COLOR_TEXT_DIM = new Color(150, 150, 150);
    public static Color COLOR_ACCENT_GREEN = new Color(50, 205, 50);
    public static Color COLOR_CARD_BACKGROUND = new Color(30, 35, 43);

    // couleurs dark
    public static Color COLOR_BACKGROUND_DARK_D = new Color(18, 22, 28);
    public static Color COLOR_SIDEBAR_BACKGROUND_D = new Color(25, 30, 38);
    public static Color COLOR_TEXT_LIGHT_D = new Color(230, 230, 230);
    public static Color COLOR_TEXT_DIM_D = new Color(150, 150, 150);
    public static Color COLOR_ACCENT_GREEN_D = new Color(50, 205, 50);
    public static Color COLOR_CARD_BACKGROUND_D = new Color(30, 35, 43);

    // couleurs claires
    public static Color COLOR_BACKGROUND_LIGHT = new Color(245, 247, 250);
    public static Color COLOR_SIDEBAR_BACKGROUND_LIGHT = new Color(255, 255, 255);
    public static Color COLOR_TEXT_DARK = new Color(30, 35, 43);
    public static Color COLOR_TEXT_DIM_LIGHT = new Color(110, 115, 125);
    public static Color COLOR_ACCENT_GREEN_LIGHT = new Color(40, 180, 40);
    public static Color COLOR_CARD_BACKGROUND_LIGHT = new Color(255, 255, 255);

    public static void changeTheme(){
        if (COLOR == "DARK") {
            COLOR = "LIGHT";
                COLOR_BACKGROUND_DARK = COLOR_BACKGROUND_LIGHT;
                COLOR_SIDEBAR_BACKGROUND = COLOR_SIDEBAR_BACKGROUND_LIGHT;
                COLOR_TEXT_DARK = COLOR_TEXT_LIGHT;
                COLOR_TEXT_DIM = COLOR_TEXT_DIM_LIGHT;
                COLOR_ACCENT_GREEN = COLOR_ACCENT_GREEN_LIGHT;
                COLOR_CARD_BACKGROUND = COLOR_CARD_BACKGROUND_LIGHT;
        }
        if (COLOR == "LIGHT"){
            COLOR = "DARK";
            COLOR_BACKGROUND_DARK = COLOR_BACKGROUND_DARK_D;
            COLOR_SIDEBAR_BACKGROUND = COLOR_SIDEBAR_BACKGROUND_D;
            COLOR_TEXT_DARK = COLOR_TEXT_LIGHT_D;
            COLOR_TEXT_DIM = COLOR_TEXT_DIM_D;
            COLOR_ACCENT_GREEN = COLOR_ACCENT_GREEN_D;
            COLOR_CARD_BACKGROUND = COLOR_CARD_BACKGROUND_D;
        }
    }

    public static JLabel createImageLabel(String imagePath, int width, int height) {
        JLabel label = new JLabel();

        try {
            // 1. Charger l'image d'origine
            ImageIcon originalIcon = new ImageIcon(imagePath);

            // 2. Extraire l'objet Image pour le redimensionner
            Image image = originalIcon.getImage();

            // 3. Redimensionner l'image (Image.SCALE_SMOOTH donne le meilleur rendu visuel)
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // 4. Re-créer un ImageIcon avec l'image redimensionnée
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // 5. L'appliquer au JLabel
            label.setIcon(scaledIcon);

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image : " + imagePath);
            // Si l'image n'est pas trouvée, on met un texte par défaut
            label.setText("Image introuvable");
            label.setForeground(Color.RED);
        }

        // On fixe les dimensions du JLabel pour qu'il respecte la taille demandée
        Dimension size = new Dimension(width, height);
        label.setPreferredSize(size);
        label.setMaximumSize(size);
        label.setMinimumSize(size);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        return label;
    }

    public static JLabel createBadge(String text) {
        JLabel badge = new JLabel(" @" + text + "  ");
        badge.setFont(new Font("Arial", Font.PLAIN, 14));
        badge.setForeground(COLOR_TEXT_LIGHT);
        badge.setOpaque(true);
        badge.setBackground(COLOR_CARD_BACKGROUND);
        badge.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                new EmptyBorder(5, 5, 5, 5)
        ));
        return badge;
    }


    public static void genererEpisodesPourSerie(Serie serie, String dureeMoyenne, int limiteEpisodes) {
        Episode episodePrecedent = null;

        // On prend le minimum entre la limite fixée et le vrai nombre d'épisodes de la série
        int nbA_Generer = Math.min(limiteEpisodes, serie.getNombreEpisodes());

        for (int i = 1; i <= nbA_Generer; i++) {

            int numSaison = 1; // Pour simplifier, on met tout en saison 1
            String titreEpisode = "Épisode " + i;

            // 1. Création de l'épisode
            Episode nouvelEpisode = new Episode(
                    serie.getLaCategorie(),
                    titreEpisode,
                    serie.getDate(),
                    serie.getRealisateur(),
                    serie,
                    null, // Episode suivant (sera lié au tour prochain)
                    episodePrecedent,
                    numSaison,
                    dureeMoyenne
            );

            // 2. Mise à jour de la chaîne (Lien avec le précédent)
            if (episodePrecedent != null) {
                episodePrecedent.setEpisodeSuivant(nouvelEpisode);
            }

            // 3. Ajout à la série
            serie.ajouterEpisode(nouvelEpisode);

            // 4. Préparation pour le tour suivant
            episodePrecedent = nouvelEpisode;
        }
    }

}
