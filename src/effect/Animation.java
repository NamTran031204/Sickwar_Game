package effect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Animation {
    String name;
    int curIndex;
    double delayTime;
    double beginTime=0;
    boolean repeated;
    ArrayList<BufferedImage> frameImage;
    public Animation(){
        
    }
public Animation(ArrayList<BufferedImage> T,double delayTime,String name){
    frameImage = T;
    curIndex=0;
    this.delayTime=delayTime;
    this.name=name;
}
public Animation(Animation animation){
        delayTime=animation.delayTime;
        repeated = animation.repeated;
        curIndex=0;
        frameImage = new ArrayList<BufferedImage>();
        for(BufferedImage f : animation.frameImage){
            frameImage.add(f);
        }
}
public boolean isLastFrame(){
    if (curIndex==frameImage.size()-1) return true;
    else return false;
}
public void update(long curTime){
    if(repeated==true){
    if(beginTime==0) beginTime=curTime;
    else
    {
        if(curTime-beginTime>=delayTime) {
            curIndex=(curIndex+1)%frameImage.size();
            beginTime=curTime;
        }
        
    }}
    else{
        if(beginTime==0) beginTime=curTime;
    else
    {
        if(curTime-beginTime>=delayTime) {
            if(curIndex<frameImage.size()){
            curIndex=curIndex+1;
            beginTime=curTime;}
            else {
                beginTime=curTime;
            }
        }
        
    }
    }
}
public void draw(double x, double y,Graphics2D g2){
    g2.drawImage(getCurImage(), (int)x-getCurImage().getWidth()/2,(int)y-getCurImage().getHeight()/2,null);
    

}
    public void flipAll(){
             for(int i=0;i<frameImage.size();i++){
            BufferedImage image=frameImage.get(i);
           AffineTransform tx= AffineTransform.getScaleInstance(-1,1);
            tx.translate(-image.getWidth(),0);
            AffineTransformOp op=new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image,null);
            frameImage.set(i,image);
        
      }
    }
    public void reset(){
        curIndex=0;
        beginTime=0;
    }
    public int getCurIndex() {
        return this.curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public double getDelayTime() {
        return this.delayTime;
    }
 
    public void setDelayTime(double delayTime) {
        this.delayTime = delayTime;
    }

    public ArrayList<BufferedImage> getFrameImage() {
        return this.frameImage;
    }

    public void setFrameImage(ArrayList<BufferedImage> frameImage) {
        this.frameImage = frameImage;
    }   
    public BufferedImage getCurImage(){
        return frameImage.get(curIndex);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
