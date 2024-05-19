package Button;


import java.awt.Color;
import java.awt.Font;
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
	button defend, attack;
	BufferedImage aImg, wImg, dImg, atkImg, defImg;
	Font moneyFont = new Font("Arial", Font.BOLD, 20);
	int Money = 300;
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
		g.fillRect(1413, 100, 120, 200);
		drawButton(g);
		drawGold(g);
	}

	
	public void initButton() {
		archer = new button(1123, 10 , 80, 80, aImg, 100);
		warrior = new button(1270, 10 , 80, 80, wImg, 100);
		digger = new button(1411, 10 , 80, 80, dImg, 50);
		defend = new button(1435, 105, 80, 80, defImg);
		attack = new button(1435, 205, 80, 80, atkImg);

	}
	
	public void drawPicButton(Graphics g) {
		archer.drawPic(g);
		warrior.drawPic(g);
		digger.drawPic(g);
		defend.drawPicWthoutText(g);
		attack.drawPicWthoutText(g);
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
			atkImg = ImageIO.read(getClass().getResourceAsStream("/resource/attack-icon.png"));
			defImg = ImageIO.read(getClass().getResourceAsStream("/resource/defense-icon.png"));
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
		else if(selectedChamp == 2) {
			g.setColor(Color.green);
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
		defend.setMouseOver(false);
		if(defend.getOutline().contains(x, y)) {
			defend.setMouseOver(true);
		}
		attack.setMouseOver(false);
		if(attack.getOutline().contains(x, y)) {
			attack.setMouseOver(true);
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
		if(defend.getOutline().contains(x, y)) {
			defend.setMousePress(true);
		}
		if(attack.getOutline().contains(x, y)) {
			attack.setMousePress(true);
		}
	}

	public void mouseRelease(int x, int y) {
		resetButton();
		
	}

	private void resetButton() {
		warrior.resetBoolean();
		archer.resetBoolean();
		digger.resetBoolean();
		defend.resetBoolean();
		attack.resetBoolean();
	}
	
	


	public void mouseClicked(int x, int y) {
		if(archer.getOutline().contains(x, y)) {
			
			if(Money > archer.money) {
				Money -= (archer.money / 2);
				selectedChamp = 0;
			}
			
			else if(Money == archer.money) {
				Money -= (archer.money);
				selectedChamp = 0;
			}
			
			return;
		}
		if(warrior.getOutline().contains(x, y)) {
			
			if(Money > warrior.money) {
				Money -= (warrior.money / 2);
				selectedChamp = 1;
			}
			
			else if(Money == warrior.money) {
				Money -= (warrior.money);
				selectedChamp = 1;
			}
			return;
		}
		if(digger.getOutline().contains(x, y)) {
			if(Money > digger.money) {
				Money -= (digger.money / 2);
				selectedChamp = 2;
			}
			
			else if(Money == digger.money) {
				Money -= (digger.money);
				selectedChamp = 2;
			}
			return;
		}
		if(defend.getOutline().contains(x, y)) {
			System.out.println("defend");
			return;
		}
		if(attack.getOutline().contains(x, y)) {
			System.out.println("attack");
			return;
		}
	}
	
	public void drawGold(Graphics g) {
		g.setFont(moneyFont);
		g.setColor(Color.white);
		g.drawString(Money + "G", 234, 30);
	}
	

	
	


}
