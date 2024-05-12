package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import Input.mouseListener;
import Scene.Menu;
import Scene.Playing;
import Scene.Setting;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends JFrame implements Runnable{

	private double timePerUpdate;
	private long lastUpdate;
	
	private final int FPS_SET = 60;
	Rendering render;
	
	
	Thread thread;
	Menu menu;
	Playing playing;
	Setting setting;
	gamePanel gp;
	public Window() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		initClasses();
		add(gp);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(true);
	}
	
	private void initClasses() {
		render = new Rendering(this);
		gp = new gamePanel(this);
		menu = new Menu(this);
		playing = new Playing(this);
		setting = new Setting(this);
		
	}
	

	private void start() {
		thread = new Thread(this);
		thread.start();
	}


	private void updateGame() {
		//System.out.println("updated");
	}

	public static void main(String[] args) {
		Window window = new Window();
		window.gp.initInput();
		window.start();
	}

	@Override
	public void run() {
		long now;
		double timePerFrame = 1000000000 / FPS_SET;
		long lastFrame = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		
		
		int frame = 0;
		
	
		//RENDER
		while(true) {
			now = System.nanoTime();
			if(now - lastFrame >= timePerFrame) {
			lastFrame = now;
			repaint();
			frame++;
			}
			
			if(now - lastUpdate >= timePerUpdate) {
				updateGame();

			}
			if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS = " + frame);
				frame = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
		
	}
	
	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Setting getSetting() {
		return setting;
	}


}