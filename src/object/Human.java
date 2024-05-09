package object;

import effect.Animation;
import state.GameWorld;

public abstract class Human extends ParticularObject{
    public static final  int MOVING=0;
    public static final int ATTACKING=1;

    private int action=MOVING;
    public Animation move;
    public Animation attack;
    public Animation pause;
    private int cost;

    public Human(float x, float y, float width, float height, int blood, int team,int damage,int cost,GameWorld gameWorld) {
        super(x, y, width, height, blood,team,damage, gameWorld);
        setCost(cost);
        setState(ALIVE);
    }


    @Override
    public void Update() {
        super.Update();
       switch (getGameWorld().mode) {
            case GameWorld.ATTACK:
            for(int i=0;i<getGameWorld().particularObjectManager.particularObjects.size();i++){
                if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null);
                

            }
                break;
        
            case GameWorld.DEFEND:
                
                break;
            case GameWorld.FINAL:
        }
    }

    public abstract void run();

    public abstract void stopRun();

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    

}
