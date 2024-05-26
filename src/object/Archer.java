
package object;

import state.GameWorld;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import effect.Animation;
import effect.Loader;


public class Archer extends Human {

    private Animation ArcherRun , ArcherRunBack;
    private Animation ArcherAttack , ArcherAttackBack;
    private Animation curAnimation , ArcherPause;

    private boolean isShooting ;
    private long lastShootingTime;
        
    public Archer (float x , float y ,int team, GameWorld gameWorld) {
        super(x, y, 70, 100, 100, team, 5, 500, gameWorld);

        setTeamType(TEAM1);

        ArcherRun = Loader.getInstanceLoader().getAnimation("ArcherRun");
        ArcherRunBack = Loader.getInstanceLoader().getAnimation("ArcherRun");
        ArcherRunBack.flipAll();

        ArcherAttack = Loader.getInstanceLoader().getAnimation("ArcherAttack");
        ArcherAttackBack = Loader.getInstanceLoader().getAnimation("ArcherAttack");
        ArcherAttackBack.flipAll();

        ArcherPause = Loader.getInstanceLoader().getAnimation("ArcherPause");

        curAnimation = ArcherRun;

        setSpeedX(2);

    }
    public void draw(Graphics2D g2){
        
        curAnimation.draw(getPosX(), getPosY(), g2);
       
    }


    @Override
    public void Update() {
        super.Update();

        if(isShooting) {
            if(System.nanoTime() - lastShootingTime > 1000*1000000){
                isShooting = false;
            }
        }

        if(getAction() == ATTACKING) {

            if(!isShooting) {

                Arrow arrow = new Arrow(getPosX(), getPosY()-25, getGameWorld());

                if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null &&getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==RIGHT_DIR){
                curAnimation = ArcherAttackBack;
                ArcherAttackBack.update(System.nanoTime());
                
                arrow.setSpeedX(-3);
                
                }
                if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null&&getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==LEFT_DIR){
                curAnimation = ArcherAttack;
                ArcherAttack.update(System.nanoTime());

                arrow.setSpeedX(3);

                }
                getGameWorld().arrowManager.addObject(arrow);
                ArcherRun.reset();
                ArcherRunBack.reset();

                lastShootingTime = System.nanoTime();
                isShooting = true;

           }
            
        }
        if(getAction() == MOVING){
            if(getDirection() == RIGHT_DIR){
                 curAnimation = ArcherRun;
                 ArcherRun.update(System.nanoTime());
                
            }
            if(getDirection() == LEFT_DIR) {
                curAnimation = ArcherRunBack;
                ArcherRunBack.update(System.nanoTime());
                
            }
            ArcherAttack.reset();
            ArcherAttackBack.reset();
            //System.out.println(getPosX());
             
        }
        if(getAction() == PAUSE) {

            if(curAnimation.isLastFrame()) {
                curAnimation = ArcherPause;
                ArcherPause.update(System.nanoTime());
                ArcherRun.reset();
                ArcherRunBack.reset();
                ArcherAttack.reset();
                ArcherAttackBack.reset();
            }
            else {
                curAnimation.update(System.nanoTime());
            }
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth() + 300,(int)getHeight());
        return rec;
    }
    @Override
    public Rectangle getObjectBound() {
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth(),(int)getHeight());
        return rec;
    }
        
    
}
