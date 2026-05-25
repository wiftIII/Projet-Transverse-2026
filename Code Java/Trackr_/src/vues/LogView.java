package vues;


import controleurs.FactoryController;
import controleurs.LogController;
import controleurs.PlaylistController;
import main.ApplicationMedias;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.SystemColor.text;
import static utils.Utils.*;

public class LogView extends JPanel {


    private JButton btnNewProfile;
    private JButton btnSelma;


    ApplicationMedias factoryMedia;


    public LogView() {
        this.setLayout(new BorderLayout());
        this.setBackground(COLOR_BACKGROUND_DARK);

        JPanel logPanel = new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));
        logPanel.setBackground(COLOR_SIDEBAR_BACKGROUND);
        logPanel.setPreferredSize(new Dimension(230, 0));
        logPanel.setBorder(new EmptyBorder(25, 15, 25, 15));

        // Logo "TRACKR" (couleur verte)
        JLabel appNameLabel = new JLabel("TRACKR");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        appNameLabel.setForeground(COLOR_ACCENT_GREEN);
        appNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        logPanel.add(appNameLabel);
        logPanel.add(Box.createRigidArea(new Dimension(0, 40)));


        btnNewProfile = createSidebarButton("Nouveau Profile");
        btnSelma = createSidebarButton("Profile Selma");


        logPanel.add(btnNewProfile);
        logPanel.add(btnSelma);

        LogController.openViewFactoryWithSelma(btnSelma, this);
        LogController.openViewFactoryWithUserLogger(btnNewProfile, this);

        this.add(logPanel, BorderLayout.CENTER);

    }

    private JButton createSidebarButton (String text){
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

}