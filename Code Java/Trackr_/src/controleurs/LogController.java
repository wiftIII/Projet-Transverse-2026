package controleurs;

import main.FactoryMedia;
import vues.FactoryView;

import javax.swing.*;
import java.awt.*;

public class LogController {

    public static void openViewFactoryWithUserLogger(JButton button, JPanel jPanel){
        button.addActionListener(e -> {

            JPanel center = jPanel;
            reloadLayout(center);
            center.removeAll();

            FactoryView factoryview = new FactoryView(FactoryMedia.getFactoryMedia(), true);

            center.add(factoryview, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

    public static void openViewFactoryWithSelma(JButton button, JPanel jPanel){

        button.addActionListener(e -> {
            JPanel center = jPanel;
            reloadLayout(center);
            center.removeAll();

            FactoryMedia.getFactoryMedia().setUserLoggedWithSelma();
            FactoryView factoryview = new FactoryView(FactoryMedia.getFactoryMedia(), true);

            center.add(factoryview, BorderLayout.CENTER);

            center.revalidate();
            center.repaint();
        });
    }

    public static void reloadLayout(JPanel jpanel){
        jpanel.setLayout(new BorderLayout());
    }

}
