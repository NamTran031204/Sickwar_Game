package effect;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Loader {
    public static Loader instance=null;
    Hashtable<String,Animation> allAnimation;
    private Loader(){
    }
    public static Loader getInstanceLoader(){  // gioi han chi có 1 instance duoc sinh ra
        if(instance==null)
        instance = new Loader();
        return instance;
    }
    public Hashtable<String,Animation> loadAllAnimation(){
        File file = new File("Stickwar_Game/src/resource/Data/frame");
        Hashtable<String,Animation> allAnimation= new Hashtable<String,Animation>();
    
        BufferedImage image=null;
        String l=null;
             try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int n=Integer.parseInt(reader.readLine());

                for(int i=0;i<n;i++){
                    Animation animation=new Animation();
                    ArrayList<BufferedImage> T= new ArrayList<BufferedImage>();
                    while((l=reader.readLine()).equals(""));// dong trong thi k lgi
                    animation.name=l; // load ten
                    animation.delayTime=Integer.parseInt(reader.readLine());//load delayTime
                    
                    while((l=reader.readLine())!=null&&!l.equals("")){ // tao arraylist frameImage
                        image=ImageIO.read(new File(l));
                        T.add(image);
                    }
                 animation.frameImage=T;// load frameImage
                allAnimation.put(animation.name, animation);
                    }
                    reader.close();
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }

            return allAnimation;

    }
    public void loadData() {
            this.loadAllAnimation();
    }
}