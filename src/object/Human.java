package object;

import state.GameWorld;

public abstract class Human extends ParticularObject{
    public static final  int MOVING=0;
    public static final int ATTACKING=1;
    public static final int PAUSE=2;
    private int action=MOVING;
 
    private int cost;

    public Human(float x, float y, float width, float height, int blood, int team,int damage,int cost,GameWorld gameWorld) {
        super(x, y, width, height, blood,team,damage, gameWorld);
        setCost(cost);
        setState(ALIVE);
    }


    @Override
    public void Update() {
        super.Update();
        if(getState()==ALIVE){
        if(action==ATTACKING){
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null){
            action=ATTACKING;
            }
            else {
                action=MOVING;
            }
        }
           
            
        
        if(action == MOVING){
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null){
                action=ATTACKING;
                }
            else {
                if(getTeamType()==TEAM1){
                if(getGameWorld().state==GameWorld.ATTACK)   {
                
                    setDirection(RIGHT_DIR);
                    if(getPosX()+getSpeedX()*getDirection()<=1000){
                        setPosX(getPosX()+getSpeedX()*getDirection());
                        action=MOVING;
                    }
                    else{
                        action=PAUSE;
                        
                    }
                }
            
                else if(getGameWorld().state==GameWorld.DEFEND){
                
                    setDirection(LEFT_DIR);
                    if(getPosX()+getSpeedX()*getDirection()>=200){
                    setPosX(getPosX()+getSpeedX()*getDirection());
                        action=MOVING;
                    }
                    else{

                        action=PAUSE;
                    }
                }
                }
                if(getTeamType()==TEAM2){
                    if(getPosX()+getSpeedX()*getDirection()>=0){
                        setPosX(getPosX()+getSpeedX()*getDirection());
                    }
                    else 
                    setPosX(0);
                }
            
        }
        }
        if(action==PAUSE){
            if(getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this)!=null){
                action=ATTACKING;
                }
                if(getTeamType()==TEAM1){
            if(getGameWorld().state==GameWorld.ATTACK&&getPosX()<=1000){
                action=MOVING;
            }
            if(getGameWorld().state==GameWorld.DEFEND&&getPosX()>=200){
                action=MOVING;
            }
                }
    }
    }
    }

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
