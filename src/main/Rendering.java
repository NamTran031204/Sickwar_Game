package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Rendering {
	Window wd;
	
	public Rendering(Window wd) {
		this.wd = wd;
		
		
	}
	public void render(Graphics g) {
		switch(gameStatus.gs) {
			case MENU:
				wd.menu.render(g);
				break;
			case PLAYING:
				wd.playing.render(g);
				break;
			case SETTING:
				wd.setting.render(g);
				break;
		}
	}
	
	
	
	
	
}
