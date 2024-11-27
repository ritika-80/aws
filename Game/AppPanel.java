package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class AppPanel extends JPanel {
    int x = 0;
    int y = 100;
    Timer timer;
    Car car1;
    Car car2;
    Car car3;
    Car car4;
    Car car5;
    Car car6;
    BufferedImage RoadImage;
    int passedCars;

    public static int MOVE_DISTANCE = 5;

    AppPanel() {
        setSize(500, 500);
        car1 = new Car(350, 350, 70, 50, "R.png", 5);
        car2 = new Car(300, 0, 70, 50, "R.png", 1);
        car3 = new Car(100, 0, 70, 50, "R.png", 3);
        car4 = new Car(600, 0, 70, 50, "R.png", 2);
        car5 = new Car(500, 0, 70, 50, "R.png", 4);
        car6 = new Car(400, 0, 70, 50, "R.png", 7);
        loadRoadImage();
        callPaintAgain();
        addKeyboardControls();
        setFocusable(true);
        passedCars = 0;
    }

    void loadRoadImage() {
        try {
            RoadImage = ImageIO.read(getClass().getResource("Road.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        pen.drawImage(RoadImage, 0, 0, getWidth(), getHeight(), null);
        car1.showCar(pen);
        car2.showCar(pen);
        car3.showCar(pen);
        car4.showCar(pen);
        car5.showCar(pen);
        car6.showCar(pen);
        pen.drawString("Passed Cars: " + passedCars, 10, 10);
    }

    void callPaintAgain() {
        timer = new Timer(20, (a) -> {
            // car1.moveCar();
            car2.moveCar();
            car3.moveCar();
            car4.moveCar();
            car5.moveCar();
            car6.moveCar();
            checkCollisions();
            countPassedCars();
            repaint();
        });
        timer.start();
    }

    void checkCollisions() {
        if (car1.collidesWith(car2) || car1.collidesWith(car3) || car1.collidesWith(car4) ||
                car1.collidesWith(car5) || car1.collidesWith(car6)) {
            exitGame();
        }
    }

    void exitGame() {
        JOptionPane.showMessageDialog(this, "Game Over!");
        System.exit(0);
    }

    void addKeyboardControls() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    car1.xAxis += MOVE_DISTANCE;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    car1.xAxis -= MOVE_DISTANCE;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    car1.yAxis -= MOVE_DISTANCE;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    car1.yAxis += MOVE_DISTANCE;
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    void countPassedCars() {

        if (car1.yAxis < car2.yAxis && car2.yAxis >= 0) {
            passedCars++;
            car2.yAxis = -1;
        }
        if (car1.yAxis < car3.yAxis && car3.yAxis >= 0) {
            passedCars++;
            car3.yAxis = -1;
        }
        if (car1.yAxis < car4.yAxis && car4.yAxis >= 0) {
            passedCars++;
            car4.yAxis = -1;
        }
        if (car1.yAxis < car5.yAxis && car5.yAxis >= 0) {
            passedCars++;
            car5.yAxis = -1;
        }
        if (car1.yAxis < car6.yAxis && car6.yAxis >= 0) {
            passedCars++;
            car6.yAxis = -1;
        }
    }
}
