package controleurs;

import main.ApplicationMedias;
import modeles.user.Playlist;
import vues.FactoryView;
import vues.PlaylistView;
import vues.PlaylistsView;

import javax.swing.*;
import java.awt.*;

public class LogController {

    public static void openViewFactoryWithUserLogger(JButton button, JPanel jPanel){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            FactoryView factoryview = new FactoryView(ApplicationMedias.getFactoryMedia(), true);

            center.add(factoryview, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

    public static void openViewFactoryWithSelma(JButton button, JPanel jPanel){
        button.addActionListener(e -> {
            JPanel center = jPanel;
            center.removeAll();

            ApplicationMedias.getFactoryMedia().setUserLoggedWithSelma();
            FactoryView factoryview = new FactoryView(ApplicationMedias.getFactoryMedia(), true);

            center.add(factoryview, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

}
