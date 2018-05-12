package game.utils;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musicplayer {
	
	public void playMusic() {
		try {
			URL url = Musicplayer.class.getResource("music.wav");
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			Clip music = AudioSystem.getClip();
			music.open(audio);
			music.loop(-1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
