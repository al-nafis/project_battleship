package Audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * This class simply plays a background music in a separate thread
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-03-2018
 */
public class BackgroundMusic implements Runnable{
	
	/**
	 * this boolean indicates whether the background music is playing
	 */
	private boolean isMusicPlaying; 
	
	/**
	 * this method sets the status for isMusicPlaying
	 * @param b if true, it means the music is on
	 */
	public void setIsMusicPlaying(boolean b) {
		isMusicPlaying = b;
	}
	
	/**
	 * this method stops the background music
	 */
	public void stopMusic() {
		isMusicPlaying = false;
		clip.stop();
	}
	
	/**
	 * this method plays the background music
	 */
	public void playMusic() {
		isMusicPlaying = true;
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);	
	}
	
	/**
	 * this method returns isMusicPlaying
	 * @return isMusicPlaying if true, it means music is on
	 */
	public boolean isMusicPlaying() {
		return isMusicPlaying;
	};
	
	/**
	 * this method closes the clip
	 */
	public void closeClip() {
		clip.close();
	}
	
	/**
	 * this object variable holds the Clip instance that controls the audio
	 */
	private Clip clip;
	
	/**
	 * this String holds the file path for an audio
	 */
	private String filePath;
	
	/**
	 * this is the constructor
	 * @param c instance of Clip class
	 * @param filePath String that holds the file location
	 * @param playStatus boolean the states whether the music should be played right away
	 */
	public BackgroundMusic(Clip c, String filePath, boolean playStatus) {
		clip = c;
		this.filePath = filePath;
		isMusicPlaying = playStatus;
	}
	
	/**
	 * this is an overridden method from Runnable interface that executes when a thread starts
	 */
	@Override
	public void run() {
		try {
			File file = new File(filePath);
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			
			clip.open(ais);
			
			//controlling the volume
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20);
			
			clip.start();
			clip.stop();
			
			if(isMusicPlaying) {
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

} //class ends
