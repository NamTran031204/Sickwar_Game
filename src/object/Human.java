package gameobject;

import state.GameWorldState;

public abstract class Human extends ParticularObject{
    
    public Human(float x, float y, float width, float height, int blood, GameWorldState gameWorld) {
        super(x, y, width, height, blood, gameWorld);
        
        setState(ALIVE);
    }


    @Override
    public void Update() {
        super.Update();
        if(getState() == ALIVE) setPosX(getPosX() + getSpeedX());
    }

    public abstract void run();

    public abstract void stopRun();
    

}
