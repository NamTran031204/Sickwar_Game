package Scene;

import static main.gameStatus.SETTING;
import static main.gameStatus.setGameStatus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import Button.button;
import main.Window;
import state.GameWorld;
import main.GamePanel;


import static main.gameStatus.*;

public class Setting extends gameScene implements screenMethod{
	button Menu, Quit, Return, Plus, Minus, Plus1, Minus1;
	Font setFont = new Font("Arial", Font.BOLD, 20);
	public static int Status;
	public Setting(Window wd, GamePanel gp) {
		super(wd, gp);
		initButton();

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1920, 1080);
		drawButton(g);
		wd.ui.settingUI(g);
	}
	
	public void drawButton(Graphics g) {
		Menu.draw(g);
		Quit.draw(g);
		Return.draw(g);
		g.setFont(setFont);
		Plus.draw(g);
		Minus.draw(g);
		Plus1.draw(g);
		Minus1.draw(g);
		
	}
	
	public void initButton() {
		Menu = new button(670, 620, 150, 50, "Menu");
		Quit = new button(955, 620, 150, 50, "Quit");
		Return = new button(400, 620, 150, 50, "Return");
		Plus = new button(1054, 158, 50, 50, "+");
		Minus = new button(855, 158, 50, 50, "-");
		Plus1 = new button(1054, 308, 50, 50, "+");
		Minus1 = new button(855, 308, 50, 50, "-");
	}

	@Override
	public void mouseMoved(int x, int y) {
		Return.setMouseOver(false);
		if(Return.getOutline().contains(x, y)) {
			Return.setMouseOver(true);
		}
		Menu.setMouseOver(false);
		if(Menu.getOutline().contains(x, y)) {
			Menu.setMouseOver(true);
		}
		Plus.setMouseOver(false);
		if(Plus.getOutline().contains(x, y)) {
			Plus.setMouseOver(true);
		}
		Minus.setMouseOver(false);
		if(Minus.getOutline().contains(x, y)) {
			Minus.setMouseOver(true);
		}
		Plus1.setMouseOver(false);
		if(Plus1.getOutline().contains(x, y)) {
			Plus1.setMouseOver(true);
		}
		Minus1.setMouseOver(false);
		if(Minus1.getOutline().contains(x, y)) {
			Minus1.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePress(int x, int y) {
		if(Return.getOutline().contains(x, y)) {
			Return.setMousePress(true);
		}
		if(Menu.getOutline().contains(x, y)) {
			Menu.setMousePress(true);
		}
		if(Plus.getOutline().contains(x, y)) {
			Plus.setMousePress(true);
		}
		if(Minus.getOutline().contains(x, y)) {
			Minus.setMousePress(true);
		}
		if(Plus1.getOutline().contains(x, y)) {
			Plus1.setMousePress(true);
		}
		if(Minus1.getOutline().contains(x, y)) {
			Minus1.setMousePress(true);
		}
		
	}

	@Override
	public void mouseRelease(int x, int y) {
		resetButton();
		
	}

	private void resetButton() {
		Menu.resetBoolean();
		Return.resetBoolean();
		Plus.resetBoolean();
		Minus.resetBoolean();
		Plus1.resetBoolean();
		Minus1.resetBoolean();
	}

	@Override
	public void mouseClicked(int x, int y) {
		
		if(Plus.getOutline().contains(x, y)) {
			
			if(gp.audio.volumeScale < 100) {
				gp.audio.volumeScale += 10;
			}
			gp.audio.checkVolume();
			
		}
		if(Minus.getOutline().contains(x, y)) {
			if(gp.audio.volumeScale > 0) {
				gp.audio.volumeScale -= 10;
			}
			gp.audio.checkVolume();
			
		}
		
		if(Plus1.getOutline().contains(x, y)) {
			if(wd.getPlaying().gameWorld.se.volumeScale < 100) {
				wd.getPlaying().gameWorld.se.volumeScale += 20;
			}
			wd.getPlaying().gameWorld.se.checkVolume();
			
		}
		if(Minus1.getOutline().contains(x, y)) {
			if(wd.getPlaying().gameWorld.se.volumeScale > 0) {
				wd.getPlaying().gameWorld.se.volumeScale -= 20;
			}
			wd.getPlaying().gameWorld.se.checkVolume();
			
		}
		if(Menu.getOutline().contains(x, y)) {
			setGameStatus(MENU);
			gp.stopMusic();
			gp.audio.checkVolume();
			gp.playMusic(0);
			
		}
		if(Quit.getOutline().contains(x, y)) {
			int result = JOptionPane.showConfirmDialog(null, "Quitting?", null, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		
		if(Return.getOutline().contains(x, y)) {
			if(Status == 1) {
				setGameStatus(MENU);
				Status = 0;
			}
			if(Status == 2) {
				setGameStatus(PLAYING);
				Status = 0;
			}
		}
		
		
		
	}

}
