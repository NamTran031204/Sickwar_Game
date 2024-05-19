package state;

import java.awt.Color;
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
import object.Statue;


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
    public Statue statue; // ptd
    Playing playing;
    Giant giant1;
// Thong so cua game
    public int gold=500;
    public int numHuman=2;// ban dau co 2 ng dao vang
    public long startTime = 0;
    
    private final int[] lineYPositions = new int[4]; // ptd
    public GameWorld(Playing playing){
        try {
            bg= ImageIO.read(new File("src/resource/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufImage = new BufferedImage(SCREEN_WIDTH_MAX, SCREEN_HEIGHT_MAX, BufferedImage.TYPE_INT_ARGB);
        particularObjectManager = new ParticularObjectManager(this);
        this.playing=playing;
        giant1=new Giant(0, 500,1, this);
        statue = new Statue(20,500,90,160,200); // ptd
        
        // Xác định vị trí các đường line - ptd
        for (int i = 0; i < 4; i++) {
            lineYPositions[i] = 475 + i * 75; 
        }
    }
    
   public void Update(){
    initEnemy(System.currentTimeMillis());
    particularObjectManager.UpdateObjects();
    particularObjectManager.checkCollisionWithStatue(statue); // ptd
    // ptd
    if (statue.getHealth() <= 0) {
        state = GAMEOVER; 
    }
    
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
        // ptd
        g2.setColor(Color.gray);
        for (int y : lineYPositions) {
            g2.drawLine(0, y, SCREEN_WIDTH_MAX, y);
        }  
        statue.draw(g2); // ptd
        particularObjectManager.draw(g2);
        
    }
    }
    }
    public void initEnemy(long curTime){
        if(startTime==0) startTime = curTime;
        while(curTime-startTime>=3000){ // ptd sửa
        	for(int i = 0; i <4 ; i++) { // ptd
        	       	Giant entity = new Giant(200, lineYPositions[i], 1, this); // ptd
        	        particularObjectManager.addObject(entity); // ptd
        	        }
            Giant giant = new Giant(1000, 500, 2, this);
            particularObjectManager.addObject(giant); // ptd
            startTime = curTime; // ptd
        
        }

    }
}
