package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UI {
	Window wd;
	Font Arial_40;
	int second, minute, realSecond;
	boolean gameFinish;
	public UI(Window wd) {
		this.wd = wd;
		Font Arial_40 = new Font("Arial", Font.BOLD, 20);
	}
	
	public void draw(Graphics g) {
		if(!gameFinish) {
			g.setColor(Color.gray);
			g.fillRect(80, 10, 120, 80);
			g.setFont(Arial_40);
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
	
	
}
