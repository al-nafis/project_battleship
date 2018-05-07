package Audio;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

/**
 * this class has the elements for a sound track to play
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-05-2018
 */
public class SoundEffect implements Runnable{

	/**
	 * this String variable holds a file path for the audio track
	 */
	private String file;
	
	/**
	 * this is the constructor that assigns the audio file path to the file variable
	 * @param file String that holds the file's location
	 */
	public SoundEffect(String file) {
		this.file = file;
	}
	
	/**
	 * this holds the instance of Clip class
	 */
	private Clip clip;
	
	/**
	 * this is an overridden method from Runnable interface that executes when a thread starts
	 */
	@Override
	public void run() {
	
		try {
			File soundFile = new File(file);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(ais);
			clip.start();
			
			//controlling the volume
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
} //class ends

