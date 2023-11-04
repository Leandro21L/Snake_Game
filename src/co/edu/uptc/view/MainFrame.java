package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super(Constants.TITLE);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
        setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage(Constants.ICON_PATH);
        setIconImage(icon);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

    }
}
