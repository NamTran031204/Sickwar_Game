package Button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class button {

	private int x, y, width, height;
	private String text;
	boolean mouseOver;
	boolean mousePress;
	Rectangle outline;
	private BufferedImage img;
	//NORMAL BUTTON
	public button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		initOutline();
	}
	//BUTTON WITH PICS
	public button(int x, int y, int width, int height, BufferedImage img ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = img;
		initOutline();
	}
	
	private void initOutline() {
		this.outline = new Rectangle(x, y, width, height);
	}
	public Rectangle getOutline() {
		return outline;
	}
	
	public void draw(Graphics g) {
		//Body
		drawBody(g);

		//Outline
		drawOutline(g);

		//Text
		drawText(g);

	}

	public void drawOutline(Graphics g) {
		if(mousePress) {
			g.drawRect(x + 1, y+ 1, width- 2, height-2);
			g.drawRect(x + 2, y + 2 , width - 4, height - 4);
			
		}else {
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
		}
		
		
	}

	public void drawBody(Graphics g) {
		if(mouseOver) {
			g.setColor(Color.gray);
		}
		
		else {
			g.setColor(Color.white);
		}
		
		g.fillRect(x, y, width, height);
		
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	public void setMousePress(boolean mousePress) {
		this.mousePress = mousePress;
	}

	private void drawText(Graphics g) {
		int textWidth = g.getFontMetrics().stringWidth(text);
		int textHeight = g.getFontMetrics().stringWidth(text);
		g.drawString(text, x - textWidth/2 + width / 2, y + textHeight/2+  height / 3);
	}
	
	public void resetBoolean() {
		this.mouseOver = false;
		this.mousePress = false;
	}
	
	
	

}
