package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Player;
import Model.Ship;
import Model.User;
import View.Board;
import View.GameWindow;
import View.MainFrame;
import View.PauseMenu;

/**
 * this class is the controller for the actual game section
 * 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-08-2018
 */
public class Game {
	
	
	
	/**
	 * this variable contains the MainController object
	 */
	private MainController controller;
	
	/**
	 * this variable contains the MainWindow object
	 */
	private MainFrame window;
	
	/**
	 * this variable creates an instance for the main game section
	 */
	private GameWindow gameWindow;
	
	/**
	 * this variable holds an instance of the User class
	 */
	private User user;
	
	/**
	 * this method returns the User instance
	 * @return user instance of User class
	 */
	public Player getUser() {
		return user;
	}
	
	/**
	 * this is the constructor
	 * @param c instance of the MainController that is used here mostly for getting the audio control
	 * @param w instance of the MainFrame that is used to get access to the JFrame, that is used to display the whole application
	 */
	public Game(MainController c, MainFrame w) {
		this.window = w;
		this.controller = c;
		user = new User();
		gameWindow = new GameWindow(window, user);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int a=1; a<Board.boardSize; a++) {
					for(int b=1; b<Board.boardSize; b++) {
						// the waiting game with a condition in a while loop
						while(user.getBoard().getBoardCell()[a][b] == null) {try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();};}
					}
				}
				// the waiting game with a condition in a while loop
				while(!window.isAncestorOf(gameWindow.getPlanningPanel())) {try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();};}
				controller.getAudioControl().playInGameBackgroundMusic();
				
				
				
			}	
		}).start(); //Thread ends
		
		//adding mouse listeners to the button on the shipStation
		gameWindow.getMenuButton().addMouseListener(new MouseEffect(gameWindow.getMenuButton(), MouseEffect.ForMenuButton, controller));
		gameWindow.getPlaceButton().addMouseListener(new MouseEffect(gameWindow.getPlaceButton(), MouseEffect.ForMenuButton, controller));
		MouseEffect fightButtonMouseEffect = new MouseEffect(gameWindow.getFightButton(), MouseEffect.ForMenuButton, controller);
		gameWindow.getFightButton().addMouseListener(fightButtonMouseEffect);
		gameWindow.getFightButton().setEnabled(false);
		fightButtonMouseEffect.setButtonEnabled(gameWindow.getFightButton().isEnabled());
		
		//adding button listeners
		gameWindow.getMenuButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				controller.getAudioControl().goForwardSound();
				gameWindow.getPlanningPanel().setVisible(false);
				window.remove(gameWindow.getPlanningPanel());
				
				//adding a pause menu to the planning panel
				PauseMenu pauseMenu = new PauseMenu(window);
				new PauseMenuActionListener(pauseMenu, controller, window, gameWindow.getPlanningPanel());
			}
		}); // menuButton actionListener adding ends
		
		gameWindow.getPlaceButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						gameWindow.getPlaceButton().setEnabled(false);
						controller.getAudioControl().goForwardSound();
				
						for(Ship ship : user.getShipArray()) {
							if(ship.isPlacedOnBoard()) {
								ship.setIsPlacedOnBoard(false);
								ship.createNewLocationInstance();
								
								for(int a=0; a<Board.boardSize; a++) {
									for(int b=0; b<Board.boardSize; b++) {
										user.getBoard().getBoardCell()[a][b].setStatus(Board.EMPTY);
										user.getBoard().getBoardCell()[a][b].setIcon(null);
									}
								}
							}
						}

				
						for(int i=0; i<Player.totalNumberOfShips; i++) {
							user.randomlyPlaceShip(user.getShipArray()[i]);
							
							//setting up the ship images for the defenseBoard
							String plDirec;
							if(user.getShipArray()[i].getPlacingDirection() == Board.verticalInput) {plDirec = "V";} else {plDirec = "";}
							for(int j=0; j<user.getShipArray()[i].getLength(); j++) {
								user.getBoard().getBoardCell()[user.getShipArray()[i].getLocation()[j][0]][user.getShipArray()[i].getLocation()[j][1]].setImage("src/images/ships/" + user.getShipArray()[i].getName() + "/" + user.getShipArray()[i].getName() + (j+1) + plDirec +".png");
								
							}
						}
				
				
						if(!gameWindow.getFightButton().isEnabled()) {
							gameWindow.getFightButton().setEnabled(true);
							fightButtonMouseEffect.setButtonEnabled(gameWindow.getFightButton().isEnabled());
						}
				
						try {
							Thread.sleep(500);			
							gameWindow.getPlaceButton().setEnabled(true);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}).start();
				
			}
		}); // placeButton actionListener adding ends
		
		gameWindow.getFightButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				controller.getAudioControl().goForwardSound();
				gameWindow.getPlanningPanel().remove(user.getBoard());
				gameWindow.getPlanningPanel().setVisible(false);
				window.remove(gameWindow.getPlanningPanel());
				
				new War(controller, window, controller.getGame(), gameWindow, user);
			}
		}); // fightButton actionListener adding ends
		
		
	} // constructor ends
	
} //class ends
