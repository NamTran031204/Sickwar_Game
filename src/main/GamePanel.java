package main;


import javax.swing.JPanel;
import state.GameWorld;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;


public class GamePanel extends JPanel implements Runnable {
     Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    final public int SCREEN_WIDTH_MAX=screenSize.width;
    final public int SCREEN_HEIGHT_MAX=screenSize.height;
    Thread thread;
    public Graphics2D g2;
    public boolean isRunning=true;
    GameWorld gameWorld;


    public GamePanel()  {
        this.setPreferredSize(new Dimension(500,500));
        this.setDoubleBuffered(true);
        gameWorld=new GameWorld(this);
    }
    public void startGame(){
        thread= new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        long t= System.nanoTime();
        long sleepTime;
        while(isRunning){
            
            update();
            gameWorld.Render();
            repaint();
            if( (sleepTime=1000000000/60-System.nanoTime()+t)>0){
                
                try {
                    Thread.sleep(sleepTime/1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            t=System.nanoTime();
        }
    }
    public void update(){
        gameWorld.Update();
    }
    @Override
    public void paint(Graphics g) {
       g.drawImage(gameWorld.bufImage, 0, 0 , this);
       
    }

}
