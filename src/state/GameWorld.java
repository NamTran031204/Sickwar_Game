package state;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Scene.Playing;
import Scene.gameScene;
import main.GamePanel;
import object.Giant;
import object.ParticularObject;
import object.ParticularObjectManager;


public class GameWorld {
    Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    final public int SCREEN_WIDTH_MAX=screenSize.width;
    final public int SCREEN_HEIGHT_MAX=screenSize.height;
    public final static int ATTACK=0;
    public final static int DEFEND=1;
    public final static int WIN=2;
    public final static int GAMEOVER=3;
    

    public int state=ATTACK;
    public BufferedImage bg,bufImage;
    public ParticularObjectManager particularObjectManager;
    Playing playing;
    Giant giant1;
// Thong so cua game
    public int gold=500;
    public int numHuman=2;// ban dau co 2 ng dao vang
    public long startTime = 0;
    
    public GameWorld(Playing playing){
        try {
            bg= ImageIO.read(new File("Sickwar_Game/src/resource/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufImage = new BufferedImage(SCREEN_WIDTH_MAX, SCREEN_HEIGHT_MAX, BufferedImage.TYPE_INT_ARGB);
        particularObjectManager = new ParticularObjectManager(this);
        this.playing=playing;
        giant1=new Giant(0, 500,1, this);
    }
    
   public void Update(){
    initEnemy(System.currentTimeMillis());
    particularObjectManager.UpdateObjects();
   
    
   }  
   public void Render(){
    
    if(bufImage==null){
        bufImage = new BufferedImage(SCREEN_WIDTH_MAX, SCREEN_HEIGHT_MAX, BufferedImage.TYPE_INT_ARGB);
    }
    if(bufImage!=null){
        Graphics2D g2=(Graphics2D)bufImage.getGraphics();
        g2= (Graphics2D)bufImage.getGraphics();
    if(g2!=null){
        g2.drawImage(bg,0,0 ,  SCREEN_WIDTH_MAX , SCREEN_HEIGHT_MAX, null);
        particularObjectManager.draw(g2);
        
    }
    }
    }
    public void initEnemy(long curTime){
        if(startTime==0) startTime = curTime;
        if(curTime-startTime>=1000&&curTime-startTime<=1100){
            Giant giant = new Giant(1000, 500, 2, this);
        }

    }
}
