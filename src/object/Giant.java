package object;

import state.GameWorld;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import effect.Animation;
import effect.Loader;


public class Giant extends Human {
    public Animation move;
    public Animation moveBack;
    public Animation attack;
    public Animation pause;
    public Animation attackBack;
    public Animation curAnimation;
    // can animation die ke thua
    public Giant(float x, float y,int team,GameWorld gameWorld) {
        super(x,y,150,400,3000,team,300,1500,gameWorld); 
        move = Loader.getInstanceLoader().getAnimation("GiantMove");
        moveBack = Loader.getInstanceLoader().getAnimation("GiantMove");
        moveBack.flipAll();
        pause= Loader.getInstanceLoader().getAnimation("GiantPause");
        die=Loader.getInstanceLoader().getAnimation("GiantPause");
        attackBack=Loader.getInstanceLoader().getAnimation("GiantAttack");
        attack=Loader.getInstanceLoader().getAnimation("GiantAttack");
        attack.flipAll();
        setSpeedX(0.000015f);
        setSpeedY(0);
        if(getTeamType()==TEAM1) curAnimation=move;
        else curAnimation=moveBack;
    }

    
    public void draw(Graphics2D g2){
        
        curAnimation.draw(getPosX(), getPosY(), g2);
    }

    public void update(){
        super.Update();
        if(getAction()==ATTACKING){
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==RIGHT_DIR){
             curAnimation=attack;
             attack.update(System.nanoTime());
           
            }
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==LEFT_DIR){
              curAnimation=attackBack;
              attackBack.update(System.nanoTime());
          
            }
            move.reset();
            moveBack.reset();

        }
        if(getAction()==MOVING){
            if(getDirection()==RIGHT_DIR){
                 curAnimation=move;
                 move.update(System.nanoTime());
                
            }
            if(getDirection()==LEFT_DIR) {
                curAnimation=moveBack;
                moveBack.update(System.nanoTime());
                
            }
            attack.reset();
            attackBack.reset();
             
        }
        if(getAction()==PAUSE){
            if(curAnimation.isLastFrame()) {
                curAnimation =pause;
                pause.update(System.nanoTime());
                move.reset();
                moveBack.reset();
                attack.reset();
                attackBack.reset();
            }
            else {
                curAnimation.update(System.nanoTime());
            }
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rec = new Rectangle((int)getWidth(),(int)getHeight());
        return rec;
    }

}
