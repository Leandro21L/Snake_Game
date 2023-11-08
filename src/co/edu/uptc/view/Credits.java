package co.edu.uptc.view;

import co.edu.uptc.presenter.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Credits extends JDialog {

    public Credits(ActionListener listener) {
        setSize(500, 1000);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 1, 1, 1));
        add(new JLabel("Desarrollado por:", SwingConstants.CENTER));
        add(new JLabel("Leandro Arturo Luis Sierra", SwingConstants.CENTER));
        add(new JLabel("Codigo:", SwingConstants.CENTER));
        add(new JLabel("201813802", SwingConstants.CENTER));
        add(new JLabel("Docente:", SwingConstants.CENTER));
        add(new JLabel("Edgar Meneses", SwingConstants.CENTER));
        add(new JLabel("Ingenieria de Sistemas y Computacion", SwingConstants.CENTER));
        add(new JLabel("2023", SwingConstants.CENTER));
        add(new JLabel("Programacion III", SwingConstants.CENTER));
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(new ImageIcon((Constants.UPTC_LOGO))));
        jPanel.setPreferredSize(new Dimension(473, 120));
        add(jPanel);
        add(jButton("Volver al Menu", Commands.BACK_MENU.name(), listener));
        setUndecorated(true);
    }

    private JButton jButton(String name, String command, ActionListener listener) {
        JButton jButton = new JButton(name);
        jButton.setActionCommand(command);
        jButton.addActionListener(listener);
        return jButton;
    }
}
