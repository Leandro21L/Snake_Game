package co.edu.uptc.model;

import java.util.Random;

public class Item implements Runnable {

    private int timeOfReappear;
    private int xCoord;
    private int yCoord;
    private boolean isPlaying;

    public Item(int timeOfReappear, int xCoord, int yCoord) {
        this.timeOfReappear = timeOfReappear;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.isPlaying = false;
    }

    public void repaint() {
        Random random = new Random();
        xCoord = random.nextInt(800);
        yCoord = random.nextInt(700);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (isPlaying) {
            try {
                xCoord = random.nextInt(800);
                yCoord = random.nextInt(700);
                Thread.sleep(500L * timeOfReappear);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getTimeOfReappear() {
        return timeOfReappear;
    }

    public void setTimeOfReappear(int timeOfReappear) {
        this.timeOfReappear = timeOfReappear;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
