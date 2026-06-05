package vues;

import controleurs.PlaylistsController;
import modeles.media.Media;
import modeles.user.Playlist;
import modeles.user.User;
import utils.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static utils.Utils.*;

public class PlaylistsView extends JPanel{

    User user;

    public PlaylistsView(User user) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(COLOR_BACKGROUND_DARK);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.user = user;

        //ajout du header
        this.add(createHeaderSection());
        this.add(Box.createRigidArea(new Dimension(0, 40)));

        //ajout de la grille de playlist
        JScrollPane scrollPane = new JScrollPane(createPlaylistsGrid());
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Enlever la bordure disgracieuse
        scrollPane.setBackground(COLOR_BACKGROUND_DARK);
        scrollPane.getViewport().setBackground(COLOR_BACKGROUND_DARK);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Rendre le défilement plus fluide
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
    }

    //creation du header
    private JPanel createHeaderSection() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_BACKGROUND_DARK);
        // On fixe une hauteur max pour éviter que le header ne s'étire verticalement
        headerPanel.setMaximumSize(new Dimension(2000, 60));

        // Titre et Compteur
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(COLOR_BACKGROUND_DARK);

        JLabel titleLabel = new JLabel("Mes listes personnalisées");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(COLOR_TEXT_LIGHT);

        int nbPlaylists = (user.getMesPlaylists() != null) ? user.getMesPlaylists().size() : 0;
        JLabel countLabel = new JLabel(nbPlaylists + " PLAYLISTS");
        countLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        countLabel.setForeground(COLOR_ACCENT_GREEN);

        leftPanel.add(titleLabel);
        leftPanel.add(countLabel);

        // Bouton "Creer"
        JButton btnCreate = new JButton("+ CRÉER UNE LISTE");
        btnCreate.setFocusPainted(false);
        btnCreate.setBackground(COLOR_ACCENT_GREEN);
        btnCreate.setForeground(Color.BLACK);
        btnCreate.setFont(new Font("Arial", Font.BOLD, 12));
        btnCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCreate.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //allignement et assemblage
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(COLOR_BACKGROUND_DARK);
        rightPanel.add(btnCreate);
        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        return headerPanel;
    }

    //creation du conteneur principal en temps que grille
    private JPanel createPlaylistsGrid() {
        // FlowLayout permet aux éléments de se placer les uns après les autres
        // et de passer à la ligne suivante si la fenêtre est trop petite.
        JPanel gridPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 25, 25));
        gridPanel.setBackground(COLOR_BACKGROUND_DARK);

        // Si l'utilisateur a des playlists, on les génère
        if (user.getMesPlaylists() != null && !user.getMesPlaylists().isEmpty()) {
            for (Playlist playlist : user.getMesPlaylists()) {
                gridPanel.add(createPlaylistItem(playlist));
            }
        } else {
            // Message affiché si aucune playlist n'existe
            JLabel emptyLabel = new JLabel("Vous n'avez pas encore de playlist.");
            emptyLabel.setForeground(COLOR_TEXT_DIM);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            gridPanel.add(emptyLabel);
        }

        return gridPanel;
    }

    //creation d'un block playlist
//creation d'un block playlist
    private JPanel createPlaylistItem(Playlist playlist) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COLOR_BACKGROUND_DARK);

        // Fixer la taille maximum
        Dimension cardSize = new Dimension(200, 350);
        card.setPreferredSize(cardSize);
        card.setMaximumSize(cardSize);
        card.setMinimumSize(cardSize);

        // --- 1. L'image (Mosaïque façon Spotify) ---
        JButton imageButton = new JButton();
        imageButton.setBorderPainted(false);
        imageButton.setFocusPainted(false);
        imageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        Dimension imageSize = new Dimension(200, 200);
        imageButton.setPreferredSize(imageSize);
        imageButton.setMaximumSize(imageSize);
        imageButton.setMinimumSize(imageSize);

        // Récupération des médias
        List<Media> medias = playlist.getLesMedias();
        int mediaCount = (medias != null) ? medias.size() : 0;
        boolean isImageSet = false;

        if (mediaCount >= 4) {
            // --- CAS 1 : 4 médias ou plus -> Mosaïque 2x2 ---
            BufferedImage composite = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = composite.createGraphics();

            for (int i = 0; i < 4; i++) {
                String chemin = "src/images/" + medias.get(i).getTitre() + ".jpg";
                File file = new File(chemin);

                int x = (i % 2) * 100; // Colonne 0 ou 1
                int y = (i / 2) * 100; // Ligne 0 ou 1

                if (file.exists()) {
                    Image img = new ImageIcon(chemin).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    new ImageIcon(img); // Force le chargement de l'image en mémoire
                    g2d.drawImage(img, x, y, null);
                } else {
                    // Si l'image d'un des médias manque, on met un fond gris foncé
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.fillRect(x, y, 100, 100);
                }
            }
            g2d.dispose(); // Libère les ressources graphiques
            imageButton.setIcon(new ImageIcon(composite));
            imageButton.setContentAreaFilled(false);
            isImageSet = true;

        } else if (mediaCount > 0) {
            // --- CAS 2 : Entre 1 et 3 médias -> On prend juste la première image ---
            String chemin = "src/images/" + medias.get(0).getTitre() + ".jpg";
            File file = new File(chemin);
            if (file.exists()) {
                Image img = new ImageIcon(chemin).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                imageButton.setIcon(new ImageIcon(img));
                imageButton.setContentAreaFilled(false);
                isImageSet = true;
            }
        }

        // --- CAS 3 : Fallback (Playlist vide ou images introuvables) ---
        if (!isImageSet) {
            imageButton.setText(";)");
            imageButton.setFont(new Font("Arial", Font.PLAIN, 50));
            imageButton.setBackground(COLOR_CARD_BACKGROUND);
            imageButton.setForeground(COLOR_TEXT_DIM);
            imageButton.setOpaque(true);
            imageButton.setContentAreaFilled(true); // Requis pour afficher la couleur de fond
        }

        // Ajout du listener
        PlaylistsController.openPlaylistView(imageButton, this, playlist);

        // --- 2. Le Titre ---
        JLabel titleLabel = new JLabel(playlist.getNom());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(COLOR_TEXT_LIGHT);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- 3. Le nombre d'éléments ---
        String texteElement = (mediaCount <= 1) ? " élément" : " éléments";
        JLabel countLabel = new JLabel(mediaCount + texteElement);
        countLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        countLabel.setForeground(COLOR_TEXT_DIM);
        countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- Assemblage de la carte ---
        card.add(imageButton);
        card.add(Box.createRigidArea(new Dimension(0, 8))); // Espace entre l'image et le titre
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 2))); // Espace minimal entre les deux textes
        card.add(countLabel);
        card.add(Box.createVerticalGlue()); // Absorbe l'espace restant

        return card;
    }



}
