package object;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import state.GameWorld;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import effect.Animation;
import effect.Loader;


public class Giant extends Human {
    public Loader load;

    public Giant(float x, float y,int team,GameWorld gameWorld) {
        super(x,y,72,100,3000,team,300,1500,gameWorld); 
        try {
            this.image= ImageIO.read(new File("src/resource/giant/move/Giantmove_7.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTeamType(team);
        move = Loader.getInstanceLoader().loadAllAnimation().get("GiantMove");
        pause= Loader.getInstanceLoader().loadAllAnimation().get("GiantPause");
        setSpeedX(1);
        setSpeedY(1);
    }

    
    public void draw(Graphics2D g2){
        if (getAction()== MOVING) move.draw(getPosX(),getPosY(),g2);
        else pause.draw(getPosX(),getPosY(),g2);
        
    }

    public void update(){

    }
    @Override
    public void run() {
        
    }


    @Override
    public void stopRun() {
        
    }


    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rec = new Rectangle((int)getWidth(),(int)getHeight());
        return rec;
    }

}
