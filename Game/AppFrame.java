package Game;

import javax.swing.*;

public class AppFrame extends JFrame {
    private AppPanel cp;

    public AppFrame() {
        cp = new AppPanel();
        add(cp);
        configureFrame();
    }

    private void configureFrame() {
        setSize(700, 500);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new AppFrame();
    }

}
