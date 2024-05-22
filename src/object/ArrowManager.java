package object;

import state.GameWorld;

public class ArrowManager extends ParticularObjectManager {

    public ArrowManager(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void UpdateObjects() {
        
        super.UpdateObjects(); 
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject object = particularObjects.get(id);
                
                if(object.getState() == ParticularObject.DEATH){
                    particularObjects.remove(id);
                    
                }
            }
        }
    }
    
}
