package Audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 * this class is the audio controlling center
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-13-2018
 *
 */
public class AudioControl {
	
	/**
	 * this variable holds the only available instance of the class
	 */
	private static AudioControl instance = new AudioControl();
	
	/**
	 * this method returns the singleton instance of this class
	 * @return instance the variable that holds the only available instance of the class
	 */
	public static AudioControl getInstance() {
		return instance;
	}
	
	/**
	 * this boolean states whether the soundEffects are enabled
	 */
	private boolean isSoundEffectsOn;
	
	/**
	 * holds a instance variable of Clip that is used for playing background music
	 */
	private Clip clip;
	
	/**
	 * this is an instance of the BackgroundMusic class
	 */
	private BackgroundMusic backgroundMusic;
	
	/**
	 * this variable is used when a new game is stated.
	 * Basically when the control of the application is 
	 * passed on to the Game class, this variable holds 
	 * the status of whether the background music is on
	 */
	private boolean transitionBGMStatus;
	
	/**
	 * sets status for transitionBGMStatus
	 * @param b sets the status of the background music whether it is on
	 */
	public void updateTransitionBGMStatus(boolean b) {
		transitionBGMStatus = b;
	}
	
	/**
	 * this method sets the isSoundEffectOn status
	 * @param b true means soundEffect is on
	 */
	public void setSoundEffect(boolean b){
		isSoundEffectsOn = b;
	}
	
	/**
	 * returns clip
	 * @return clip instance of Clip class
	 */
	public Clip getClip() {
		return clip;
	}
	

	/**
	 * this method returns instance for BackgroundMusic class
	 * @return backgroundMusic
	 */
	public BackgroundMusic getBackgroundMusic() {
		return backgroundMusic;
	}
	
	
	/**
	 * returns inSoundEffectOn
	 * @return isSoundEffectOn states whether the sound effect is enabled
	 */
	public boolean getSoundEffectStatus() {
		return isSoundEffectsOn;
	}
	
	
	/**
	 * this is the constructor for the class
	 */
	private AudioControl() {
		transitionBGMStatus = true;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		this.isSoundEffectsOn = true;
		
		
	}
	
	/**
	 * this method plays the background music for main menu
	 */
	public void playMenuBackgroundMusic() {
		//playing the background music
		backgroundMusic = new BackgroundMusic(clip, "src/sounds/epic_battle_music.wav", transitionBGMStatus);
		new Thread(backgroundMusic).start();
	}
	
	/**
	 * this method plays the background music during the game
	 */
	public void playInGameBackgroundMusic() {
		backgroundMusic = new BackgroundMusic(clip, "src/sounds/inGame.wav", transitionBGMStatus);
		new Thread(backgroundMusic).start();
		if(!transitionBGMStatus) {
			backgroundMusic.setIsMusicPlaying(false);
			backgroundMusic.stopMusic();
		}
	}
	
	/**
	 * this method plays a sound effect when advancing to the next step or section
	 */
	public void goForwardSound() {
		if(isSoundEffectsOn) {
			new Thread(new SoundEffect("src/sounds/goForwardSound.wav")).start();
		}
	}
	
	/**
	 * this method plays a sound effect when going back to the previous step or section
	 */
	public void goBackwardSound() {
		if(isSoundEffectsOn) {
			new Thread(new SoundEffect("src/sounds/goBack.wav")).start();
		}
	}
	
	/**
	 * this method plays a sound effect when the game starts
	 */
	public void gameStartSound() {
		if(isSoundEffectsOn) {
			new Thread(new SoundEffect("src/sounds/war_begins.wav")).start();
		}
	}
	
	/**
	 * this method plays a sound effect when the cursor hovers over a content 
	 */
	public void cellHoverSound() {
		if(isSoundEffectsOn) {
			new Thread(new SoundEffect("src/sounds/hoverSound.wav")).start();
		}
	}
	
	/**
	 * this method plays a sound effect when user or computer hits one of the opponent's ships
	 */
	public void cellHitSound() {
		if(isSoundEffectsOn) {
			new Thread(new SoundEffect("src/sounds/hit.wav")).start();
		}
	}
	
	/**
	 * this method plays a sound effect when user or computer misses a shot during the game
	 */
	public void cellMissSound() {
		if(isSoundEffectsOn) {
			new Thread(new SoundEffect("src/sounds/miss.wav")).start();
		}
	}
	
	/**
	 * this method plays a sound effect when user or computer destroys one of opponent's ships
	 */
	public void shipDestroyingSound() {
		if(isSoundEffectsOn) {
			new Thread(new SoundEffect("src/sounds/shipDestroyed.wav")).start();
		}
	}
	
	/**
	 * this method plays a sound effect when user or computer wins the game
	 */
	public void playWinnerSound() {
		if(isSoundEffectsOn) {
			new Thread(new SoundEffect("src/sounds/victory.wav")).start();
		}
	}
	
} //class ends
