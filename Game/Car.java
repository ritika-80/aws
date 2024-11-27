package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Car {
    int xAxis;
    int yAxis;
    int height;
    int width;
    String filename;
    int carSpeed;
    BufferedImage carImage;
    Random rand = new Random();

    Car(int xAxis, int yAxis, int height, int width, String filename, int carSpeed) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.height = height;
        this.width = width;
        this.filename = filename;
        this.carSpeed = carSpeed;
        loadCarImage(); // Load the car image in the constructor
    }

    void loadCarImage() {
        try {
            carImage = ImageIO.read(getClass().getResource(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showCar(Graphics pen) {

        pen.drawImage(carImage, xAxis, yAxis, width, height, null);
    }

    void moveCar() {
        yAxis += carSpeed; // Move car downwards
        if (yAxis > 500) { // If the car moves out of the bottom bound
            yAxis = -height; // Reset yAxis to top (negative height)
            xAxis = rand.nextInt(500 - width); // Randomize xAxis within bounds

        }

    }

    boolean collidesWith(Car other) {
        Rectangle thisRect = new Rectangle(xAxis, yAxis, width, height);
        Rectangle otherRect = new Rectangle(other.xAxis, other.yAxis, other.width, other.height);
        return thisRect.intersects(otherRect);
    }
}
