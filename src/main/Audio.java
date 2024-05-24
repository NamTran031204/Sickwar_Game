package main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Audio {
	Clip clip;
	URL audioURL[] = new URL[30];
	FloatControl fc;
	public int volumeScale = 60;
	float volume;
	
	public Audio() {
		//MENU AUDIO
		audioURL[0] = getClass().getResource("/Sound/themesong.wav");
		//INGAME AUDIO
		audioURL[1] = getClass().getResource("/Sound/soundtrack.wav");
		//SOUND EFFECT
		audioURL[2] = getClass().getResource("/Sound/attack.wav");
		audioURL[3] = getClass().getResource("/Sound/bow_release.wav");
		audioURL[4] = getClass().getResource("/Sound/dao_mo.wav");
		audioURL[5] = getClass().getResource("/Sound/giant-smash.wav");
		audioURL[6] = getClass().getResource("/Sound/kiem_chem.wav");
		audioURL[7] = getClass().getResource("/Sound/WAKANDA FOREVER.wav");
		
	}
	
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		}catch(Exception e) {
			
		}
	}
	
	public void playAudio() {
		clip.start();
	}
	
	public void loopAudio() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void checkVolume() {
		switch(volumeScale) {
		case 0:
			volume = -80f;
			break;
		case 20:
			volume = -20f;
			break;
		case 40:
			volume = -12f;
			break;
		case 60:
			volume = -5f;
			break;
		case 80:
			volume = 1f;
			break;
		case 100:
			volume = 6f;
			break;
		}
		fc.setValue(volume);
	}
	
}