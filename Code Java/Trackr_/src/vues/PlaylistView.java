
package vues;

import controleurs.PlaylistController;
import modeles.media.Media;
import modeles.user.Playlist;
import utils.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static utils.Utils.*;

public class PlaylistView extends JPanel {


    Playlist playlist;

    public PlaylistView(Playlist playlist){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(COLOR_BACKGROUND_DARK);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.playlist = playlist;

        //ajout du header
        this.add(createHeaderSection());
        this.add(Box.createRigidArea(new Dimension(0, 40)));

        //ajout de la grille de playlist
        JScrollPane scrollPane = new JScrollPane(createPlaylistsGrid());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(COLOR_BACKGROUND_DARK);
        scrollPane.getViewport().setBackground(COLOR_BACKGROUND_DARK);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
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

        JLabel titleLabel = new JLabel(playlist.getNom());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(COLOR_TEXT_LIGHT);

        int nbMedia = (playlist.getLesMedias().size());
        JLabel countLabel = new JLabel(nbMedia + " Media");
        countLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        countLabel.setForeground(COLOR_ACCENT_GREEN);

        leftPanel.add(titleLabel);
        leftPanel.add(countLabel);

        //allignement et assemblage
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(COLOR_BACKGROUND_DARK);
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
        if (playlist.getLesMedias() != null && !playlist.getLesMedias().isEmpty()) {
            for (Media media : playlist.getLesMedias()) {
                gridPanel.add(createMediaItem(media));
            }
        } else {
            // Message affiché si aucune playlist n'existe
            JLabel emptyLabel = new JLabel("Vous n'avez aucun média.");
            emptyLabel.setForeground(COLOR_TEXT_DIM);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            gridPanel.add(emptyLabel);
        }
        return gridPanel;
    }

    //creation d'un block media
    private JPanel createMediaItem(Media media) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COLOR_BACKGROUND_DARK);

        // On augmente légèrement la hauteur pour accommoder le nouveau bouton
        card.setPreferredSize(new Dimension(200, 300));

        // --- 1. Le Bouton Image (qui remplace le JLabel et le texte) ---
        String cheminImage = "src/images/" + media.getTitre() +  ".jpg";
        JButton imageButton = new JButton();

        try {
            ImageIcon originalIcon = new ImageIcon(cheminImage);
            Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageButton.setIcon(new ImageIcon(scaledImage));

            imageButton.setBorderPainted(false);
            imageButton.setFocusPainted(false);
            imageButton.setContentAreaFilled(false);
            imageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        } catch (Exception e) {
            imageButton.setText(";)");
            imageButton.setFont(new Font("Arial", Font.PLAIN, 50));
            imageButton.setBackground(COLOR_CARD_BACKGROUND);
            imageButton.setForeground(COLOR_TEXT_DIM);
            imageButton.setOpaque(true);
        }

        Dimension imageSize = new Dimension(200, 200);
        imageButton.setPreferredSize(imageSize);
        imageButton.setMaximumSize(imageSize);
        imageButton.setMinimumSize(imageSize);
        imageButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        PlaylistController.openMediaView(imageButton, this, media);

        // --- 2. Le Titre ---
        JLabel titleLabel = new JLabel(media.getTitre());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(COLOR_TEXT_LIGHT);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- 3. Le nombre d'éléments (La note) ---
        int noteMedia = (int) media.getScoreMoyen();
        String texteElement = "/5";
        JLabel noteLabel = new JLabel(noteMedia + texteElement);
        noteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        noteLabel.setForeground(COLOR_TEXT_DIM);
        noteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton removeButton = new JButton("🗑️");

        removeButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));

        removeButton.setForeground(new Color(220, 50, 50));
        removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        removeButton.setBorderPainted(false);
        removeButton.setContentAreaFilled(false);
        removeButton.setFocusPainted(false);

        removeButton.addActionListener(e -> {
            int reponse = JOptionPane.showConfirmDialog(
                    this,
                    "Êtes-vous sûr de vouloir retirer '" + media.getTitre() + "' de la playlist ?",
                    "Confirmation de retrait",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (reponse == JOptionPane.YES_OPTION) {
                // 1. Suppression dans les données (Modèle)
                // Note: pas besoin de passer "card" au contrôleur si on gère l'affichage ici
                PlaylistController.retirerMediaDePlaylist(media, playlist, card);

                // 2. CORRECTION : Suppression visuelle via le parent direct
                Container parentContainer = card.getParent(); // On récupère le conteneur de la carte

                if (parentContainer != null) {
                    parentContainer.remove(card); // On supprime la carte de son parent
                    parentContainer.revalidate(); // On recalcule la disposition
                    parentContainer.repaint();    // On redessine l'écran
                } else {
                    // Sécurité de secours : si on ne trouve pas le parent, on cache la carte
                    card.setVisible(false);
                }

                // 3. Petite popup de succès
                JOptionPane.showMessageDialog(
                        this,
                        media.getTitre() + " a bien été retiré.",
                        "Succès",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        // --- Assemblage de la carte ---
        card.add(imageButton);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(noteLabel);
        card.add(Box.createRigidArea(new Dimension(0, 8))); // Espace avant le bouton
        card.add(removeButton); // Ajout du bouton retirer

        return card;
    }
}
