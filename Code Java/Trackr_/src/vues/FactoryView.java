package vues;


import controleurs.*;
import main.ApplicationMedias;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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


    ApplicationMedias factoryMedia;


    public FactoryView(ApplicationMedias factoryMedia, Boolean sideBar) {
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
        sidebarPanel.add(btnDebugFilm);


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

        searchField = new JTextField("Rechercher un film, une série, un réalisateur...");
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setForeground(new Color(150, 150, 150)); // Texte d'espace réservé (simulé)
        searchField.setBackground(COLOR_CARD_BACKGROUND);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                new EmptyBorder(10, 15, 10, 15)
        ));

        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (searchField.getText().equals("Rechercher un film, une série, un réalisateur...")) {
                    searchField.setText("");
                    searchField.setForeground(COLOR_TEXT_LIGHT);
                }
            }
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




    private JLabel createSectionHeader(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(COLOR_TEXT_DIM);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 5, 0));
        return label;
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


    public JButton getBtnDebugFilm() {
        return btnDebugFilm;
    }

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