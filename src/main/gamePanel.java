package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import Input.mouseListener;

public class gamePanel extends JPanel{
	
	Random random;
	Window wd;
	Dimension size;
	mouseListener mouse;
    Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    final public int SCREEN_WIDTH_MAX=screenSize.width;
    final public int SCREEN_HEIGHT_MAX=screenSize.height;

	public final int Window_Height = 1920;
	public final int Window_Width = 1080;

	
	public gamePanel(Window wd) {
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
	



	

}
