package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        JFrame window = new JFrame("Chegg dev0.0.1");
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
