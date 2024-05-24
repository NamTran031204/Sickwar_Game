package object;

import java.awt.Graphics2D;

import effect.Animation;
import state.GameWorld;
import java.awt.Rectangle;
import effect.Loader;

public class Arrow extends ParticularObject {


    private Animation forwardArrow ; 

    public Arrow(float x , float y , GameWorld gameWorld) {

        super(x, y, 60, 30, 1,1,30, gameWorld);
        forwardArrow = Loader.getInstanceLoader().getAnimation("ForwardArrow");
        
    }
    @Override
    public void draw(Graphics2D g2) {

        forwardArrow.update(System.nanoTime());

        forwardArrow.draw(getPosX(), getPosY(), g2);
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
       return new Rectangle((int)getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight());
    }

    @Override
    public Rectangle getObjectBound() {
        Rectangle rec = new Rectangle(0,0,0,0);
        return rec;
    }
    public void Update() {
        super.Update();
        setPosX(getPosX() + getSpeedX());

        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object != null && object.getState() == ALIVE) {
            setBlood(0);
            object.beHurt(getDamage());
        }
    }
}

