package vues;

import main.ApplicationMedias;
import modeles.media.Media;
import modeles.media.Film;
import modeles.media.Serie;
import modeles.media.Episode; // Assure-toi que l'import est correct selon ton architecture
import modeles.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

import static utils.Utils.COLOR_BACKGROUND_DARK;
import static utils.Utils.COLOR_TEXT_LIGHT;
import static utils.Utils.COLOR_TEXT_DIM;
import static utils.Utils.COLOR_ACCENT_GREEN;
import static utils.Utils.COLOR_CARD_BACKGROUND;

public class MediaView extends JPanel {

    private static final Color COLOR_STAR_ACTIVE = new Color(255, 191, 0);

    public MediaView(Media media) {
        this.setLayout(new BorderLayout());
        this.setBackground(COLOR_BACKGROUND_DARK);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(COLOR_BACKGROUND_DARK);
        contentPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // --- 2. BLOC DU HAUT : JAQUETTE + INFORMATIONS/AVIS ---
        JPanel topSectionPanel = new JPanel();
        topSectionPanel.setLayout(new BoxLayout(topSectionPanel, BoxLayout.X_AXIS));
        topSectionPanel.setOpaque(false);
        topSectionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // A. L'Affiche
        JLabel coverLabel = new JLabel();
        coverLabel.setPreferredSize(new Dimension(220, 310));
        coverLabel.setMinimumSize(new Dimension(220, 310));
        coverLabel.setMaximumSize(new Dimension(220, 310));
        coverLabel.setBackground(COLOR_CARD_BACKGROUND);
        coverLabel.setOpaque(true);
        coverLabel.setBorder(BorderFactory.createLineBorder(COLOR_BACKGROUND_DARK, 1));
        coverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        coverLabel.setForeground(COLOR_TEXT_DIM);
        coverLabel.setText("Affiche " + media.getTitre());

        topSectionPanel.add(coverLabel);
        topSectionPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        // B. Le panneau de détails
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setOpaque(false);

        // Titre + Boutons Favoris / Vu
        JPanel titleHeaderPanel = new JPanel(new BorderLayout());
        titleHeaderPanel.setOpaque(false);
        titleHeaderPanel.setMaximumSize(new Dimension(700, 50));
        titleHeaderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitle = new JLabel(media.getTitre());
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblTitle.setForeground(COLOR_TEXT_LIGHT);
        titleHeaderPanel.add(lblTitle, BorderLayout.WEST);

        JPanel circleButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        circleButtonsPanel.setOpaque(false);

        JButton btnHeart = createCircleButton("❤");
        JButton btnCheck = createCircleButton("\uD83D\uDC4D");

        User currentUser = ApplicationMedias.getFactoryMedia().getUserLogged();

        boolean isAlreadyLiked = currentUser.getLike().getLesMedias().contains(media);
        btnHeart.setBackground(isAlreadyLiked ? Color.RED : COLOR_TEXT_LIGHT);

        boolean isAlreadyVu = currentUser.getVu().getLesMedias().contains(media);
        btnCheck.setBackground(isAlreadyVu ? COLOR_ACCENT_GREEN : COLOR_TEXT_LIGHT);

        btnHeart.addActionListener(e -> {
            currentUser.toggleCoupDeCoeur(media);
            boolean isLiked = currentUser.getLike().getLesMedias().contains(media);
            btnHeart.setBackground(isLiked ? Color.RED : COLOR_TEXT_LIGHT);
            btnHeart.repaint();
        });

        btnCheck.addActionListener(e -> {
            currentUser.toggleMediaVu(media);
            boolean isVu = currentUser.getVu().getLesMedias().contains(media);
            btnCheck.setBackground(isVu ? COLOR_ACCENT_GREEN : COLOR_TEXT_LIGHT);
            btnCheck.repaint();
        });
        circleButtonsPanel.add(btnHeart);
        circleButtonsPanel.add(btnCheck);

        circleButtonsPanel.add(btnHeart);
        circleButtonsPanel.add(btnCheck);
        titleHeaderPanel.add(circleButtonsPanel, BorderLayout.EAST);

        detailsPanel.add(titleHeaderPanel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // --- TRAITEMENT CONDITIONNEL DES MÉTADONNÉES ---
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String anneeStr = (media.getDate() != null) ? yearFormat.format(media.getDate()) : "----";
        String categorieStr = (media.getLaCategorie() != null) ? media.getLaCategorie().toString() : "Général";

        String specifiqueStr = "";
        if (media instanceof Film) {
            Film film = (Film) media;
            specifiqueStr = film.getDuree() != null ? film.getDuree() : "Durée inconnue";
        } else if (media instanceof Serie) {
            Serie serie = (Serie) media;
            specifiqueStr = serie.getNombreEpisodes() + " Épisodes";
        }

        JLabel lblMeta = new JLabel(anneeStr + "  •  " + specifiqueStr + "  •  " + categorieStr);
        lblMeta.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblMeta.setForeground(COLOR_TEXT_DIM);
        lblMeta.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(lblMeta);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Réalisateur
        JPanel directorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        directorPanel.setOpaque(false);
        directorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel lblRealText = new JLabel("Réalisé par ");
        lblRealText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblRealText.setForeground(COLOR_TEXT_LIGHT);
        JLabel lblRealName = new JLabel(media.getRealisateur());
        lblRealName.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblRealName.setForeground(COLOR_ACCENT_GREEN);
        directorPanel.add(lblRealText);
        directorPanel.add(lblRealName);
        detailsPanel.add(directorPanel);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Bloc Score Moyen
        JPanel scoreBadge = new JPanel();
        scoreBadge.setLayout(new BoxLayout(scoreBadge, BoxLayout.Y_AXIS));
        scoreBadge.setBackground(COLOR_CARD_BACKGROUND);
        scoreBadge.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        scoreBadge.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreBadge.setMaximumSize(new Dimension(120, 60));

        JLabel lblScoreTitle = new JLabel("SCORE MOYEN");
        lblScoreTitle.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblScoreTitle.setForeground(COLOR_TEXT_DIM);
        lblScoreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblScoreValue = new JLabel(String.format("%.1f / 5", media.getScoreMoyen()).replace(",", "."));
        lblScoreValue.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblScoreValue.setForeground(COLOR_ACCENT_GREEN);
        lblScoreValue.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreBadge.add(lblScoreTitle);
        scoreBadge.add(Box.createRigidArea(new Dimension(0, 2)));
        scoreBadge.add(lblScoreValue);
        detailsPanel.add(scoreBadge);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Section "Votre avis"
        JLabel lblVotreAvis = new JLabel("Votre avis");
        lblVotreAvis.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblVotreAvis.setForeground(COLOR_TEXT_LIGHT);
        lblVotreAvis.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(lblVotreAvis);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Ligne des 5 étoiles
        JPanel starsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        starsPanel.setOpaque(false);
        starsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (int i = 0; i < 5; i++) {
            JLabel star = new JLabel(i < 4 ? "★" : "☆");
            star.setFont(new Font("SansSerif", Font.PLAIN, 22));
            star.setForeground(i < 4 ? COLOR_STAR_ACTIVE : COLOR_TEXT_DIM);
            star.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            starsPanel.add(star);
        }
        detailsPanel.add(starsPanel);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Zone de texte pour la critique
        JTextArea txtCritique = new JTextArea("Écrivez votre critique ici...");
        txtCritique.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtCritique.setBackground(COLOR_CARD_BACKGROUND);
        txtCritique.setForeground(COLOR_TEXT_DIM);
        txtCritique.setCaretColor(Color.WHITE);
        txtCritique.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BACKGROUND_DARK, 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        txtCritique.setLineWrap(true);
        txtCritique.setWrapStyleWord(true);
        txtCritique.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtCritique.setMaximumSize(new Dimension(550, 70));
        txtCritique.setPreferredSize(new Dimension(550, 70));
        detailsPanel.add(txtCritique);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Boutons actions sous la critique
        JPanel actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        actionButtonsPanel.setOpaque(false);
        actionButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnSaveAvis = new JButton("Enregistrer l'avis");
        btnSaveAvis.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSaveAvis.setBackground(COLOR_ACCENT_GREEN);
        btnSaveAvis.setForeground(COLOR_BACKGROUND_DARK);
        btnSaveAvis.setFocusPainted(false);
        btnSaveAvis.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnSaveAvis.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton btnAddList = new JButton("Ajouter à une liste");
        btnAddList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnAddList.setForeground(COLOR_TEXT_LIGHT);
        btnAddList.setContentAreaFilled(false);
        btnAddList.setFocusPainted(false);
        btnAddList.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_TEXT_DIM, 1),
                BorderFactory.createEmptyBorder(9, 20, 9, 20)
        ));
        btnAddList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        actionButtonsPanel.add(btnSaveAvis);
        actionButtonsPanel.add(btnAddList);
        detailsPanel.add(actionButtonsPanel);

        topSectionPanel.add(detailsPanel);
        contentPanel.add(topSectionPanel);

        // --- 3. TRAITEMENT CONDITIONNEL DE LA SECTION ÉPISODES (SÉRIE UNIQUEMENT) ---
        if (media instanceof Serie) {
            Serie laSerie = (Serie) media;

            contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

            JLabel lblEpisodesTitle = new JLabel("Épisodes");
            lblEpisodesTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
            lblEpisodesTitle.setForeground(COLOR_TEXT_LIGHT);
            lblEpisodesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPanel.add(lblEpisodesTitle);

            contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

            // Génération dynamique de la liste s'il y a des épisodes configurés
            if (laSerie.getLesEpisodes() != null && !laSerie.getLesEpisodes().isEmpty()) {
                for (Episode ep : laSerie.getLesEpisodes()) {
                    contentPanel.add(createEpisodeRow(ep.getTitre(), ep.getDuree())); // Ajuste selon les méthodes de ton objet Episode
                    contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            } else {
                // Fallback si la liste d'épisodes est vide
                contentPanel.add(createEpisodeRow("S01E01 - Pilot", "58 min"));
            }
        }

        // Intégration globale de la vue dans le ScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(COLOR_BACKGROUND_DARK);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    // Méthode utilitaire pour générer proprement une ligne d'épisodre
    private JPanel createEpisodeRow(String title, String duration) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(COLOR_CARD_BACKGROUND);
        row.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(850, 50));

        JLabel lblName = new JLabel(title);
        lblName.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblName.setForeground(COLOR_TEXT_LIGHT);
        row.add(lblName, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightPanel.setOpaque(false);

        JLabel lblDuration = new JLabel(duration);
        lblDuration.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDuration.setForeground(COLOR_TEXT_DIM);

        JLabel lblWatched = new JLabel("✓ Vu");
        lblWatched.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblWatched.setForeground(COLOR_ACCENT_GREEN);

        rightPanel.add(lblDuration);
        rightPanel.add(lblWatched);
        row.add(rightPanel, BorderLayout.EAST);

        return row;
    }

    private JButton createCircleButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                // Amélioration de la qualité du rendu
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                // Dessin du cercle AVEC LA COULEUR D'ARRIÈRE-PLAN DYNAMIQUE
                g2.setColor(getBackground());
                g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
                g2.dispose();

                // Dessin du texte/emoji par-dessus
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        button.setForeground(COLOR_BACKGROUND_DARK); // La couleur de l'émoji (souvent noir/sombre)
        button.setBackground(COLOR_TEXT_LIGHT); // Couleur de base du cercle
        button.setPreferredSize(new Dimension(50, 50));

        button.setMargin(new java.awt.Insets(0, 0, 0, 0));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return button;
    }
}