package state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.ParticularObjectManager;


public class GameWorld {
    public final static int ATTACK=0;
    public final static int DEFEND=1;
    //public final static int FINAL=2;

    public int state=DEFEND;
    public BufferedImage bg,bufImage;
    public GamePanel gamePanel;
    public ParticularObjectManager particularObjectManager;
    public GameWorld(GamePanel gamePanel){
        try {
            bg= ImageIO.read(new File("src/resource/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufImage = new BufferedImage(gamePanel.SCREEN_WIDTH_MAX, gamePanel.SCREEN_HEIGHT_MAX, BufferedImage.TYPE_INT_ARGB);
    
        this.gamePanel= gamePanel;
        
    }
   public void Update(){

   }  
   public void Render(){
    
    if(bufImage==null){
        bufImage = new BufferedImage(gamePanel.SCREEN_WIDTH_MAX, gamePanel.SCREEN_HEIGHT_MAX, BufferedImage.TYPE_INT_ARGB);
    }
    if(bufImage!=null){
        Graphics2D g2=(Graphics2D)bufImage.getGraphics();
        g2= (Graphics2D)bufImage.getGraphics();
    if(g2!=null){
        g2.drawImage(bg,0,0 ,  gamePanel.SCREEN_WIDTH_MAX , gamePanel.SCREEN_HEIGHT_MAX, gamePanel);
   
    }
    
    }}
}
