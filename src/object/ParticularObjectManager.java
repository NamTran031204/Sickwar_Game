package object;

import state.GameWorld;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ParticularObjectManager {

    protected List<ParticularObject> particularObjects;

    private GameWorld gameWorld;
    
    public ParticularObjectManager(GameWorld gameWorld){
        
        particularObjects = Collections.synchronizedList(new LinkedList<ParticularObject>());
        this.gameWorld = gameWorld;
        
    }
    
    public GameWorld getGameWorld(){
        return gameWorld;
    }
    
    public void addObject(ParticularObject particularObject){
        
        
        synchronized(particularObjects){
            particularObjects.add(particularObject);
        }
        
    }
    
    public void RemoveObject(ParticularObject particularObject){
        synchronized(particularObjects){
        
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject object = particularObjects.get(id);
                if(object == particularObject)
                    particularObjects.remove(id);
                
            }
        }
    }
    
    public ParticularObject getCollisionWidthEnemyObject(ParticularObject object){
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject objectInList = particularObjects.get(id);

                if(object.getTeamType() != objectInList.getTeamType() && 
                        object.getBoundForCollisionWithEnemy().intersects(objectInList.getObjectBound())){
                    return objectInList;
                }
            }
        }
        return null;
    }

    public void UpdateObjects(){
        
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject object = particularObjects.get(id);
                
                
                object.Update();
                
                if(object.getState() == ParticularObject.DEATH){
                    particularObjects.remove(id);
                }
            }
        }

        
        
    }
    
//  ptd
    public void checkCollisionWithStatue(Statue statue) {
        synchronized (particularObjects) {
            for (ParticularObject object : particularObjects) {
                if (object.getTeamType() == ParticularObject.TEAM2) { // TEAM2 là đội của kẻ thù
                    if (object.getBoundForCollisionWithEnemy().intersects(statue.getBoundForCollision())) {
                        statue.takeDamage(object.getDamage());
                        if (statue.getHealth() <= 0) {
                            gameWorld.state = GameWorld.GAMEOVER; // Trò chơi kết thúc khi tượng bị phá hủy
                        }
                    }
                }
            }
        }
    }
    
    public void draw(Graphics2D g2){
        synchronized(particularObjects){
            for(ParticularObject object: particularObjects)
                 object.draw(g2);
        }
    }
	
}