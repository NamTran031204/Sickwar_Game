package object;
// ptd
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Statue {
	private float posX;
	private float posY;
	
	private float width;
	private float height;
	
	private int health;
	private int maxHealth;
	
	private BufferedImage image;
	
	public Statue(float posX, float posY, float width, float height, int maxHealth) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.maxHealth = maxHealth;
		this.health = maxHealth; // Khởi tạo máu ban đầu bằng máu tối đa
		
		try {
            image = ImageIO.read(new File("src/resource/statue/statue_.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void draw(Graphics2D g2) {
		// Vẽ tượng
		g2.drawImage(image, (int) posX, (int) posY, (int) width, (int) height, null);
	//	g2.setColor(Color.GRAY);                                        
	//	g2.fillRect((int) posX, (int) posY, (int) width, (int) height);
		
		// Vẽ thanh máu
		g2.setColor(Color.RED);
		int healthBarWidth = (int) (width * 0.6 * ((float) health / maxHealth)); 
		g2.fillRect((int) posX + 50, (int) posY - 20, healthBarWidth, 10); 
		
	}
	
	public void takeDamage(int damage) { // Gọi trong phần xử lý va chạm
	    health -= damage;
	    if (health < 0) {
	        health = 0;
	    }
	}
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setPosX(float posX) {
		this.posX = posX;
	}
	
	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public int getHealth() {
		return health;
	}
	
    public void setHealth(int health) {
    	this.health = health;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    public Rectangle getBoundForCollision() {
    	return new Rectangle((int)posX, (int)posY, (int)width,(int)height);
    }
}
