package Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Button.button;
import main.Window;
import main.GamePanel;


import static main.gameStatus.*;

public class Menu extends gameScene implements screenMethod{
	
	BufferedImage img;
	Random random;
	button play, set, quit;
	public Menu(Window wd, GamePanel gp) {
		super(wd, gp);
		random = new Random();
//		importImg();
//		loadSprite();
		initButton();
	}

	private void initButton() {
		int width = 150;
		int height = width / 3;

		play = new button(649, 388, width, height, "Play");
		set = new button(646, 506, width, height, "Setting");
		quit = new button(645, 660 ,width, height, "Quit");
		
	}
	
	

	@Override
	public void render(Graphics g) {
		
		drawButton(g);
		
	}
	private void drawButton(Graphics g) {
		play.draw(g);
		set.draw(g);
		quit.draw(g);
		
	}

//	private void importImg() {
//		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
//		try {
//			img = ImageIO.read(is);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	private void loadSprite() {
//		
//		for(int y = 0; y < 10; y++) {
//			for(int x = 0; x < 10; x++) {
//				sprite.add(img.getSubimage(x * 32, y * 32, 32, 32));
//			}
//		}	
//	}
//	
//	private int getRandomInt() {
//		return random.nextInt(80);
//	}

	@Override
	public void mouseClicked(int x, int y) {
		if(play.getOutline().contains(x, y)) {
			setGameStatus(PLAYING);
		}
		if(set.getOutline().contains(x, y)) {
			wd.getSetting().Status = 1;
			setGameStatus(SETTING);

		}
		if(quit.getOutline().contains(x, y)) {
			int result = JOptionPane.showConfirmDialog(null, "Quitting?", null, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		play.setMouseOver(false);
		if(play.getOutline().contains(x, y)) {
			play.setMouseOver(true);
		}
		set.setMouseOver(false); 
		if(set.getOutline().contains(x, y)) {
			set.setMouseOver(true);
		}
		quit.setMouseOver(false);
		if(quit.getOutline().contains(x, y)) {
			quit.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePress(int x, int y) {
		if(play.getOutline().contains(x, y)) {
			play.setMousePress(true);
		}
		if(set.getOutline().contains(x, y)) {
			set.setMousePress(true);
		}
		if(quit.getOutline().contains(x, y)) {
			quit.setMousePress(true);
		}
		
	}

	@Override
	public void mouseRelease(int x, int y) {
		resetButton();
		
	}

	private void resetButton() {
		play.resetBoolean();
		set.resetBoolean();
		quit.resetBoolean();
		
	}

}
