package object;

import state.GameWorld;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.sound.midi.Receiver;

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
        super(x,y,150,400,3000,team,5,1500,gameWorld); 
        moveBack = Loader.getInstanceLoader().getAnimation("GiantMove");
        move = Loader.getInstanceLoader().getAnimation("GiantMove");
        move.flipAll();
        pause= Loader.getInstanceLoader().getAnimation("GiantPause");
        pause.flipAll();
        die=Loader.getInstanceLoader().getAnimation("GiantPause");
        attackBack=Loader.getInstanceLoader().getAnimation("GiantAttack");
        attack=Loader.getInstanceLoader().getAnimation("GiantAttack");
        attack.flipAll();
        setSpeedX(0.5f);
        setSpeedY(0);
        if(getTeamType()==TEAM1) curAnimation=move;
        else curAnimation=moveBack;
    }

    
    public void draw(Graphics2D g2){
        if(curAnimation!=null){
        curAnimation.draw(getPosX(), getPosY(), g2);
        }
    }

    public void Update(){
        super.Update();
        if(getAction()==ATTACKING){
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null &&getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==RIGHT_DIR){
             curAnimation=attackBack;
             attackBack.update(System.nanoTime());
           
            }
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null&&getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==LEFT_DIR){
              curAnimation=attack;
              attack.update(System.nanoTime());
          
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
            if(attack!=null&&attackBack!=null){
            attack.reset();
            attackBack.reset(); 
         }
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
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth(),(int)getHeight());
        return rec;
    }

}
