package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import Input.mouseListener;
import main.Audio;

public class GamePanel extends JPanel{
	
	Random random;
	Window wd;
	Dimension size;
	mouseListener mouse;
	public Audio audio = new Audio();
	public Audio se = new Audio();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int SCREEN_WIDTH_MAX=screenSize.width;
    public int SCREEN_HEIGHT_MAX=screenSize.height;

	public int Window_Height = (int)screenSize.getHeight() * 2;
	public int Window_Width = (int)screenSize.getWidth() * 2;

	
	public GamePanel(Window wd) {
		this.wd = wd;
		
		setWindowSize();
		initInput();
		random = new Random();

	}
	
	public void initInput() {
		
		mouse = new mouseListener(wd);
		addMouseListener(mouse);
		
		requestFocus();
	}
	
	private void setWindowSize() {
		size = new Dimension(Window_Height, Window_Width);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		wd.render.render(g);
	}
	

	
	public void playMusic(int i) {
		audio.setFile(i);
		audio.playAudio();
		audio.loopAudio();
		
	}
	
	public void stopMusic() {
		audio.stop();
	}
	
	public void playSoundEffect(int i) {
		se.setFile(i);
		se.playAudio();
	}
	
	



	

}
