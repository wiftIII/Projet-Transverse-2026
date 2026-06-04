package controleurs;

import modeles.user.Playlist;
import vues.PlaylistView;

import javax.swing.*;
import java.awt.*;

public class PlaylistsController {

    public static void openPlaylistView(JButton button, JPanel jPanel, Playlist playlist){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            PlaylistView playlistView = new PlaylistView(playlist);

            center.add(playlistView, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

}
