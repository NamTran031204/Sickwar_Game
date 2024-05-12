package object;

import state.GameWorldState;

public abstract class GameObject {

    private float posX;
    private float posY;

    private GameWorldState gameWorld;
    
    public GameObject(float x, float y ,GameWorldState gameWorld) {
        posX = x;
        posY = y;
        this.gameWorld = gameWorld;
    }
    public float getPosX() {
        return posX;
    }
    public void setPosX(float posX) {
        this.posX = posX;
    }
    public float getPosY() {
        return posY;
    }
    public void setPosY(float posY) {
        this.posY = posY;
    }

    public GameWorldState getGameWorld() {
        return gameWorld;
    }

    public abstract void Update();
    
}
