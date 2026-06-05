package vues;

import controleurs.UserController;
import modeles.media.Media;
import modeles.user.Avis;
import modeles.user.Playlist;
import modeles.user.User;
import utils.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static utils.Utils.*;

// Vue globale du profil utilisateur
public class UserView extends JPanel {

    User user;

    public UserView(User user) {
        this.user = user;

        // Configuration de base du panel
        this.setLayout(new BorderLayout());
        this.setBackground(COLOR_BACKGROUND_DARK);

        // Panel principal qui va contenir tous les éléments empilés
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(COLOR_BACKGROUND_DARK);
        contentPanel.setBorder(new EmptyBorder(30, 40, 40, 40));

        // Ajout des différentes sections avec un peu d'espace entre elles
        contentPanel.add(createHeaderSection(user));
        contentPanel.add(Box.createVerticalStrut(40));

        contentPanel.add(createSectionTitle("Mes Abonnements"));
        contentPanel.add(createAbonnementsSection(contentPanel));
        contentPanel.add(Box.createVerticalStrut(40));

        contentPanel.add(createSectionTitle("Mes Abonné"));
        contentPanel.add(createAbonnesSection(contentPanel));
        contentPanel.add(Box.createVerticalStrut(40));

        // Section des avis
        contentPanel.add(createDernierAvis());

        // Section des playlists
        contentPanel.add(createSectionTitle("Mes Playlists"));
        contentPanel.add(createPlaylistPerso());
        contentPanel.add(Box.createVerticalStrut(10));

        // On met tout ça dans un ScrollPane pour pouvoir scroller
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Scroll plus fluide
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(COLOR_BACKGROUND_DARK);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    // Génère le haut du profil : Avatar, Pseudo et les 3 stats
    private JPanel createHeaderSection(User user) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_BACKGROUND_DARK);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Partie gauche : Avatar + Texte
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        infoPanel.setBackground(COLOR_BACKGROUND_DARK);

        JLabel avatar = new JLabel(user.getPseudo().substring(0, 2).toUpperCase(), SwingConstants.CENTER);
        avatar.setFont(new Font("Arial", Font.BOLD, 30));
        avatar.setForeground(COLOR_ACCENT_GREEN);
        avatar.setOpaque(true);
        avatar.setBackground(COLOR_CARD_BACKGROUND);
        avatar.setPreferredSize(new Dimension(100, 100));

        JPanel textInfoPanel = new JPanel();
        textInfoPanel.setLayout(new BoxLayout(textInfoPanel, BoxLayout.Y_AXIS));
        textInfoPanel.setBackground(COLOR_BACKGROUND_DARK);

        JLabel nameLabel = new JLabel(user.getPseudo());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 32));
        nameLabel.setForeground(COLOR_TEXT_LIGHT);

        JLabel memberSince = new JLabel("Membre depuis 2025");
        memberSince.setFont(new Font("Arial", Font.PLAIN, 14));
        memberSince.setForeground(COLOR_TEXT_DIM);

        textInfoPanel.add(Box.createVerticalStrut(20));
        textInfoPanel.add(nameLabel);
        textInfoPanel.add(Box.createVerticalStrut(5));
        textInfoPanel.add(memberSince);

        infoPanel.add(avatar);
        infoPanel.add(textInfoPanel);

        // Partie droite : Les stats
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
        statsPanel.setBackground(COLOR_BACKGROUND_DARK);

        statsPanel.add(createStatItem( user.getVu().getLesMedias().size() + "", "VUS"));
        statsPanel.add(createStatItem(user.getFollower().size() + "", "FOLLOWERS"));
        statsPanel.add(createStatItem(user.getSuivi().size() + "", "SUIVIS"));

        headerPanel.add(infoPanel, BorderLayout.WEST);
        headerPanel.add(statsPanel, BorderLayout.EAST);

        return headerPanel;
    }


    private JPanel createAbonnementsSection(JPanel mainContainer) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10)) {
            @Override
            public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
            }
        };
        panel.setBackground(COLOR_BACKGROUND_DARK);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.user.getSuivi().forEach(user1 -> {
            // On récupère le badge (qui peut être un JButton, un JPanel, ou un JLabel)
            Component badge = createBadge(user1.getPseudo());

            // Si votre badge est un JButton, utilisez un ActionListener :
            if (badge instanceof JButton) {
                ((JButton) badge).addActionListener(e -> UserController.openViewUser(user1, mainContainer));
            }
            // Si votre badge est un JPanel ou JLabel, utilisez un MouseListener :
            else {
                badge.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        UserController.openViewUser(user1, mainContainer);
                    }

                    // Optionnel : Changer le curseur en petite main au survol pour indiquer que c'est cliquable
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        badge.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                });
            }

            panel.add(badge);
        });

        return panel;
    }

    private JPanel createAbonnesSection(JPanel mainContainer) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10)) {
            @Override
            public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
            }
        };
        panel.setBackground(COLOR_BACKGROUND_DARK);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Changement ici : on itère sur les followers (abonnés) au lieu des suivis
        this.user.getFollower().forEach(user1 -> {
            // On récupère le badge (qui peut être un JButton, un JPanel, ou un JLabel)
            Component badge = createBadge(user1.getPseudo());

            // Si votre badge est un JButton, utilisez un ActionListener :
            if (badge instanceof JButton) {
                ((JButton) badge).addActionListener(e -> UserController.openViewUser(user1, mainContainer));
            }
            // Si votre badge est un JPanel ou JLabel, utilisez un MouseListener :
            else {
                badge.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        UserController.openViewUser(user1, mainContainer);
                    }

                    // Optionnel : Changer le curseur en petite main au survol pour indiquer que c'est cliquable
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        badge.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                });
            }

            panel.add(badge);
        });

        return panel;
    }

    // Affiche max 4 playlists et un bouton "Voir plus" si besoin
    private JPanel createPlaylistPerso() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15)) {
            @Override
            public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
            }
        };
        panel.setBackground(COLOR_BACKGROUND_DARK);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        var playlists = this.user.getMesPlaylists();

        // On se limite à 4 éléments
        playlists.stream()
                .limit(4)
                .forEach(playlist -> {
                    panel.add(createPlaylistItem(playlist));
                });

        // Bouton pour afficher la suite si on dépasse 4
        if (playlists.size() > 4) {
            JButton btnVoirPlus = new JButton("Voir plus");
            btnVoirPlus.setFont(new Font("Arial", Font.BOLD, 14));
            btnVoirPlus.setForeground(COLOR_ACCENT_GREEN);
            btnVoirPlus.setBackground(COLOR_CARD_BACKGROUND);
            btnVoirPlus.setFocusPainted(false);
            btnVoirPlus.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnVoirPlus.setPreferredSize(new Dimension(150, 50));

            btnVoirPlus.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                    new EmptyBorder(10, 20, 10, 20)
            ));

            JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            btnWrapper.setBackground(COLOR_BACKGROUND_DARK);
            btnWrapper.setBorder(new EmptyBorder(80, 20, 0, 0));
            btnWrapper.add(btnVoirPlus);

            // Redirection via le controller
            UserController.openViewPlaylist(btnVoirPlus, this);

            panel.add(btnWrapper);
        }

        return panel;
    }

    private JPanel createDernierAvis() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(COLOR_BACKGROUND_DARK);
        container.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // Récupère le nombre total d'avis
        long countAvis = user.getSesAvis()
                .stream()
                .filter(avis -> avis != null && avis.getCreateur() != null)
                .filter(avis -> avis.getCreateur().equals(user))
                .count();

        container.add(createSectionTitle("Mes Derniers avis (" + countAvis + ")"));


        JPanel cardsWrapper = new JPanel(new WrapLayout(FlowLayout.LEFT, 15, 15));
        cardsWrapper.setBackground(COLOR_BACKGROUND_DARK);
        cardsWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Ajoute chaque avis dans le Wrapper (qui va les aligner et les passer à la ligne)
        user.getSesAvis().stream()
                .filter(avis -> avis != null && avis.getCreateur() != null)
                .filter(avis -> avis.getCreateur().equals(user))
                .forEach(avis -> {
                    cardsWrapper.add(createCarteCritique(avis));
                });

        container.add(cardsWrapper);

        container.add(cardsWrapper);
        return container;
    }

    private JPanel createCarteCritique(Avis avis) {
        Media media = avis.getMediaAssocie();

        // On passe sur un BorderLayout vertical (Info film en haut, Avis en bas)
        JPanel card = new JPanel(new BorderLayout(0, 15)); // 15px d'espace entre le haut et le bas
        card.setBackground(COLOR_CARD_BACKGROUND);
        card.setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- TAILLE FIXE POUR LES CARTES ---
        // C'est ce qui permet au FlowLayout de les aligner proprement
        Dimension cardSize = new Dimension(380, 220);
        card.setPreferredSize(cardSize);
        card.setMinimumSize(cardSize);
        card.setMaximumSize(cardSize);

        // --- PARTIE HAUTE (Infos du média) ---
        JPanel filmPanel = new JPanel();
        filmPanel.setLayout(new BoxLayout(filmPanel, BoxLayout.Y_AXIS));
        filmPanel.setBackground(COLOR_CARD_BACKGROUND);

        JLabel titleLabel = new JLabel(media.getTitre().toUpperCase());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Police légèrement réduite
        titleLabel.setForeground(COLOR_ACCENT_GREEN);

        JLabel directorLabel = new JLabel("De " + media.getRealisateur());
        directorLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        directorLabel.setForeground(COLOR_TEXT_DIM);

        JLabel catLabel = new JLabel(media.getLaCategorie().toString());
        catLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        catLabel.setForeground(COLOR_TEXT_LIGHT);
        catLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_TEXT_DIM),
                new EmptyBorder(2, 5, 2, 5)
        ));

        filmPanel.add(titleLabel);
        filmPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        filmPanel.add(directorLabel);
        filmPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        filmPanel.add(catLabel);

        // --- PARTIE BASSE (Note et commentaire) ---
        JPanel avisPanel = new JPanel(new BorderLayout(0, 8)); // 8px entre les étoiles et le texte
        avisPanel.setBackground(COLOR_CARD_BACKGROUND);

        String etoiles = "\u2605".repeat(avis.getNombreEtoiles()) + "\u2606".repeat(5 - avis.getNombreEtoiles());
        JLabel starsLabel = new JLabel(etoiles + " (" + avis.getNombreEtoiles() + "/5)  •  " + avis.getDateDeCreation().toLocaleString().split(",")[0]);
        starsLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        starsLabel.setForeground(new Color(255, 215, 0));

        JTextArea commentArea = new JTextArea(avis.getCommentaire());
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setEditable(false);
        commentArea.setBackground(COLOR_CARD_BACKGROUND);
        commentArea.setForeground(COLOR_TEXT_LIGHT);
        commentArea.setFont(new Font("Arial", Font.PLAIN, 13));

        avisPanel.add(starsLabel, BorderLayout.NORTH);
        avisPanel.add(commentArea, BorderLayout.CENTER);

        // On assemble le tout
        card.add(filmPanel, BorderLayout.NORTH);
        card.add(avisPanel, BorderLayout.CENTER);

        return card;
    }

    // Composant UI : Une carte de playlist (Image + Titre + Compteur)
    private JPanel createPlaylistItem(Playlist playlist) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COLOR_BACKGROUND_DARK);
        Dimension cardSize = new Dimension(200, 260);
        card.setPreferredSize(cardSize);
        card.setMinimumSize(cardSize);
        card.setMaximumSize(cardSize);

        JLabel imagePlaceholder = new JLabel("<html><span style='font-family: Segoe UI Emoji;'>🎥</span></html>", SwingConstants.CENTER);
        imagePlaceholder.setFont(new Font("Arial", Font.PLAIN, 50));
        imagePlaceholder.setOpaque(true);
        imagePlaceholder.setBackground(COLOR_CARD_BACKGROUND);
        imagePlaceholder.setForeground(COLOR_TEXT_DIM);

        Dimension imageSize = new Dimension(200, 200);
        imagePlaceholder.setPreferredSize(imageSize);
        imagePlaceholder.setMaximumSize(imageSize);
        imagePlaceholder.setMinimumSize(imageSize);

        JLabel titleLabel = new JLabel(playlist.getNom());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(COLOR_TEXT_LIGHT);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        int mediaCount = (playlist.getLesMedias() != null) ? playlist.getLesMedias().size() : 0;
        String texteElement = (mediaCount <= 1) ? " élément" : " éléments";
        JLabel countLabel = new JLabel(mediaCount + texteElement);
        countLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        countLabel.setForeground(COLOR_TEXT_DIM);
        countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(imagePlaceholder);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(countLabel);

        return card;
    }


    // Helper : Formate rapidement les titres de section
    private JLabel createSectionTitle(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setForeground(COLOR_TEXT_LIGHT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(0, 0, 10, 0));
        return label;
    }

    // Helper : Formate les blocs chiffres/texte du header
    private JPanel createStatItem(String number, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_BACKGROUND_DARK);

        JLabel numLabel = new JLabel(number, SwingConstants.CENTER);
        numLabel.setFont(new Font("Arial", Font.BOLD, 24));
        numLabel.setForeground(COLOR_TEXT_LIGHT);
        numLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel txtLabel = new JLabel(text, SwingConstants.CENTER);
        txtLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        txtLabel.setForeground(COLOR_TEXT_DIM);
        txtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(numLabel);
        panel.add(txtLabel);
        return panel;
    }

    // Helper : Formate les petits encarts d'abonnements
    private JLabel createBadge(String text) {
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
}