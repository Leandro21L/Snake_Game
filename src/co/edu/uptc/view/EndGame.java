package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EndGame extends JDialog {

    public EndGame(ActionListener listener) {
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel jLabel = new JLabel(new ImageIcon(Constants.GAME_OVER));
        add(jLabel, BorderLayout.CENTER);
        setUndecorated(false);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere salir?");
                if (i == 0)
                    System.exit(0);
            }
        });
    }
}
