package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Startup.Main;

public class SoundPlayer {
	
	public static synchronized void playSound(final String url) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(
				Main.class.getResource("../Sound/Sounds/" + url));
			clip.open(inputStream);
			clip.start(); 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
