package co.edu.uptc.view;

import co.edu.uptc.presenter.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JDialog {

    private JTextArea textArea;
    private JButton playButton, historyButton, creditsButton, exitButton;

    public Menu(ActionListener listener) {
        setSize(400, 400);
        textArea = new JTextArea();
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Menu principal", SwingConstants.CENTER));
        this.add(jPanel, BorderLayout.NORTH);
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(5, 1, 15, 15));
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(new JLabel("Nombre: ", SwingConstants.LEFT), BorderLayout.WEST);
        jPanel1.add(textArea, BorderLayout.CENTER);
        namePanel.add(jPanel1);
        namePanel.add(jButton("Jugar", Commands.PLAY.name(), listener));
        namePanel.add(jButton("Historial de Partidas", Commands.HISTORY.name(), listener));
        namePanel.add(jButton("Creditos", Commands.CREDITS.name(), listener));
        namePanel.add(jButton("Salir", Commands.EXIT.name(), listener));
        this.add(namePanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    private JButton jButton(String name, String command, ActionListener listener) {
        JButton jButton = new JButton(name);
        jButton.setActionCommand(command);
        jButton.addActionListener(listener);
        return jButton;
    }

    public String getName() {
        return textArea.getText();
    }
}
