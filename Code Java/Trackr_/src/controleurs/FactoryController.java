package controleurs;

import main.ApplicationMedias;
import modeles.user.Playlist;
import vues.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static utils.Utils.COLOR_ACCENT_GREEN;
import static utils.Utils.COLOR_TEXT_LIGHT;

public class FactoryController {

    public static void mouseDesigned(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(COLOR_ACCENT_GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(COLOR_TEXT_LIGHT);
            }
        });
    }


    public static void openViewUser(JButton button, JPanel jPanel){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            UserView profilPage = new UserView(ApplicationMedias.getFactoryMedia().getUserLogged());

            center.add(profilPage, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

    public static void openViewFactory(JButton button, JPanel jPanel){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            FactoryView factoryview = new FactoryView(ApplicationMedias.getFactoryMedia(), false);

            center.add(factoryview, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

    public static void openViewPlaylists(JButton button, JPanel jPanel){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            PlaylistsView playlistPage = new PlaylistsView(ApplicationMedias.getFactoryMedia().getUserLogged());

            center.add(playlistPage, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

    public static void openViewLog(JButton button, JPanel jPanel){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            LogView logview = new LogView();

            center.add(logview, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }
}
