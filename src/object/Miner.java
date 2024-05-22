package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import effect.Animation;
import effect.Loader;
import state.GameWorld;

public class Miner extends Human {
    int gold=100;// sau 1 khoang tgian thi dc bn vang
    float goldPosX;
    float goldPosY;
    long curTime=0;
    public Animation move;
    public Animation attack,attackBack;
    public Animation curAnimation;
    public Miner( int team,float goldPosX,float goldPosY,GameWorld gameWorld) {
        super(0, goldPosY,72 , 72, 500, team, 0, 120, gameWorld);
        setSpeedX(1f);
        this.goldPosX=goldPosX;
        this.goldPosY=goldPosY;
        move = Loader.getInstanceLoader().getAnimation("MinerRun");
        attack=Loader.getInstanceLoader().getAnimation("MinerWork");
        attack.flipAll();
        attackBack=Loader.getInstanceLoader().getAnimation("MinerWork");
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
            attack.update(System.nanoTime());
            changeGold(gold, curTime);
        }
    }
    
    }
    public void changeGold(int gold,long curTime){
        if(this.curTime==0) this.curTime=System.currentTimeMillis();
        if(System.currentTimeMillis()-this.curTime>=8000) {getGameWorld().gold=getGameWorld().gold+gold;
            this.curTime=System.currentTimeMillis();
        }
    }
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth(),(int)getHeight());
        return rec;
    }

    @Override
    public void draw(Graphics2D g2) {
        curAnimation.draw(getPosX(), getPosY(), g2);
 }
}