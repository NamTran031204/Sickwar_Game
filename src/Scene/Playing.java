package Scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Button.button;
import Button.buttonTable;
import main.Window;
import main.gamePanel;
import state.GameWorld;

import static main.gameStatus.*;

public class Playing extends gameScene implements screenMethod{
	Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    final public int SCREEN_WIDTH_MAX=screenSize.width;
    final public int SCREEN_HEIGHT_MAX=screenSize.height;
	
	BufferedImage img;
	BufferedImage setImg;
	button setting;
	buttonTable buttontable;
	

	public Playing(Window wd) {
		super(wd);
		importImg();
		initButton();
		buttontable = new buttonTable(1090,0,445,100);
	}

	

	@Override
	public void render(Graphics g) {
		g.drawImage(img, 0, 0,SCREEN_WIDTH_MAX,SCREEN_HEIGHT_MAX ,null);
		drawButton(g);
		buttontable.draw(g);
	}
	
	
	
	public void drawButton(Graphics g) {
	//	setting.draw(g);
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
		if(x >= 1090 && x <=1535 && y >= 0 && y <= 100) {
			buttontable.mouseMoved(x, y);
		}
		setting.setMouseOver(false);
		if(setting.getOutline().contains(x, y)) {
			setting.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePress(int x, int y) {
		if(x >= 1090 && x <=1535 && y >= 0 && y <= 100) {
			buttontable.mousePress(x, y);
		}
		if(setting.getOutline().contains(x, y)) {
			setting.setMousePress(true);
		}
		
	}

	@Override
	public void mouseRelease(int x, int y) {
		if(x >= 1090 && x <=1535 && y >= 0 && y <= 100) {
			buttontable.mouseRelease(x, y);
		}
		resetButton();
		
	}

	private void resetButton() {
		setting.resetBoolean();
		
	}



	@Override
	public void mouseClicked(int x, int y) {
		if(x >= 1090 && x <=1535 && y >= 0 && y <= 100) {
			buttontable.mouseClicked(x, y);
		}
		else {
			if(setting.getOutline().contains(x, y)) {
			setGameStatus(SETTING);
			}
		}
		
		
		
	}

}
