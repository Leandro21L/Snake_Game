package co.edu.uptc.presenter;

import co.edu.uptc.model.Manager;
import co.edu.uptc.persistence.ReadAndWrite;
import co.edu.uptc.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Presenter implements KeyListener, ActionListener {

    private Manager manager;
    private MainFrame mainFrame;
    private String direction;
    private Menu menu;
    private Credits credits;
    private History history;
    private ReadAndWrite readAndWrite;
    private EndGame endGame;
    private Timer timer;

    public Presenter() {
        this.readAndWrite = new ReadAndWrite();
        this.menu = new Menu(this);
        menu.setVisible(true);
    }

    private void setDirection() {
        manager.setDirection(direction);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 68) {
            if (direction != "LEFT") {
                direction = "RIGHT";
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 65) {
            if (direction != "RIGHT") {
                direction = "LEFT";
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == 87) {
            if (direction != "DOWN") {
                direction = "UP";
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == 83) {
            if (direction != "UP") {
                direction = "DOWN";
            }
        }
        setDirection();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Commands.valueOf(e.getActionCommand())) {
            case PLAY:
                try {
                    this.manager = new Manager(true, menu.getName());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                this.mainFrame = new MainFrame(manager.getPoints(), this, manager.getFood(), manager.getBarrier(), manager.getScore());
                timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainFrame.updateSnake(manager.getPoints());
                        mainFrame.updateFood(manager.getFood(), manager.getScore());
                        mainFrame.updateBarrier(manager.getBarrier());
                        if (!manager.isRun()) {
                            timer.stop();
                            mainFrame.setVisible(false);
                            endGame = new EndGame(this);
                            endGame.setVisible(true);

                        }
                    }
                });
                timer.start();
                menu.dispose();
                break;
            case HISTORY:
                menu.setVisible(false);
                try {
                    history = new History(this, readAndWrite.readFile("history"));
                    history.setVisible(true);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case CREDITS:
                menu.setVisible(false);
                credits = new Credits(this);
                credits.setVisible(true);
                break;
            case EXIT:
                System.exit(0);
                break;
            case BACK_MENU:
                credits.dispose();
                menu.setVisible(true);
                break;
            case BACK_MENU2:
                history.dispose();
                menu.setVisible(true);
                break;
        }
    }
}
