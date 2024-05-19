package Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Button.button;
import Button.buttonTable;
import main.Window;
import main.GamePanel;
import object.Giant;
import state.GameWorld;


import static main.gameStatus.*;

public class Playing extends gameScene implements screenMethod{
	
	BufferedImage img;
	BufferedImage setImg;
	button setting;
	buttonTable buttontable;
	public GameWorld gameWorld;
	
	public Playing(Window wd, GamePanel gp) {
		super(wd, gp);
		importImg();
		initButton();
		buttontable = new buttonTable(1090,0,445,100);
		gameWorld=new GameWorld(this);
	}

	@Override
	public void render(Graphics g) {
			
		gameWorld.Update();
		gameWorld.Render();
		g.drawImage(gameWorld.bufImage, 0, 0, wd);
		drawButton(g);
		buttontable.draw(g);
		wd.ui.draw(g);
		
	}
	
	
	
	public void drawButton(Graphics g) {
		drawPicButton(g);
	}
	public void drawPicButton(Graphics g) {
		setting.drawOutline(g);
		setting.drawBody(g);
		g.drawImage(setImg, 10, 10, 50, 50, null);
	}
	
	public void initButton() {
		setting = new button(4, 4 , 60, 60, setImg);
	}
	
	
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/resource/background.png");
		try {
			img = ImageIO.read(is);
			setImg = ImageIO.read(getClass().getResourceAsStream("/resource/settings-icon-2048x2046-cw28eevx.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	@Override
	public void mouseMoved(int x, int y) {
		if(x >= 1090 && x <=1535 && y >= 0 && y <= 100
		|| x >= 1413 && x <= 1533 && y >= 100 && y <= 300) {
			buttontable.mouseMoved(x, y);
		}
		setting.setMouseOver(false);
		if(setting.getOutline().contains(x, y)) {
			setting.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePress(int x, int y) {
		if(x >= 1090 && x <=1535 && y >= 0 && y <= 100
		|| x >= 1413 && x <= 1533 && y >= 100 && y <= 300) {
			buttontable.mousePress(x, y);
		}
		if(setting.getOutline().contains(x, y)) {
			setting.setMousePress(true);
		}
		
	}

	@Override
	public void mouseRelease(int x, int y) {
		if(x >= 1090 && x <=1535 && y >= 0 && y <= 100
		|| x >= 1413 && x <= 1533 && y >= 100 && y <= 300) {
			buttontable.mouseRelease(x, y);
		}
		resetButton();
		
	}

	private void resetButton() {
		setting.resetBoolean();
		
	}



	@Override
	public void mouseClicked(int x, int y) {
		if(x >= 1090 && x <=1535 && y >= 0 && y <= 100
		|| x >= 1413 && x <= 1533 && y >= 100 && y <= 300) {
			buttontable.mouseClicked(x, y);
		}
		else {
			if(setting.getOutline().contains(x, y)) {
			setGameStatus(SETTING);
			wd.getSetting().Status = 2;
			}
		}
		
		
		
	}

}
