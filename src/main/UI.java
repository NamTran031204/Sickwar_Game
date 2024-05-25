package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import state.GameWorld;

public class UI {
	Window wd;
	int x, y;
	String text;
	Font Arial_40 = new Font("Arial", Font.BOLD, 40);
	Font Timer = new Font("Arial", Font.BOLD, 20);
	Font Arial_80 = new Font("Arial", Font.BOLD, 80);
	Font settingFont = new Font("Arial", Font.BOLD, 90);
	int second, minute, realSecond;
	boolean gameFinish;
	public UI(Window wd) {
		this.wd = wd;
	}
	
	public void draw(Graphics g) {
		if(!gameFinish) {
			g.setColor(Color.gray);
			g.fillRect(80, 10, 120, 80);
			g.setFont(Timer);
			g.setColor(Color.white);
			second++;
			realSecond = second / 120;
			if(realSecond == 60) {
			second = 0;
				minute++;
				realSecond = 0;
			}
			g.drawString("Time: " + minute + ":" + realSecond , 85, 30);
		}
		
	}
	
	public void gameLose(Graphics g) {
		text = "YOU LOSE!";
		//textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
		g.setColor(Color.yellow);
		g.setFont(Arial_80);
		x = wd.gp.SCREEN_HEIGHT_MAX / 3  + 200;
		y = wd.gp.SCREEN_WIDTH_MAX / 3 - 100;
		g.drawString(text, x, y);
	}
	
	
	public void gameWin(Graphics g) {
		text = "YOU WIN!";
		//textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
		g.setColor(Color.yellow);
		g.setFont(Arial_80);
		x = wd.gp.SCREEN_HEIGHT_MAX / 3  + 200;
		y = wd.gp.SCREEN_WIDTH_MAX / 3 - 100;
		g.drawString(text, x, y);
	}


	public void settingUI(Graphics g) {
		g.setColor(Color.white);
		String text = "SETTING";
		g.setFont(settingFont);
		g.drawString(text, 550, 100);
		g.setFont(Arial_40);
		g.drawString("Music", 397, 200);
		String musicVolume = Integer.toString(wd.gp.audio.volumeScale);
		g.drawString(musicVolume, 957, 195);
		
		g.drawString("SE", 397, 350);
		String seVolume = Integer.toString(wd.getPlaying().gameWorld.se.volumeScale);
		g.drawString(seVolume, 963, 350);
	}
}
