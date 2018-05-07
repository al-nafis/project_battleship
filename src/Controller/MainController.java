package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Audio.AudioControl;
import Model.Computer;
import View.MainFrame;

/**
 * This class acts as the controller of the whole application
 * 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-03-2018
 */
public class MainController {
	
	/**
	 * holds the only instance of the class 'Controller'
	 */
	private static MainController instance = new MainController();
	
	/**
	 * this method returns the only instance of the class 'Controller'
	 * @return instance
	 */
	public static MainController getInstance() {
		return instance;
	}
	
	/**
	 * this variable holds the instance of the MainWindow class
	 */
	private MainFrame window;
	
	/**
	 * this variable contains an instance of the Game class
	 */
	private Game game;
	
	/**
	 * an instances of MouseListenerEffect
	 */
	private MouseEffect musicOnButtonMouseListener;
	
	/**
	 * an instances of MouseListenerEffect
	 */
	private MouseEffect musicOffButtonMouseListener;
	
	/**
	 * an instances of MouseListenerEffect
	 */
	private MouseEffect soundOnButtonMouseListener;
	
	/**
	 * an instances of MouseListenerEffect
	 */
	private MouseEffect soundOffButtonMouseListener;
	
	/**
	 * this instance holds the audio control object
	 */
	private AudioControl audioControl;
	
	/**
	 * this variable holds the key number that defines the game difficulty level that is stated in the Computer class
	 */
	private int difficultyLevel;
	
	/**
	 * this method returns the the integer that states the difficulty level of the current game
	 * @return difficultyLevel integer that states the difficulty level
	 */
	public int getDifficultyLevel() {
		return difficultyLevel;
	}
	
	/**
	 * this method returns the instance of the Game class
	 * @return game instance of Game
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * this method returns the AudioControl instance
	 * @return audioControl
	 */
	public AudioControl getAudioControl() {
		return audioControl;
	}
	
	/**
	 * this method returns the MouseEffect instance
	 * @return musicOnButtonMouseListener
	 */
	public MouseEffect getMusicOnButtonMouseListener() {
		return musicOnButtonMouseListener;
	}

	/**
	 * this method returns the MouseEffect instance
	 * @return musicOffButtonMouseListener
	 */
	public MouseEffect getMusicOffButtonMouseListener() {
		return musicOffButtonMouseListener;
	}

	/**
	 * this method returns the MouseEffect instance
	 * @return soundOnButtonMouseListener
	 */
	public MouseEffect getSoundOnButtonMouseListener() {
		return soundOnButtonMouseListener;
	}

	/**
	 * this method returns the MouseEffect instance
	 * @return soundOffButtonListener
	 */
	public MouseEffect getSoundOffButtonMouseListener() {
		return soundOffButtonMouseListener;
	}

	/**
	 * this is the constructor of the class
	 */
	private MainController() {
		
		
		//initializing the frame
		window = MainFrame.getInstance();
		
		//initializing mouseListenerEffect instances
		musicOnButtonMouseListener = new MouseEffect(window.getMenus().getMusicOnButton(), MouseEffect.ForMenuButton, this);
		musicOffButtonMouseListener = new MouseEffect(window.getMenus().getMusicOffButton(), MouseEffect.ForMenuButton, this);
		soundOnButtonMouseListener = new MouseEffect(window.getMenus().getSoundOnButton(), MouseEffect.ForMenuButton, this);
		soundOffButtonMouseListener = new MouseEffect(window.getMenus().getSoundOffButton(), MouseEffect.ForMenuButton, this);
		
		//effects on hovering over the menu items
		addTheInitialHoveringEffects();
		
		//adding action listener
		addTheInitialActionListeners();
		
		//adding audio control
		audioControl = AudioControl.getInstance();
		audioControl.playMenuBackgroundMusic();
		
	}
	
	/**
	 * this method adds the required hovering effects to the game
	 */
	private void addTheInitialHoveringEffects() {
		window.getMenus().getNewGameButton().addMouseListener(new MouseEffect(window.getMenus().getNewGameButton(), MouseEffect.ForMenuButton, this));
		window.getMenus().getOptionsButton().addMouseListener(new MouseEffect(window.getMenus().getOptionsButton(), MouseEffect.ForMenuButton, this));
		window.getMenus().getCreditsButton().addMouseListener(new MouseEffect(window.getMenus().getCreditsButton(), MouseEffect.ForMenuButton, this));
		
		window.getMenus().getEasyDifficultyButton().addMouseListener(new MouseEffect(window.getMenus().getEasyDifficultyButton(), MouseEffect.ForMenuButton, this));
		window.getMenus().getNormalDifficultyButton().addMouseListener(new MouseEffect(window.getMenus().getNormalDifficultyButton(), MouseEffect.ForMenuButton, this));
		window.getMenus().getGoBackFromNewGameButton().addMouseListener(new MouseEffect(window.getMenus().getGoBackFromNewGameButton(), MouseEffect.ForMenuButton, this));
		
		window.getMenus().getMusicOnButton().addMouseListener(musicOnButtonMouseListener);
		window.getMenus().getMusicOffButton().addMouseListener(musicOffButtonMouseListener);
		window.getMenus().getSoundOnButton().addMouseListener(soundOnButtonMouseListener);
		window.getMenus().getSoundOffButton().addMouseListener(soundOffButtonMouseListener);
		window.getMenus().getGoBackFromOptionsButton().addMouseListener(new MouseEffect(window.getMenus().getGoBackFromOptionsButton(), MouseEffect.ForMenuButton, this));
		
		window.getMenus().getGoBackFromCreditsButton().addMouseListener(new MouseEffect(window.getMenus().getGoBackFromCreditsButton(), MouseEffect.ForMenuButton, this));
	} //addTheInitialHoveringEffects ends
	
	
	/**
	 * this method adds the required action listeners to the game
	 */
	private void addTheInitialActionListeners(){
		window.getMenus().getNewGameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				window.getMenus().removeMenuBox();	
				window.getMenus().showDifficultyMenu();
				audioControl.goForwardSound();
			}
		});
		window.getMenus().getOptionsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(audioControl.getBackgroundMusic().isMusicPlaying()) {
					musicOnButtonMouseListener.setButtonEnabled(false);
					musicOffButtonMouseListener.setButtonEnabled(true);
					window.getMenus().getMusicOnButton().setEnabled(false);
					window.getMenus().getMusicOffButton().setEnabled(true);
				} else {
					musicOnButtonMouseListener.setButtonEnabled(true);
					musicOffButtonMouseListener.setButtonEnabled(false);
					window.getMenus().getMusicOnButton().setEnabled(true);
					window.getMenus().getMusicOffButton().setEnabled(false);
				}
				if(audioControl.getSoundEffectStatus()) {
					soundOnButtonMouseListener.setButtonEnabled(false);
					soundOffButtonMouseListener.setButtonEnabled(true);
					window.getMenus().getSoundOnButton().setEnabled(false);
					window.getMenus().getSoundOffButton().setEnabled(true);
				} else {
					soundOnButtonMouseListener.setButtonEnabled(true);
					soundOffButtonMouseListener.setButtonEnabled(false);
					window.getMenus().getSoundOffButton().setEnabled(false);
					window.getMenus().getSoundOnButton().setEnabled(true);
				}
				window.getMenus().removeMenuBox();
				window.getMenus().getMainMenu().add(window.getMenus().getOptionBox());
				window.getMenus().showOptionsMenu();
				audioControl.goForwardSound();
			}
		});
		window.getMenus().getCreditsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				window.getMenus().removeMenuBox();
				window.getMenus().showCreditMenu();
				audioControl.goForwardSound();
			}
		});
		window.getMenus().getEasyDifficultyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {	
				difficultyLevel = Computer.EASYDIFFICULTY;
				
				audioControl.updateTransitionBGMStatus(audioControl.getBackgroundMusic().isMusicPlaying());
				audioControl.getBackgroundMusic().stopMusic();
				audioControl.getBackgroundMusic().closeClip();
				audioControl.gameStartSound();
				window.getMenus().removeDifficultyMenu();
				window.getMenus().hideMainMenu();
				
				//actual game starts here
				new Game(instance, window);
				
				//reset the main menu display
				window.getMenus().showMenuBox();
			}
		});
		window.getMenus().getNormalDifficultyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				difficultyLevel = Computer.NORMALDIFFICULTY;
				
				audioControl.updateTransitionBGMStatus(audioControl.getBackgroundMusic().isMusicPlaying());
				audioControl.getBackgroundMusic().stopMusic();
				audioControl.getBackgroundMusic().closeClip();
				audioControl.gameStartSound();
				window.getMenus().removeDifficultyMenu();
				window.getMenus().hideMainMenu();
				
				//actual game starts here
				new Game(instance, window);
				
				//reset the main menu display
				window.getMenus().showMenuBox();
			}
		});
		window.getMenus().getGoBackFromNewGameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				window.getMenus().removeDifficultyMenu();
				window.getMenus().showMenuBox();
				audioControl.goBackwardSound();
			}
		});
		window.getMenus().getMusicOnButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				audioControl.getBackgroundMusic().playMusic();
				window.getMenus().getMusicOnButton().setEnabled(false);
				window.getMenus().getMusicOffButton().setEnabled(true);
				audioControl.goForwardSound();
				musicOnButtonMouseListener.setButtonEnabled(false);
				musicOffButtonMouseListener.setButtonEnabled(true);
			}
		});
		window.getMenus().getMusicOffButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				audioControl.getBackgroundMusic().stopMusic();
				window.getMenus().getMusicOffButton().setEnabled(false);
				window.getMenus().getMusicOnButton().setEnabled(true);
				audioControl.goForwardSound();
				musicOnButtonMouseListener.setButtonEnabled(true);
				musicOffButtonMouseListener.setButtonEnabled(false);
			}
		});
		window.getMenus().getSoundOnButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				audioControl.setSoundEffect(true);;
				MouseEffect.setSoundStatus(true);
				audioControl.goForwardSound();
				soundOnButtonMouseListener.setButtonEnabled(false);
				soundOffButtonMouseListener.setButtonEnabled(true);
				window.getMenus().getSoundOffButton().setEnabled(true);
				window.getMenus().getSoundOnButton().setEnabled(false);
			}
		});
		window.getMenus().getSoundOffButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				audioControl.setSoundEffect(false);
				MouseEffect.setSoundStatus(false);
				audioControl.goForwardSound();
				soundOnButtonMouseListener.setButtonEnabled(true);
				soundOffButtonMouseListener.setButtonEnabled(false);
				window.getMenus().getSoundOffButton().setEnabled(false);
				window.getMenus().getSoundOnButton().setEnabled(true);
			}
		});
		window.getMenus().getGoBackFromOptionsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				window.getMenus().removeOptionsMenu();
				window.getMenus().showMenuBox();
				audioControl.goBackwardSound();
			}
		});
		window.getMenus().getGoBackFromCreditsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				window.getMenus().removeCreditMenu();
				window.getMenus().showMenuBox();
				audioControl.goBackwardSound();
			}
		});
	
	} //addTheInitialActionListeners ends
	
	
	
} //class ends
