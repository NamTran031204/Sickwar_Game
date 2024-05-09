package object;

import state.GameWorld;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import effect.Animation;

public abstract class ParticularObject extends GameObject {

    public static final int TEAM1 = 0;
    public static final int TEAM2 = 1;

    public static final int LEFT_DIR = 0;
    public static final int RIGHT_DIR = 1;

    public static final int ALIVE = 0;
   // public static final int BEHURT = 1;
    public static final int DEATH = 2;

    private int state = ALIVE;

    private float width;
    private float height;
    private float speedX;
    private float speedY;
    private int blood;
    
    private int damage;
    private Animation die;
    private int direction;

    private int teamType;

    private long t=0;
    public ParticularObject(float x , float y, float width , float height , int blood,int team,int damage ,GameWorld gameWorld ) {
        
        super(x,y,gameWorld);
        setBlood(blood);
        setWidth(width);
        setHeight(height);
        setDamage(damage);
        if(teamType==TEAM1){
        direction = RIGHT_DIR;    
        }
        if(teamType==TEAM2){
            direction=LEFT_DIR;
        }
        gameWorld.particularObjectManager.addObject(this);
    }

   @Override
    public void Update() {
        
        switch (state) {
            case ALIVE:
                ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
                if(object != null) {

                    if(object.getDamage() > 0) {

                        beHurt(object.getDamage());
                        
                    }
                }

                break;
            case DEATH:
                die.update(System.nanoTime());
               // state = DEATH;

                break;
        }
    } 
       public void beHurt(int dameEat) {

        if(getBlood()-dameEat<=0) {
            setBlood(0);
        }
        else {
        setBlood(getBlood()-dameEat);

        }
        //setBlood(getBlood() - dameEat);
        //if(getBlood()<=0) state = DEATH;
        //state = BEHURT;

    
    }
    public abstract Rectangle getBoundForCollisionWithEnemy();

    public abstract void draw(Graphics2D g2);
    
    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }


    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    


    public float getSpeedY() {
        return this.speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

}