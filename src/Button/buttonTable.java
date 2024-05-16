package Button;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class buttonTable {
	private int x, y ,width, height;
	button archer;
	button warrior;
	button digger;
	BufferedImage aImg, wImg, dImg;
	int selectedChamp = 3;
	
	public buttonTable(int x, int y, int width, int height) {
		this.x = x;
		 this.y = y;
		 this.width = width;
		 this.height = height;
		 importImg();
		 initButton();

	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
		drawButton(g);
		
	}

	
	public void initButton() {
		archer = new button(1123, 10 , 80, 80, aImg, "100G");
		warrior = new button(1270, 10 , 80, 80, wImg, "100G");
		digger = new button(1411, 10 , 80, 80, dImg, "100G");

	}
	
	public void drawPicButton(Graphics g) {
		archer.drawPic(g);
		warrior.drawPic(g);
		digger.drawPic(g);
	}
	
	private void drawButton(Graphics g) {
		drawPicButton(g);
		drawSelectedChamp(g);
	}
	
	private void importImg() {
		try {
			aImg = ImageIO.read(getClass().getResourceAsStream("/resource/archer_icon-removebg-preview.png"));
			wImg = ImageIO.read(getClass().getResourceAsStream("/resource/warrior_icon-removebg-preview.png"));
			dImg = ImageIO.read(getClass().getResourceAsStream("/resource/miner_icon-removebg-preview.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawSelectedChamp(Graphics g) {
		if(selectedChamp == 0) {
			g.setColor(Color.red);
			g.fillRect(500, 500, 60, 60);
		}
		else if(selectedChamp == 1) {
			g.setColor(Color.blue);
			g.fillRect(500, 500, 60, 60);
		}
		else if(selectedChamp == 2) {
			g.setColor(Color.green);
			g.fillRect(500, 500, 60, 60);
		}
	}

	public void mouseMoved(int x, int y) {
		archer.setMouseOver(false);
		if(archer.getOutline().contains(x, y)) {
			archer.setMouseOver(true);
		}
		digger.setMouseOver(false);
		if(digger.getOutline().contains(x, y)) {
			digger.setMouseOver(true);
		}
		warrior.setMouseOver(false);
		if(warrior.getOutline().contains(x, y)) {
			warrior.setMouseOver(true);
		}
		
	}


	public void mousePress(int x, int y) {
		if(archer.getOutline().contains(x, y)) {
			archer.setMousePress(true);
		}
		if(digger.getOutline().contains(x, y)) {
			digger.setMousePress(true);
		}
		if(warrior.getOutline().contains(x, y)) {
			warrior.setMousePress(true);
		}
	}

	public void mouseRelease(int x, int y) {
		resetButton();
		
	}

	private void resetButton() {
		warrior.resetBoolean();
		archer.resetBoolean();
		digger.resetBoolean();
		
	}
	
	


	public void mouseClicked(int x, int y) {
		if(archer.getOutline().contains(x, y)) {
			selectedChamp = 0;
			return;
		}
		else if(warrior.getOutline().contains(x, y)) {
			selectedChamp = 1;
			return;
		}
		else if(digger.getOutline().contains(x, y)) {
			selectedChamp = 2;
			return;
		}
		
		
	}

	

	
	


}
