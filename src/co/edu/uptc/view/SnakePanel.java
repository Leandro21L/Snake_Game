package co.edu.uptc.view;

import co.edu.uptc.model.Barrier;
import co.edu.uptc.model.Food;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SnakePanel extends JPanel {

    private ArrayList<Point> points;
    private Food food;
    private Barrier barrier;
    private int score;

    public SnakePanel(ArrayList<Point> points, Food food, Barrier barrier, int score) {
        super();
        this.points = points;
        this.food = food;
        this.barrier = barrier;
        this.score = score;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(("Score: " + score), 30, 30);
        g.setColor(Color.ORANGE);
        if (points.size() > 0) {
            for (Point point : points) {
                Point p = (Point) point;
                g.fillRect(p.x, p.y, 15, 15);
            }
        }
        g.setColor(Color.RED);
        g.fillOval(food.getxCoord(), food.getyCoord(), 15, 15);
        g.setColor(Color.BLACK);
        g.fillOval(barrier.getxCoord(), barrier.getyCoord(), 20, 20);
        g.setColor(Color.YELLOW);
        g.fillOval(barrier.getxCoord() + 2, barrier.getyCoord() + 2, 15, 15);
    }

    public void updateFood(Food food, int score) {
        this.food = food;
        this.score = score;
        revalidate();
        repaint();
    }

    public void updateSnake(ArrayList<Point> points) {
        this.points = points;
        revalidate();
        repaint();
    }

    public void updateBarrier(Barrier barrier) {
        this.barrier = barrier;
        revalidate();
        repaint();
    }
}
