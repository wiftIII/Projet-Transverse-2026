
package controleurs;

import modeles.media.Media;
import modeles.user.Playlist;
import vues.MediaView;
import vues.PlaylistView;

import javax.swing.*;
import java.awt.*;

public class PlaylistController {

    public static void openMediaView(JButton button, JPanel jPanel, Media media){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            MediaView mediaView = new MediaView(media);

            center.add(mediaView, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

    public static void retirerMediaDePlaylist(Media media, Playlist playlistActuelle, JPanel vueParent) {
        if (playlistActuelle != null && media != null) {
            // 1. Suppression dans le modèle (données)
            playlistActuelle.retirerMedia(media);
            System.out.println("==> Controller: Média '" + media.getTitre() + "' retiré de la playlist.");

            if (vueParent != null) {
                vueParent.revalidate();
                vueParent.repaint();
            }
        }
    }
}
