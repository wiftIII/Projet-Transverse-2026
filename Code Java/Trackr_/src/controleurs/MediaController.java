package controleurs;

import modeles.media.Media;
import modeles.user.Playlist;
import vues.MediaView;
import vues.PlaylistView;

import javax.swing.*;
import java.awt.*;

public class MediaController {

    public static void openMediaView(JButton button, JPanel jPanel, Media media){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            MediaView mediaViewedView = new MediaView(media);

            center.add(mediaViewedView, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

}
