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
    public Loader load;
    // can animation die ke thua
    public Giant(float x, float y,int team,GameWorld gameWorld) {
        super(x,y,72,100,3000,team,300,1500,gameWorld); 
        setTeamType(team);
        move = Loader.getInstanceLoader().loadAllAnimation().get("GiantMove");
        moveBack = Loader.getInstanceLoader().loadAllAnimation().get("GiantMove");
        moveBack.flipAll();
        pause= Loader.getInstanceLoader().loadAllAnimation().get("GiantPause");
        setSpeedX(1);
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
            attack.update(System.nanoTime());
            curAnimation=attack;
            }
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==LEFT_DIR){
            attackBack.update(System.nanoTime());
            curAnimation=attackBack;
            }
            move.reset();
            moveBack.reset();

        }
        if(getAction()==MOVING){
            if(getDirection()==RIGHT_DIR){
                 move.update(System.nanoTime());
                 curAnimation=move;
            }
            if(getDirection()==LEFT_DIR) {
                moveBack.update(System.nanoTime());
                curAnimation=moveBack;
            }
            attack.reset();
            attackBack.reset();
             
        }
        if(getAction()==PAUSE){
            if(curAnimation.isLastFrame()) {
                pause.update(System.nanoTime());
                curAnimation =pause;
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
