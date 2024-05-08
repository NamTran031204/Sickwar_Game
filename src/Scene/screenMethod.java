package Scene;
import java.awt.Graphics;


public interface screenMethod {
	
	public void render(Graphics g) ;
	public void mouseClicked(int x, int y);
	public void mouseMoved(int x, int y);
	public void mousePress(int x, int y);
	public void mouseRelease(int x, int y);
}
 