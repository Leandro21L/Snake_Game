package co.edu.uptc.view;

import co.edu.uptc.presenter.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class History extends JDialog {


    public History(ActionListener listener, ArrayList<String> scores) {
        setSize(400, 400);
        setLocationRelativeTo(null);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(scores.size() + 1, 1, 15, 15));
        for (String s : scores) {
            jPanel.add(new JLabel(s));
        }
        jPanel.add(jButton("Volver al Menu", Commands.BACK_MENU2.name(), listener));
        add(new JScrollPane(jPanel));
        setUndecorated(true);
    }

    private JButton jButton(String name, String command, ActionListener listener) {
        JButton jButton = new JButton(name);
        jButton.setActionCommand(command);
        jButton.addActionListener(listener);
        return jButton;
    }
}
