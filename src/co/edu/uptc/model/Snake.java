package co.edu.uptc.model;

import java.awt.*;

public class Snake {

    private Point point;
    private int size;
    private double speed;
    private String direction;

    public Snake(int xCoord, int yCoord, int size, double speed, String direction) {
        this.point = new Point(xCoord, yCoord);
        this.size = size;
        this.speed = speed;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
