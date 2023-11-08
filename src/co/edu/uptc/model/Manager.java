package co.edu.uptc.model;

import co.edu.uptc.persistence.ReadAndWrite;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Manager implements Runnable {

    private Snake snake;
    private Food food;
    private ArrayList<Point> points;
    private Barrier barrier;
    private boolean run;
    private int score;
    private String name;
    private ReadAndWrite readAndWrite;
    private ArrayList<String> scores;
    private int increment;

    public Manager(boolean run, String name) throws FileNotFoundException, InterruptedException {
        this.score = 0;
        this.readAndWrite = new ReadAndWrite();
        config();
        Thread.sleep(1000);
        this.run = run;
        this.name = name;
        this.scores = readAndWrite.readFile("history");
        points.add(snake.getPoint());
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        int widthPoint = 10;
        int heightPoint = 10;
        food.setPlaying(run);
        barrier.setPlaying(run);
        Thread foodThread = new Thread(this.food);
        Thread barrierThread = new Thread(this.barrier);
        foodThread.start();
        barrierThread.start();
        while (run) {
            if (validateCollision()) {
                try {
                    scores.add(name + " -- " + score);
                    readAndWrite.writeFile("history", scores);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                run = false;
            }
            points.add(0, new Point(snake.getPoint().x, snake.getPoint().y));
            points.remove(points.size() - 1);
            Rectangle rectangle = new Rectangle(snake.getPoint().x, snake.getPoint().y, 15, 15);
            Rectangle rectangle2 = new Rectangle(this.food.getxCoord(), this.food.getyCoord(), 15, 15);
            Rectangle rectangle3 = new Rectangle(this.barrier.getxCoord(), this.barrier.getyCoord(), 20, 20);
            if (rectangle.intersects(rectangle2)) {
                setSnakeSize(increment);
                this.food.repaint();
                score++;
            }
            if (rectangle.intersects(rectangle3)) {
                try {
                    scores.add(name + " -- " + score);
                    readAndWrite.writeFile("history", scores);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                run = false;
            }
            if (snake.getDirection() == "RIGHT") {
                snake.getPoint().x = snake.getPoint().x + widthPoint;
                if (snake.getPoint().x > 800) {
                    snake.getPoint().x = 0;
                }
            } else if (snake.getDirection() == "LEFT") {
                snake.getPoint().x = snake.getPoint().x - widthPoint;
                if (snake.getPoint().x < 0) {
                    snake.getPoint().x = 800 - widthPoint;
                }
            } else if (snake.getDirection() == "UP") {
                snake.getPoint().y = snake.getPoint().y - heightPoint;
                if (snake.getPoint().y < 0) {
                    snake.getPoint().y = 700;
                }
            } else if (snake.getDirection() == "DOWN") {
                snake.getPoint().y = snake.getPoint().y + heightPoint;
                if (snake.getPoint().y > 700) {
                    snake.getPoint().y = 0;
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateCollision() {
        if (points.size() > increment + 1) {
            for (Point point : points) {
                if (snake.getPoint().x == point.x && snake.getPoint().y == point.y) {
                    return true;
                }
            }
        }
        return false;
    }

    private void config() throws FileNotFoundException {
        this.points = new ArrayList<>();
        ArrayList<String> foodConfigs = readAndWrite.readFile("foodconfig");
        ArrayList<String> barrierConfigs = readAndWrite.readFile("barrierConfig");
        ArrayList<String> snakeConfigs = readAndWrite.readFile("snakeConfig");
        this.snake = new Snake(100, 100, 3, 5, "RIGHT");
        int op = Integer.parseInt(readAndWrite.readFile("difficulty").get(0));
        switch (op) {
            case 1:
                increment = Integer.parseInt(snakeConfigs.get(0).split(",")[1]);
                this.food = new Food(Integer.parseInt(foodConfigs.get(0)), 100, 100);
                this.barrier = new Barrier(Integer.parseInt(barrierConfigs.get(0)), 200, 200);
                break;
            case 2:
                increment = Integer.parseInt(snakeConfigs.get(1).split(",")[1]);
                setSnakeSize(Integer.parseInt(snakeConfigs.get(1).split(",")[0]));
                this.food = new Food(Integer.parseInt(foodConfigs.get(1)), 100, 100);
                this.barrier = new Barrier(Integer.parseInt(barrierConfigs.get(1)), 200, 200);
                break;
            case 3:
                increment = Integer.parseInt(snakeConfigs.get(2).split(",")[1]);
                setSnakeSize(Integer.parseInt(snakeConfigs.get(2).split(",")[0]));
                this.food = new Food(Integer.parseInt(foodConfigs.get(2)), 100, 100);
                this.barrier = new Barrier(Integer.parseInt(barrierConfigs.get(2)), 200, 200);
                break;
        }
    }

    private void setSnakeSize(int iterator) {
        for (int i = 0; i < iterator; i++) {
            points.add(0, new Point(snake.getPoint().x, snake.getPoint().y));
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setDirection(String direction) {
        snake.setDirection(direction);
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Barrier getBarrier() {
        return barrier;
    }

    public void setBarrier(Barrier barrier) {
        this.barrier = barrier;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReadAndWrite getReadAndWrite() {
        return readAndWrite;
    }

    public void setReadAndWrite(ReadAndWrite readAndWrite) {
        this.readAndWrite = readAndWrite;
    }

    public ArrayList<String> getScores() {
        return scores;
    }

    public void setScores(ArrayList<String> scores) {
        this.scores = scores;
    }
}
