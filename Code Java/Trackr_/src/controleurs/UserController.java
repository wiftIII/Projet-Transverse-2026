package controleurs;

import main.ApplicationMedias;
import vues.PlaylistsView;

import javax.swing.*;
import java.awt.*;

public class UserController {

    public static void openViewPlaylist(JButton button, JPanel jPanel){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            PlaylistsView playlistPage = new PlaylistsView(ApplicationMedias.getFactoryMedia().getUserLogged());

            center.add(playlistPage, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }
}
