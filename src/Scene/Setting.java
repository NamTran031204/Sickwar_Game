package Scene;

import static main.gameStatus.SETTING;
import static main.gameStatus.setGameStatus;

import java.awt.Color;
import java.awt.Graphics;

import Button.button;
import main.Window;
import main.gamePanel;
import static main.gameStatus.*;

public class Setting extends gameScene implements screenMethod{
	button Menu, Quit;
	
	public Setting(Window wd) {
		super(wd);
		initButton();

	}

	

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1920, 1080);
		drawButton(g);
	}
	
	public void drawButton(Graphics g) {
		Menu.draw(g);
		Quit.draw(g);
		
		
	}
	
	public void initButton() {
		Menu = new button(1171, 771, 150, 50, "Menu");
		Quit = new button(1371, 772, 150, 50, "Quit");
	}

	@Override
	public void mouseMoved(int x, int y) {
		Menu.setMouseOver(false);
		if(Menu.getOutline().contains(x, y)) {
			Menu.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePress(int x, int y) {
		if(Menu.getOutline().contains(x, y)) {
			Menu.setMousePress(true);
		}
		
	}

	@Override
	public void mouseRelease(int x, int y) {
		resetButton();
		
	}

	private void resetButton() {
		Menu.resetBoolean();
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(Menu.getOutline().contains(x, y)) {
			setGameStatus(MENU);
		}
		if(Quit.getOutline().contains(x, y)) {
			System.exit(0);
		}
		
		
	}

}
