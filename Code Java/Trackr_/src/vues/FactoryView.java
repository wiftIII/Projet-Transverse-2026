package vues;

import controleurs.*;
import main.FactoryMedia;
import modeles.media.Media;
import modeles.user.Playlist;
import utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Random;

import static utils.Utils.*;

public class FactoryView extends JPanel {

    private JButton btnAccueil;
    private JButton btnProfil;
    private JButton btnCoupsDeCoeur;
    private JButton btnFilmVu;
    private JButton btnMesListes;
    private JButton btnDebugFilm;

    private JButton btnSwitchProfile;
    private JButton btnParametres;

    private JTextField searchField;

    private JPanel centralContentPanel;
    private JScrollPane contentScrollPane;
    private JPanel contentContainer;

    FactoryMedia factoryMedia;

    public FactoryView(FactoryMedia factoryMedia, Boolean sideBar) {
        this.setLayout(new BorderLayout());
        this.setBackground(COLOR_BACKGROUND_DARK);

        this.factoryMedia = factoryMedia;

        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(COLOR_SIDEBAR_BACKGROUND);
        sidebarPanel.setPreferredSize(new Dimension(230, 0));
        sidebarPanel.setBorder(new EmptyBorder(25, 15, 25, 15));

        // Logo "TRACKR" (couleur verte)
        JLabel appNameLabel = new JLabel("TRACKR");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        appNameLabel.setForeground(COLOR_ACCENT_GREEN);
        appNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebarPanel.add(appNameLabel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        sidebarPanel.add(createSectionHeader("MENU"));

        btnAccueil = createSidebarButton("Accueil");
        btnProfil = createSidebarButton("Mon Profil");
        btnCoupsDeCoeur = createSidebarButton("Coups de cœur");
        btnFilmVu = createSidebarButton("Films Vu");
        btnMesListes = createSidebarButton("Mes Listes");
        btnDebugFilm = createSidebarButton("debugButton");

        sidebarPanel.add(btnAccueil);
        sidebarPanel.add(btnProfil);
        sidebarPanel.add(btnCoupsDeCoeur);
        sidebarPanel.add(btnFilmVu);
        sidebarPanel.add(btnMesListes);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidebarPanel.add(Box.createVerticalGlue());

        btnSwitchProfile = createSidebarButton("Switch Profile");
        sidebarPanel.add(btnSwitchProfile);

        btnParametres = createSidebarButton("Paramètres");
        sidebarPanel.add(btnParametres);

        centralContentPanel = new JPanel(new BorderLayout());
        centralContentPanel.setBackground(COLOR_BACKGROUND_DARK);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(COLOR_BACKGROUND_DARK);
        searchPanel.setBorder(new EmptyBorder(20, 30, 10, 30));

        // --- DANS LE CONSTRUCTEUR FactoryView ---
        // (Remplace la section existante de ton searchField par celle-ci)

                searchField = new JTextField("Rechercher un film, une série, un réalisateur...");
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setForeground(new Color(150, 150, 150));
        searchField.setBackground(COLOR_CARD_BACKGROUND);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                new EmptyBorder(10, 15, 10, 15)
        ));

// Nettoyage du texte par défaut au clic
        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (searchField.getText().equals("Rechercher un film, une série, un réalisateur...")) {
                    searchField.setText("");
                    searchField.setForeground(COLOR_TEXT_LIGHT);
                }
            }
        });

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrerEtAfficherMedias(); }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrerEtAfficherMedias(); }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrerEtAfficherMedias(); }
        });

        searchPanel.add(searchField, BorderLayout.CENTER);
        centralContentPanel.add(searchPanel, BorderLayout.NORTH);

        contentContainer = new JPanel();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setBackground(COLOR_BACKGROUND_DARK);
        contentContainer.setBorder(new EmptyBorder(20, 30, 30, 30));

        contentScrollPane = new JScrollPane(contentContainer);
        contentScrollPane.setBorder(null);
        contentScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentScrollPane.setBackground(COLOR_BACKGROUND_DARK);
        contentScrollPane.getViewport().setBackground(COLOR_BACKGROUND_DARK);

        centralContentPanel.add(contentScrollPane, BorderLayout.CENTER);

        // --- CORRECTION 1 : On ajoute les sections dans le contentContainer et pas centralContentPanel
        contentContainer.add(createMesCoupDeCoeurPanel("Mes listes de films"));
        contentContainer.add(createMesSuivis("Listes que tu suis"));
        contentContainer.add(createMediasConseille("Medias Conseillé"));

        FactoryController.openViewUser(getBtnProfil(), centralContentPanel);
        FactoryController.openViewFactory(getBtnAccueil(), centralContentPanel);
        FactoryController.openViewPlaylists(getBtnMesListes(), centralContentPanel);

        PlaylistsController.openPlaylistView(getBtnFilmVu(), centralContentPanel, factoryMedia.getUserLogged().getVu());
        PlaylistsController.openPlaylistView(getBtnCoupsDeCoeur(), centralContentPanel, factoryMedia.getUserLogged().getLike());

        MediaController.openMediaView(getBtnDebugFilm(), centralContentPanel, factoryMedia.getMediaEnVrac().getLast());

        FactoryController.openViewLog(getBtnSwitchProfile(), this);

        if(sideBar)
            this.add(sidebarPanel, BorderLayout.WEST);

        this.add(centralContentPanel, BorderLayout.CENTER);
    }

    private JPanel createMediasConseille(String titreSection) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(COLOR_BACKGROUND_DARK);
        sectionPanel.setBorder(new EmptyBorder(20, 30, 30, 30));

        // --- 1. L'en-tête (Titre + Bouton Rafraîchir) ---
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBackground(COLOR_BACKGROUND_DARK);
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titreLabel = new JLabel(titreSection);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titreLabel.setForeground(COLOR_TEXT_LIGHT);

        JButton refreshButton = new JButton("🔄 Rafraîchir");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 14));
        refreshButton.setForeground(COLOR_TEXT_LIGHT);
        refreshButton.setBackground(COLOR_CARD_BACKGROUND); // Ou une autre couleur de ton choix
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        headerPanel.add(titreLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Espace horizontal entre le titre et le bouton
        headerPanel.add(refreshButton);

        sectionPanel.add(headerPanel);
        sectionPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espace vertical avant la liste

        // --- 2. Le conteneur des médias ---
        JPanel listsdesplaylistPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        listsdesplaylistPanel.setBackground(COLOR_BACKGROUND_DARK);
        listsdesplaylistPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- 3. La logique de remplissage (Encapsulée pour être réutilisée) ---
        Runnable refreshAction = () -> {
            // On vide le panneau avant de remettre de nouveaux éléments
            listsdesplaylistPanel.removeAll();

            List<Media> tousLesMedias = FactoryMedia.getFactoryMedia().getMediaEnVrac();
            int totalMedias = tousLesMedias.size();

            if (totalMedias > 0) {
                Random random = new Random();
                for (int i = 0; i < 10; i++) {
                    int rint = random.nextInt(totalMedias);
                    Media mediaAleatoire = tousLesMedias.get(rint);
                    JPanel mediaCard = createMediaItem(mediaAleatoire);
                    listsdesplaylistPanel.add(mediaCard);
                }
            }

            // On force Swing à recalculer l'affichage et à redessiner l'écran
            listsdesplaylistPanel.revalidate();
            listsdesplaylistPanel.repaint();
        };

        // --- 4. Exécution ---
        // On exécute l'action une première fois pour le chargement initial
        refreshAction.run();

        // On lie cette même action au clic du bouton
        refreshButton.addActionListener(e -> refreshAction.run());

        sectionPanel.add(listsdesplaylistPanel);

        return sectionPanel;
    }


    private JPanel createMesSuivis(String titreSection) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(COLOR_BACKGROUND_DARK);
        sectionPanel.setBorder(new EmptyBorder(20, 30, 30, 30));

        JLabel titreLabel = new JLabel(titreSection);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titreLabel.setForeground(COLOR_TEXT_LIGHT);
        titreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sectionPanel.add(titreLabel);
        sectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel listsdesplaylistPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        listsdesplaylistPanel.setBackground(COLOR_BACKGROUND_DARK);
        listsdesplaylistPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        FactoryMedia.getFactoryMedia().getUserLogged().getSuivi().forEach(suivi -> {

            // 1. Création d'un panel conteneur (wrapper) pour empiler la carte et le badge
            JPanel wrapperPanel = new JPanel();
            wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
            wrapperPanel.setBackground(COLOR_BACKGROUND_DARK);

            // 2. Récupération de la carte existante
            JPanel playlistCard = createPlaylistItem(suivi.getMesPlaylists().get(new Random().nextInt(0, FactoryMedia.getFactoryMedia().getUserLogged().getMesPlaylists().size() - 1)));

            // 3. Ajout de l'encadré vert (bordure de 2 pixels d'épaisseur)
            // 3. Ajout de l'encadré vert avec un espacement intérieur
            Border bordureVerte = BorderFactory.createLineBorder(COLOR_ACCENT_GREEN_LIGHT, 2);
            Border espaceInterieur = BorderFactory.createEmptyBorder(10, 10, 10, 10); // Haut, Gauche, Bas, Droite (en pixels)

            playlistCard.setBorder(BorderFactory.createCompoundBorder(bordureVerte, espaceInterieur));
            playlistCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            playlistCard.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrage dans le wrapper

            // 4. Création du badge (remplace "@pseudo" par "@" + suivi.getPseudo() si besoin)
            Component badge = Utils.createBadge(suivi.getPseudo());
            // On s'assure de pouvoir modifier l'alignement selon le type de retour de Utils
            if (badge instanceof JComponent) {
                ((JComponent) badge).setAlignmentX(Component.CENTER_ALIGNMENT);
            }

            // 5. Assemblage dans le wrapper
            wrapperPanel.add(playlistCard);
            wrapperPanel.add(Box.createRigidArea(new Dimension(0, 8))); // Petit espace entre la carte et le badge
            wrapperPanel.add(badge);

            // 6. Ajout du wrapper au panel principal
            listsdesplaylistPanel.add(wrapperPanel);
        });

        sectionPanel.add(listsdesplaylistPanel);

        return sectionPanel;
    }

    private JPanel createMesCoupDeCoeurPanel(String titreSection) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(COLOR_BACKGROUND_DARK);
        sectionPanel.setBorder(new EmptyBorder(20, 30, 30, 30));

        JLabel titreLabel = new JLabel(titreSection);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titreLabel.setForeground(COLOR_TEXT_LIGHT);
        titreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sectionPanel.add(titreLabel);
        sectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel listsdesplaylistPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        listsdesplaylistPanel.setBackground(COLOR_BACKGROUND_DARK);
        listsdesplaylistPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        FactoryMedia.getFactoryMedia().getUserLogged().getMesPlaylists().forEach(me -> {
            JPanel playlistCard = createPlaylistItem(me);
            listsdesplaylistPanel.add(playlistCard);
        });

        sectionPanel.add(listsdesplaylistPanel);

        return sectionPanel;
    }

    // --- CORRECTION 3 : Suppression du ", JPanel panel" inutile dans la signature
    private JPanel createPlaylistItem(Playlist playlist) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COLOR_BACKGROUND_DARK);

        Dimension cardSize = new Dimension(200, 350);
        card.setPreferredSize(cardSize);
        card.setMaximumSize(cardSize);
        card.setMinimumSize(cardSize);

        JButton imageButton = new JButton();
        imageButton.setBorderPainted(false);
        imageButton.setFocusPainted(false);
        imageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        Dimension imageSize = new Dimension(200, 200);
        imageButton.setPreferredSize(imageSize);
        imageButton.setMaximumSize(imageSize);
        imageButton.setMinimumSize(imageSize);

        List<Media> medias = playlist.getLesMedias();
        int mediaCount = (medias != null) ? medias.size() : 0;
        boolean isImageSet = false;

        if (mediaCount >= 4) {
            BufferedImage composite = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = composite.createGraphics();

            for (int i = 0; i < 4; i++) {
                String chemin = "src/images/" + medias.get(i).getTitre() + ".jpg";
                File file = new File(chemin);

                int x = (i % 2) * 100;
                int y = (i / 2) * 100;

                if (file.exists()) {
                    Image img = new ImageIcon(chemin).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    new ImageIcon(img);
                    g2d.drawImage(img, x, y, null);
                } else {
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.fillRect(x, y, 100, 100);
                }
            }
            g2d.dispose();
            imageButton.setIcon(new ImageIcon(composite));
            imageButton.setContentAreaFilled(false);
            isImageSet = true;

        } else if (mediaCount > 0) {
            String chemin = "src/images/" + medias.get(0).getTitre() + ".jpg";
            File file = new File(chemin);
            if (file.exists()) {
                Image img = new ImageIcon(chemin).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                imageButton.setIcon(new ImageIcon(img));
                imageButton.setContentAreaFilled(false);
                isImageSet = true;
            }
        }

        if (!isImageSet) {
            imageButton.setText(";)");
            imageButton.setFont(new Font("Arial", Font.PLAIN, 50));
            imageButton.setBackground(COLOR_CARD_BACKGROUND);
            imageButton.setForeground(COLOR_TEXT_DIM);
            imageButton.setOpaque(true);
            imageButton.setContentAreaFilled(true);
        }

        PlaylistsController.openPlaylistView(imageButton, centralContentPanel, playlist);

        JLabel titleLabel = new JLabel(playlist.getNom());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(COLOR_TEXT_LIGHT);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String texteElement = (mediaCount <= 1) ? " élément" : " éléments";
        JLabel countLabel = new JLabel(mediaCount + texteElement);
        countLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        countLabel.setForeground(COLOR_TEXT_DIM);
        countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(imageButton);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 2)));
        card.add(countLabel);
        card.add(Box.createVerticalGlue());

        return card;
    }

    private JLabel createSectionHeader(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(COLOR_TEXT_DIM);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 5, 0));
        return label;
    }


    private JPanel createMediaItem(Media media) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COLOR_BACKGROUND_DARK);

        // On augmente légèrement la hauteur pour accommoder le nouveau bouton
        card.setPreferredSize(new Dimension(200, 500));

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

        // --- Assemblage de la carte ---
        card.add(imageButton);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(noteLabel);
        card.add(Box.createRigidArea(new Dimension(0, 8))); // Espace avant le bouton

        return card;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setForeground(COLOR_TEXT_LIGHT);
        button.setBackground(COLOR_SIDEBAR_BACKGROUND);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(5, 10, 5, 10));

        FactoryController.mouseDesigned(button);

        return button;
    }

    // --- NOUVELLES MÉTHODES À RAJOUTER EN BAS DE LA CLASSE ---

    /**
     * Filtre dynamiquement le panneau central avec les résultats de la recherche.
     */
    private void filtrerEtAfficherMedias() {
        String saisie = searchField.getText().trim().toLowerCase();

        // Si le champ est vide ou a le texte par défaut, on réaffiche tout ou le comportement de base
        if (saisie.isEmpty() || saisie.equals("rechercher un film, une série, un réalisateur...")) {
            reconstruireAffichageDeBase();
            return;
        }

        // 1. Récupérer et TRIER la liste par titre (Obligatoire pour la dichotomie)
        List<Media> listeTriee = new java.util.ArrayList<>(factoryMedia.getMediaEnVrac());
        listeTriee.sort((m1, m2) -> m1.getTitre().compareToIgnoreCase(m2.getTitre()));

        // 2. Vider le conteneur visuel actuel
        contentContainer.removeAll();

        // 3. Créer un panel pour afficher les résultats sous forme de grille/liste
        JPanel resultatsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        resultatsPanel.setBackground(COLOR_BACKGROUND_DARK);
        resultatsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Titre des résultats
        JLabel titreResultats = new JLabel("Résultats de la recherche");
        titreResultats.setFont(new Font("Arial", Font.BOLD, 20));
        titreResultats.setForeground(COLOR_TEXT_LIGHT);
        titreResultats.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentContainer.add(titreResultats);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        // 4. Exécuter la recherche dichotomique pour trouver le premier index correspondant
        int indexInitial = rechercheDichotomiquePrefixe(listeTriee, saisie);

        if (indexInitial != -1) {
            // Comme la liste est triée, les autres résultats qui commencent par la même lettre
            // se trouvent juste à côté (les index suivants). On les lit séquentiellement.
            for (int i = indexInitial; i < listeTriee.size(); i++) {
                Media m = listeTriee.get(i);
                if (m.getTitre().toLowerCase().startsWith(saisie)) {
                    resultatsPanel.add(createMediaItem(m));
                } else {
                    break; // Dès que ça ne commence plus par la saisie, on stoppe (gain de performance énorme)
                }
            }
        }

        contentContainer.add(resultatsPanel);

        // 5. Rafraîchir l'interface graphique de Swing
        contentContainer.revalidate();
        contentContainer.repaint();
    }

    /**
     * Algorithme de recherche dichotomique adapté pour trouver le PREMIER élément
     * dont le titre commence par le préfixe recherché.
     */
    private int rechercheDichotomiquePrefixe(List<Media> liste, String prefixe) {
        int debut = 0;
        int fin = liste.size() - 1;
        int resultatIndex = -1;

        while (debut <= fin) {
            int milieu = debut + (fin - debut) / 2;
            String titreMilieu = liste.get(milieu).getTitre().toLowerCase();

            if (titreMilieu.startsWith(prefixe)) {
                resultatIndex = milieu; // On a trouvé une correspondance !
                fin = milieu - 1;       // Mais on continue à chercher à gauche pour avoir le TOUT PREMIER dans l'ordre alphabétique
            } else if (titreMilieu.compareTo(prefixe) < 0) {
                debut = milieu + 1;
            } else {
                fin = milieu - 1;
            }
        }
        return resultatIndex;
    }

    private void reconstruireAffichageDeBase() {
        contentContainer.removeAll();
        contentContainer.add(createMesCoupDeCoeurPanel("Mes listes de films"));
        contentContainer.add(createMesSuivis("Listes que tu suis"));
        contentContainer.add(createMediasConseille("Medias Conseillé"));
        contentContainer.revalidate();
        contentContainer.repaint();
    }

    public JButton getBtnDebugFilm() { return btnDebugFilm; }
    public JButton getBtnAccueil() { return btnAccueil; }
    public JButton getBtnProfil() { return btnProfil; }
    public JButton getBtnCoupsDeCoeur() { return btnCoupsDeCoeur; }
    public JButton getBtnFilmVu() {return btnFilmVu;}
    public JButton getBtnMesListes() {return btnMesListes;}
    public JButton getBtnSwitchProfile() {return btnSwitchProfile;}
    public JButton getBtnParametres() { return btnParametres; }
    public JTextField getSearchField() { return searchField; }
    public JPanel getCentralContentPanel() { return centralContentPanel; }
}
