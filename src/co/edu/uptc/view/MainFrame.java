package co.edu.uptc.view;

import co.edu.uptc.model.Barrier;
import co.edu.uptc.model.Food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private SnakePanel snakePanel;

    public MainFrame(ArrayList<Point> snake, KeyListener listener, Food food, Barrier barrier, int score) throws HeadlessException {
        super(Constants.TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage(Constants.ICON_PATH);
        setIconImage(icon);

        addKeyListener(listener);

        initComponents(snake, food, barrier, score);

        setVisible(true);
    }

    public void updateSnake(ArrayList<Point> snake) {
        snakePanel.updateSnake(snake);
        revalidate();
        repaint();
    }

    public void updateFood(Food food, int score) {
        snakePanel.updateFood(food, score);
        revalidate();
        repaint();
    }

    public void updateBarrier(Barrier barrier) {
        snakePanel.updateBarrier(barrier);
        revalidate();
        repaint();
    }

    private void initComponents(ArrayList<Point> snake, Food food, Barrier barrier, int score) {
        snakePanel = new SnakePanel(snake, food, barrier, score);
        this.getContentPane().add(snakePanel);
        revalidate();
        repaint();
    }


}
