package controleurs;

import main.FactoryMedia;
import modeles.user.User;
import vues.PlaylistsView;
import vues.UserView;

import javax.swing.*;
import java.awt.*;

public class UserController {

    public static void openViewPlaylist(JButton button, JPanel jPanel){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            PlaylistsView playlistPage = new PlaylistsView(FactoryMedia.getFactoryMedia().getUserLogged());

            center.add(playlistPage, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

    public static void openViewUser(User targetUser, JPanel mainContainer) {
        // On vide le conteneur principal
        mainContainer.removeAll();

        // On instancie la vue avec l'utilisateur sur lequel on vient de cliquer
        UserView profilPage = new UserView(targetUser);

        // On l'ajoute au centre
        mainContainer.add(profilPage, BorderLayout.CENTER);

        // On rafraîchit l'interface
        mainContainer.revalidate();
        mainContainer.repaint();
    }
}
