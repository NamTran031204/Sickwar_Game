package main;
import java.io.IOException;
import javax.swing.JFrame;
import effect.Loader;
public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {

        JFrame stage = new JFrame();
        stage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stage.setTitle("Stick war");
        stage.setVisible(true);
        stage.setResizable(true);
        Loader.getInstanceLoader().loadData(); // loadData
        GamePanel gamePanel = new GamePanel();
        stage.setLocation((int)gamePanel.SCREEN_WIDTH_MAX/2-500/2,(int)gamePanel.SCREEN_HEIGHT_MAX/2-500/2);
        stage.add(gamePanel);
        gamePanel.startGame();
        stage.pack();

    }
}
