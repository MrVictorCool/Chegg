package main;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        Image icon = null;
        JFrame window = new JFrame("Chegg dev0.0.1");
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
}
