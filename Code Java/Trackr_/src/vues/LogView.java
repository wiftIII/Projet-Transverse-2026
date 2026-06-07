package vues;

import controleurs.FactoryController;
import controleurs.LogController;
import main.FactoryMedia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static controleurs.LogController.reloadLayout;
import static utils.Utils.*;

public class LogView extends JPanel {

    private JButton btnNewProfile;
    private JButton btnSelma;
    private JButton btnCreateProfile;

    FactoryMedia factoryMedia;

    public LogView() {
        // 1. GridBagLayout permet de centrer parfaitement le panneau interne au milieu de la fenêtre
        this.setLayout(new GridBagLayout());
        this.setBackground(COLOR_BACKGROUND_DARK);

        // 2. Création de la "carte" centrale (l'encadré)
        JPanel loginCard = new JPanel();
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
        loginCard.setBackground(COLOR_SIDEBAR_BACKGROUND);

        // Création de l'encadré visuel (bordure verte + marges internes généreuses)
        loginCard.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_ACCENT_GREEN, 2, true), // Bordure extérieure
                new EmptyBorder(50, 60, 50, 60) // Espace à l'intérieur de la carte
        ));

        // 3. Logo "TRACKR"
        JLabel appNameLabel = new JLabel("TRACKR", SwingConstants.CENTER);
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 32));
        appNameLabel.setForeground(COLOR_ACCENT_GREEN);
        appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrage horizontal dans la carte

        loginCard.add(appNameLabel);
        loginCard.add(Box.createRigidArea(new Dimension(0, 40)));

        // 4. Création et ajout des boutons
        btnNewProfile = createProfileButton("Nouveau Profil");
        btnSelma = createProfileButton("Profil Selma");

        loginCard.add(btnNewProfile);
        loginCard.add(Box.createRigidArea(new Dimension(0, 15))); // Petit espace entre les deux boutons
        loginCard.add(btnSelma);

        // Appel des contrôleurs

        btnNewProfile.addActionListener(e -> {

            JPanel center = loginCard;
            reloadLayout(center);
            center.removeAll();

            newProfilePanel();

            center.revalidate();
            center.repaint();
        });

        LogController.openViewFactoryWithSelma(btnSelma, this);

        // 5. Ajout de la carte au centre de la vue
        this.add(loginCard);
    }

    private JButton createProfileButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setForeground(COLOR_TEXT_LIGHT);
        button.setBackground(COLOR_BACKGROUND_DARK); // Un poil plus foncé que la carte pour créer du contraste

        // Encadré fin autour du bouton
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(80, 85, 95), 1, true), // Ligne grise
                new EmptyBorder(10, 20, 10, 20) // Marges internes
        ));

        button.setFocusPainted(false);
        button.setOpaque(true);

        // Fixer une dimension identique pour tous les boutons pour un rendu esthétique
        Dimension buttonSize = new Dimension(220, 45);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        // Centrage du bouton et du texte
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        FactoryController.mouseDesigned(button);

        return button;
    }

    private JPanel newProfilePanel(){
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(COLOR_SIDEBAR_BACKGROUND);

        profilePanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_ACCENT_GREEN, 2, true), // Bordure extérieure
                new EmptyBorder(50, 60, 50, 60) // Espace à l'intérieur de la carte
        ));

        JLabel appNameLabel = new JLabel("TRACKR", SwingConstants.CENTER);
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 32));
        appNameLabel.setForeground(COLOR_ACCENT_GREEN);
        appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profilePanel.add(appNameLabel);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JTextField searchFieldNom;
        searchFieldNom = new JTextField("Nom");
        searchFieldNom.setFont(new Font("Arial", Font.PLAIN, 14));
        searchFieldNom.setForeground(new Color(150, 150, 150));
        searchFieldNom.setBackground(COLOR_CARD_BACKGROUND);
        searchFieldNom.setBorder(BorderFactory.createCompoundBorder(
                       BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                       new EmptyBorder(10, 15, 10, 15)
        ));

        searchFieldNom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (searchFieldNom.getText().equals("Nom")) {
                    searchFieldNom.setText("");
                    searchFieldNom.setForeground(COLOR_TEXT_LIGHT);
                }
            }
        });

        JTextField searchFieldMail;
        searchFieldMail = new JTextField("Mail");
        searchFieldMail.setFont(new Font("Arial", Font.PLAIN, 14));
        searchFieldMail.setForeground(new Color(150, 150, 150));
        searchFieldMail.setBackground(COLOR_CARD_BACKGROUND);
        searchFieldMail.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                        new EmptyBorder(10, 15, 10, 15)
                ));

        searchFieldMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (searchFieldMail.getText().equals("Mail")) {
                    searchFieldMail.setText("");
                    searchFieldMail.setForeground(COLOR_TEXT_LIGHT);
                }
            }
        });

        btnCreateProfile = createProfileButton("Créer le Profile");

        profilePanel.add(searchFieldNom);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        profilePanel.add(searchFieldMail);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        profilePanel.add(btnCreateProfile);

        LogController.openViewFactoryWithUserLogger(btnCreateProfile, this);
        
        this.add(profilePanel);

        return profilePanel;
    }
}