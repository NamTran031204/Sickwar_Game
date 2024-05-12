package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Window;
import main.gameStatus;

import  static main.gameStatus.*;

public class mouseListener implements MouseListener, MouseMotionListener{
	Window wd;
	public mouseListener(Window wd) {
		this.wd = wd;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		switch(gameStatus.gs) {
		case MENU:
				wd.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAYING:
				wd.getPlaying().mouseMoved(e.getX(), e.getY());
			break;
		case SETTING:
				wd.getSetting().mouseMoved(e.getX(), e.getY());
			break;
		default:
			break;
		}
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {

		switch(gameStatus.gs) {
		case MENU:
				wd.getMenu().mouseClicked(e.getX(), e.getY());
			break;
		case PLAYING:
				wd.getPlaying().mouseClicked(e.getX(), e.getY());
			break;
		case SETTING:
				wd.getSetting().mouseClicked(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			System.out.println(e.getX() + " " + e.getY());
		}
		
		switch(gameStatus.gs) {
		
		case MENU:
				wd.getMenu().mousePress(e.getX(), e.getY());
			break;
		case PLAYING:
				wd.getPlaying().mousePress(e.getX(), e.getY());
			break;
		case SETTING:
				wd.getSetting().mousePress(e.getX(), e.getY());
			break;
		default:
			break;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(gameStatus.gs) {
		case MENU:
				wd.getMenu().mouseRelease(e.getX(), e.getY());
			break;
		case PLAYING:
				wd.getPlaying().mouseRelease(e.getX(), e.getY());
			break;
		case SETTING:
				wd.getSetting().mouseRelease(e.getX(), e.getY());
			break;
		default:
			break;
		}
		
	}
		
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
