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
	private int count;
    private int id;//
    // can animation die ke thua
    public Giant(float x, float y,int team,GameWorld gameWorld, int id) {
        super(x,y,150,400,4000,team,1,1500,gameWorld); 
        this.id = id;//
        moveBack = Loader.getInstanceLoader().getAnimation("GiantMove");
        move = Loader.getInstanceLoader().getAnimation("GiantMove");
        move.flipAll();
        pause= Loader.getInstanceLoader().getAnimation("GiantPause");
        pause.flipAll();
        die=Loader.getInstanceLoader().getAnimation("GiantPause");
        attackBack=Loader.getInstanceLoader().getAnimation("GiantAttack");
        attack=Loader.getInstanceLoader().getAnimation("GiantAttack");
        attack.flipAll();
        setSpeedX(2f);
        setSpeedY(0);
        if(getTeamType()==TEAM1) curAnimation=move;
        else curAnimation=moveBack;
    }

    
    public void draw(Graphics2D g2){
        if(curAnimation!=null){
        curAnimation.draw(getPosX(), getPosY(), g2);
        }
    }
    //
    public int getId() {
        return id;
    }
    
    public void Update(){
    	super.Update();
        Statue statue = getGameWorld().statue;
        if (isCollidingWithStatue(statue)) {
            setAction(ATTACKING);
            setSpeedX(0);
            curAnimation = (getDirection() == RIGHT_DIR) ? attack : attackBack;
            curAnimation.update(System.nanoTime());
           }
        
        if (getBlood() <= 0 && getId() == 3) {
            getGameWorld().scheduleWinStateChange();
        }
        
        if(getAction()==ATTACKING){
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null &&getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==RIGHT_DIR){
             curAnimation=attackBack;
            // count++;
             attackBack.update(System.nanoTime());
            // if(count == 100) {
           	//  gameWorld.playSoundEffect(5);
           	// count = 0;
            // }
            }
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null&&getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this).getDirection()==LEFT_DIR){
              curAnimation=attack;
              count++;
              attack.update(System.nanoTime());
              if(count == 200) {
            	  gameWorld.playSoundEffect(5);
            	  count = 0;
              }
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
    @Override
    public Rectangle getObjectBound() {
        Rectangle rec = new Rectangle((int)(getPosX()-getWidth()/2),(int)(getPosY()-getHeight()/2),(int)getWidth(),(int)getHeight());
        return rec;
    }

}
