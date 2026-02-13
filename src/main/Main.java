package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {

    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    static JFrame window = new JFrame("Chegg dev1.0.0");
    public static void main(String[] args) {
        Image icon = null;
        try {
            icon = ImageIO.read(Main.class.getResourceAsStream("/misc/icon256.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.setIconImage(icon);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();

        gp.setup();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.startGameThread();

    }

    static void fullscreen() {
        if (device.getFullScreenWindow() == null) {
            device.setFullScreenWindow(window);
        } else {
            device.setFullScreenWindow(null);
        }
    }

    static void fullscreen(boolean x) {
        device.setFullScreenWindow(x ? window : null);
    }
}
