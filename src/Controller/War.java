package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Computer;
import Model.Player;
import Model.Ship;
import Model.ShotInfo;
import Model.User;
import View.Board;
import View.BoardCell;
import View.GameWindow;
import View.MainFrame;
import View.PauseMenu;
import View.WarWindow;

/**
 * this class controls the main core part of the game where user and computer attacks on each other
 * this class holds the JButton action listeners
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-21-2018
 *
 */
public class War {

	/**
	 * this variable holds the instance of the MainFrame class
	 */
	private MainFrame window;
	
	/**
	 * this variable holds the instance of the MainController class
	 */
	private MainController controller;
	
	/**
	 * this variable contains the instance for the User class
	 */
	private User user;
	
	/**
	 * this variable contains the instance for the Computer class
	 */
	private Computer pc;
	
	/**
	 * this variable holds the instance for MouseEffect class
	 */
	private MouseEffect cellMouseEffect;
	
	/**
	 * this variable holds the instance of WarWindow class
	 */
	private WarWindow warWindow;
	
	/**
	 * this ActionListener class instance is used to add effect on a specific button
	 */
	private ActionListener forPause;
	
	/**
	 * this ActionListener class instance is used to add effect on a specific button
	 */
	private ActionListener forQuit;
	
	/**
	 * this variable holds a boolean that states whether the game is over or not
	 */
	private boolean gameOver = false;
	
	/**
	 * this method returns the WarWindow instance
	 * @return warWindow holds the JPanel to show the game contents
	 */
	public WarWindow getWarWindow() {
		return warWindow;
	}
	
	/**
	 * this constructor uses the parameters to assign them in local variables
	 * @param c MainController instance to get access to its contents
	 * @param f MainFrame instance to get access to its contents
	 * @param g Game instance to get access to its contents
	 * @param gw GameWindow instance to get access to its contents
	 * @param u User instance to get access to its contents
	 */
	public War(MainController c, MainFrame f, Game g, GameWindow gw, User u) {
		window = f;
		controller = c;
		user = u;
		pc = new Computer(controller.getDifficultyLevel());
		for(int i=0; i<Player.totalNumberOfShips; i++) {
			pc.randomlyPlaceShip(pc.getShipArray()[i]);
		}
		warWindow = new WarWindow(window, user, pc);
		
		
		for(int a=1; a<Board.boardSize; a++) {
			for(int b=1; b<Board.boardSize; b++) {
				//adding mouse listeners to the board in planning panel
				cellMouseEffect = new MouseEffect(pc.getBoard().getBoardCell()[a][b], MouseEffect.ForBoardCell, controller);
				pc.getBoard().getBoardCell()[a][b].addMouseListener(cellMouseEffect);
			}
		}
		
		//initializing ActionListeners
		forPause = new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				controller.getAudioControl().goForwardSound();
				warWindow.getWarPanel().setVisible(false);
				window.remove(warWindow.getWarPanel());
				
				//adding a pause menu to the planning panel
				PauseMenu pauseMenu = new PauseMenu(window);
				new PauseMenuActionListener(pauseMenu, controller, window, warWindow.getWarPanel());
			}
		};
		
		forQuit = new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				controller.getAudioControl().goForwardSound();
				warWindow.getWarPanel().setVisible(false);
				window.remove(warWindow.getWarPanel());
				
				window.add(window.getMenus().getMainMenu());
				window.getMenus().showMainMenu();
				controller.getAudioControl().updateTransitionBGMStatus(controller.getAudioControl().getBackgroundMusic().isMusicPlaying());
				controller.getAudioControl().getBackgroundMusic().stopMusic();
				controller.getAudioControl().getBackgroundMusic().closeClip();
				controller.getAudioControl().playMenuBackgroundMusic();
			}
		};
		
		//adding mouse listener
		warWindow.getMenuButton().addMouseListener(new MouseEffect(warWindow.getMenuButton(), MouseEffect.ForMenuButton, controller));
		//adding button listener
		warWindow.getMenuButton().addActionListener(forPause); 


		addGameBoardListener();
		
	} //constructor ends
	
	
	/**
	 * this method adds the actionListeners for each MenuButton instance in the Board
	 */
	public void addGameBoardListener() {		
		for(int a=1; a<Board.boardSize; a++) {
			for(int b=1; b<Board.boardSize; b++) {
				int x = a;
				int y = b;
				pc.getBoard().getBoardCell()[a][b].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								
						// empty by default
						warWindow.setUpdate("");
						Ship targetShip;
						
								//user attack here
								boolean isHit = user.shootsAt(pc, x, y);
								if(isHit) {

									targetShip = pc.shipThatHasBeenHit(pc, x, y); //returns opponent's ship
									controller.getAudioControl().cellHitSound();
									pc.getBoard().getBoardCell()[x][y].setHit();
									pc.getBoard().getBoardCell()[x][y].setEnabled(false);
									user.incrementTotalShotsTaken();
									
									//updating stats
									warWindow.updateUserLastShot(Board.getCoordinate(x, y));
									warWindow.updateUserTotalShot(user.getTotalShotsTaken());
									

									//disabling the buttons so that user can't click on them during computer's turn
									ArrayList<BoardCell> remaining = new ArrayList<BoardCell>();
									for(int a=1; a<Board.boardSize; a++) {
										for(int b=1; b<Board.boardSize; b++) {
											if(pc.getBoard().getBoardCell()[a][b].isEnabled()) {
												remaining.add(pc.getBoard().getBoardCell()[a][b]);
												pc.getBoard().getBoardCell()[a][b].setEnabled(false);
											}
										}
									}
									
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
									
									targetShip.loseHealth();
									if(targetShip.getHealth() <= 0) {
										
										controller.getAudioControl().shipDestroyingSound();
										pc.loseShip();
										//updating board and enemy's fleet on the top right corner panel
										if(targetShip.getPlacingDirection() == Board.verticalInput) {
											for(int q=0; q<targetShip.getLocation().length; q++) {
												pc.getBoard().getBoardCell()[targetShip.getLocation()[q][0]][targetShip.getLocation()[q][1]].setImage("src/images/ships/" + targetShip.getName() + "/" + targetShip.getName() + (q+1) + "VHit.png");
												targetShip.addVerticalShipImages(true, 60, 38);
											}
										} else {
											for(int q=0; q<targetShip.getLocation().length; q++) {
												pc.getBoard().getBoardCell()[targetShip.getLocation()[q][0]][targetShip.getLocation()[q][1]].setImage("src/images/ships/" + targetShip.getName() + "/" + targetShip.getName() + (q+1) + "Hit.png");
												targetShip.addVerticalShipImages(true, 60, 38);
											}
										}
										//updating the updateLabel
										warWindow.setUpdate("You have destroyed enemy's " + targetShip.getName());
										
										
										//waiting until sound effect is on
										try {
											Thread.sleep(1100);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
										//checking whether user has won the game
										if(pc.getShipsRemaining() < 1) {
											
											for(int a=1; a<Board.boardSize; a++) {
												for(int b=1; b<Board.boardSize; b++) {
													pc.getBoard().getBoardCell()[a][b].setEnabled(false);
												}
											}
											
											gameOver = true;
											
											try {
												Thread.sleep(500);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											
											warWindow.setUserWinner();
											controller.getAudioControl().getClip().stop();
											controller.getAudioControl().playWinnerSound();

											//add menubutton modifications
											warWindow.getMenuButton().removeActionListener(forPause);
											warWindow.getMenuButton().setText("Quit");
											warWindow.getMenuButton().addActionListener(forQuit); 
										}										
									}
									//enabling the buttons for users to interact
									if(!gameOver) {
										for(int j=0; j<remaining.size(); j++) {
											remaining.get(j).setEnabled(true);
										}
									}
									
								} else {
									controller.getAudioControl().cellMissSound();
									pc.getBoard().getBoardCell()[x][y].setMiss();	
									pc.getBoard().getBoardCell()[x][y].setEnabled(false);
									user.incrementTotalShotsTaken();;
									
									//disabling the buttons so that user can't click on them during computer's turn
									ArrayList<BoardCell> remaining = new ArrayList<BoardCell>();
									for(int a=1; a<Board.boardSize; a++) {
										for(int b=1; b<Board.boardSize; b++) {
											if(pc.getBoard().getBoardCell()[a][b].isEnabled()) {
												remaining.add(pc.getBoard().getBoardCell()[a][b]);
												pc.getBoard().getBoardCell()[a][b].setEnabled(false);
											}
										}
									}
									
									//updating stats
									warWindow.updateUserLastShot(Board.getCoordinate(x, y));
									warWindow.updateUserTotalShot(user.getTotalShotsTaken());	

									
									
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
									/////////////////////////
									// pc attack from here //
									/////////////////////////
									
									
									
									ShotInfo pcShotInfo;
									boolean pcHit = true;
								
									while(pcHit) {
										// empty by default
										warWindow.setUpdate("");
										
										pcShotInfo = pc.shootsAt(user);
										
										if(pcShotInfo.getHitStatus()) {
											
											targetShip = user.shipThatHasBeenHit(user, pcShotInfo.getRow(), pcShotInfo.getColumn());
											controller.getAudioControl().cellHitSound();
											user.getBoard().getBoardCell()[pcShotInfo.getRow()][pcShotInfo.getColumn()].setTheRightShipImage(targetShip, pcShotInfo.getHitStatus(), pcShotInfo.getRow(), pcShotInfo.getColumn());
											user.getBoard().getBoardCell()[pcShotInfo.getRow()][pcShotInfo.getColumn()].setEnabled(false);
											pc.incrementTotalShotsTaken();;
											
											//updating stats
											warWindow.updatePcLastShot(Board.getCoordinate(pcShotInfo.getRow(), pcShotInfo.getColumn()));
											warWindow.updatePcTotalShot(pc.getTotalShotsTaken());
											
											try {
												Thread.sleep(400);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											
											targetShip.loseHealth();
											//check if a ship is destroyed
											if(targetShip.getHealth() <= 0) {
								
												controller.getAudioControl().shipDestroyingSound();
												user.loseShip();
												//updating the updateLabel
												warWindow.setUpdate("Enemy has destroyed your " + targetShip.getName());
											
												//waiting until sound effect is on
												try {
													Thread.sleep(1100);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												
												//checking whether computer has won the game
												if(user.getShipsRemaining() < 1) {
													for(int a=1; a<Board.boardSize; a++) {
														for(int b=1; b<Board.boardSize; b++) {
															pc.getBoard().getBoardCell()[a][b].setEnabled(false);
														}
													}
													gameOver = true;
													pcHit = false;
													warWindow.setPcWinner();
													controller.getAudioControl().getClip().stop();
													controller.getAudioControl().playWinnerSound();
													
													//add menubutton modifications
													warWindow.getMenuButton().removeActionListener(forPause);
													warWindow.getMenuButton().setText("Quit");
													warWindow.getMenuButton().addActionListener(forQuit); 
												}
											}
											
											try {
												Thread.sleep(400);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											
											
										} else {
											controller.getAudioControl().cellMissSound();
											user.getBoard().getBoardCell()[pcShotInfo.getRow()][pcShotInfo.getColumn()].setMiss();
											user.getBoard().getBoardCell()[pcShotInfo.getRow()][pcShotInfo.getColumn()].setEnabled(false);
											pc.incrementTotalShotsTaken();;
											
											//updating stats
											warWindow.updatePcLastShot(Board.getCoordinate(pcShotInfo.getRow(), pcShotInfo.getColumn()));
											warWindow.updatePcTotalShot(pc.getTotalShotsTaken());
											pcHit = false;
										}
										
										//enabling the buttons for users to interact
										if(!gameOver) {
											for(int j=0; j<remaining.size(); j++) {
												remaining.get(j).setEnabled(true);
											}
										}
	
									}
								}
							} //Thread.run ends
						}).start(); //Thread ends
					}
				});
			}
		}
		
	} //addGameBoardListener ends
	

	
} //class ends