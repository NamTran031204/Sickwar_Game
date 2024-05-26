package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import state.GameWorld; 
import effect.Animation;
import effect.Loader;


public class Warrior extends Human {



    private Animation WarriorRun , WarriorRunBack;
    private Animation WarriorAttack , WarriorAttackBack;
    private Animation curAnimation , WarriorPause;
	private int count;

    
   

    public Warrior(float x , float y ,int team, GameWorld gameWorld) {
        super(x, y, 70, 100, 800, team, 2, 500, gameWorld);

        setTeamType(TEAM1);

        WarriorRun = Loader.getInstanceLoader().getAnimation("WarriorRun");
        WarriorRunBack = Loader.getInstanceLoader().getAnimation("WarriorRun");
        WarriorRunBack.flipAll();

        WarriorAttack = Loader.getInstanceLoader().getAnimation("WarriorAttack");
        WarriorAttackBack = Loader.getInstanceLoader().getAnimation("WarriorAttack");
        WarriorAttackBack.flipAll();

        WarriorPause = Loader.getInstanceLoader().getAnimation("WarriorPause");

        curAnimation = WarriorRun;

        setSpeedX(3);

    }

    public void draw(Graphics2D g2){
        
        curAnimation.draw(getPosX(), getPosY(), g2);
        g2.setColor(Color.red);
        g2.fillRect((int)getPosX() - (int) getWidth()/2, (int)getPosY() - (int)getHeight()/2 - 100, (int)(getBlood()/3000*getWidth()), 10);
    }


    @Override
    public void Update() {
        super.Update();
        if(getAction() == ATTACKING) {

            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null &&getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==RIGHT_DIR){
             curAnimation = WarriorAttackBack;
             WarriorAttackBack.update(System.nanoTime());
           
            }
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null&&getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==LEFT_DIR){
              curAnimation = WarriorAttack;
              count++;
              WarriorAttack.update(System.nanoTime());
              if(count == 100) {
            	  gameWorld.playSoundEffect(6);
            	  count = 0;
              }
            }
            WarriorRun.reset();
            WarriorRunBack.reset();

        }
        if(getAction() == MOVING){
            if(getDirection() == RIGHT_DIR){
                 curAnimation = WarriorRun;
                 WarriorRun.update(System.nanoTime());
                
            }
            if(getDirection() == LEFT_DIR) {
                curAnimation = WarriorRunBack;
                WarriorRunBack.update(System.nanoTime());
                
            }
            if(WarriorAttack!=null&&WarriorAttackBack!=null){
            WarriorAttack.reset();
            WarriorAttackBack.reset();
            }
             
        }
        if(getAction() == PAUSE) {

            if(curAnimation.isLastFrame()) {
                curAnimation = WarriorPause;
                WarriorPause.update(System.nanoTime());
                WarriorRun.reset();
                WarriorRunBack.reset();
                WarriorAttack.reset();
                WarriorAttackBack.reset();
            }
            else {
                curAnimation.update(System.nanoTime());
            }
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth()+10,(int)getHeight()+10);
        return rec;
    }
    @Override
    public Rectangle getObjectBound() {
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth(),(int)getHeight());
        return rec;
    }

}