package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import effect.Animation;
import effect.Loader;
import state.GameWorld;

public class Miner extends Human {
    int gold=5;// sau 1 khoang tgian thi dc bn vang
    float goldPosX;
    float goldPosY;
    long curTime=0;
    public Animation move;
    public Animation attack,attackBack;
    public Animation curAnimation;
    public int count;
    public Miner( int team,float goldPosX,float goldPosY,GameWorld gameWorld) {
        super(0, goldPosY,72 , 72, 500, team, 0, 120, gameWorld);
        setSpeedX(1f);
        this.goldPosX=goldPosX;
        this.goldPosY=goldPosY;
        move = Loader.getInstanceLoader().getAnimation("MinerRun");
        attack=Loader.getInstanceLoader().getAnimation("MinerWork");
        curAnimation=move;
        
    }
    public void Update(){
        
        super.superUpdate();//ParticularObject update
        if(getState()==ALIVE){
        if(getPosX()+getSpeedX()*getDirection()<goldPosX-20){
            move.update(System.nanoTime());
            setPosX(getPosX()+getSpeedX()*getDirection());

        }
        else{
            setPosX(goldPosX-20);
            curAnimation=attack;
            count++;
            attack.update(System.nanoTime());
            if(count == 220) {
            	gameWorld.playSoundEffect(4);
            }
            if(this.curTime==0) this.curTime=System.currentTimeMillis();
            if(System.currentTimeMillis()-this.curTime>=2220) {
            	gameWorld.playSoundEffect(4);
            	this.curTime=System.currentTimeMillis();
            }
            changeGold(gold, curTime);
        }
    }
    
    }
    public void changeGold(int gold,long curTime){
        if(this.curTime==0) this.curTime=System.currentTimeMillis();
        if(System.currentTimeMillis()-this.curTime>=2000) {
        	getGameWorld().gold=getGameWorld().gold+gold;
            this.curTime=System.currentTimeMillis();
            
            
        }
    }
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth(),(int)getHeight());
        return rec;
    }

    @Override
    public Rectangle getObjectBound() {
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth(),(int)getHeight());
        return rec;
    }
    @Override
    public void draw(Graphics2D g2) {
        curAnimation.draw(getPosX(), getPosY(), g2);
 }
}