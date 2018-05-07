package Model;

import java.util.ArrayList;
import java.util.Random;

import View.Board;

/**
 * This class holds the information about the computer
 * 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-15-2018
 */
public class Computer extends Player{
	
	/**
	 * this variable holds the integer that identifies easy difficulty
	 */
	public static final int EASYDIFFICULTY = 0;
	
	/**
	 * this variable holds the integer that identifies normal difficulty
	 */
	public static final int NORMALDIFFICULTY = 1;
	
	/**
	 * this variable holds the instance of Random class
	 */
	private Random random = new Random();
	
	/**
	 * this variable holds the difficulty level identifier integer
	 */
	private int difficultyLevel;
	
	/**
	 * if the variable is true, it means computer has identified some potential targets
	 */
	private boolean targetMode;
	
	/**
	 * this arraylist contains ShotInfo instances that holds the potential target coordinates
	 */
	private ArrayList<ShotInfo> potentialTarget;
	
	/**
	 * the constructor creates an instance of Board
	 * @param gameDifficulty the difficulty indicating number
	 */
	public Computer(int gameDifficulty) {
		super("Computer");
		difficultyLevel = gameDifficulty;
		targetMode = false;
		potentialTarget = new ArrayList<ShotInfo>();
	}

	/**
	 * this method checks if the attack hit the ships or not
	 * @param pl Player instance
	 * @return boolean whether it is a hit
	 */
	public ShotInfo shootsAt(Player pl) {
		boolean hit = false;
		int inputR = 0;
		int inputC = 0;

		boolean shotsTaken = false;
		
		if(difficultyLevel == EASYDIFFICULTY) {
		
			// for Easy Difficulty
			
			
			while(!shotsTaken) {
				inputR = random.nextInt(Board.boardSize-1)+1;
				inputC = random.nextInt(Board.boardSize-1)+1;
				if(pl.getBoard().getBoardCell()[inputR][inputC].isEnabled()) {
					if(isThereAShip(pl.getBoard(), inputR, inputC)) {
						pl.getBoard().getBoardCell()[inputR][inputC].setStatus(Board.HIT);
						shotsTaken = true;
						hit = true;
					} else {
						pl.getBoard().getBoardCell()[inputR][inputC].setStatus(Board.MISS);
						shotsTaken = true;
						hit = false;
					}
				}
			} //while loop ends
			
		} else {
			
			//Normal Difficulty
			
			if(targetMode) {
				ShotInfo a = potentialTarget.get(random.nextInt(potentialTarget.size()));
				inputR = a.getRow();
				inputC = a.getColumn();
				if(pl.getBoard().getBoardCell()[a.getRow()][a.getColumn()].isEnabled()) {
					if(isThereAShip(pl.getBoard(), a.getRow(), a.getColumn())) {
						pl.getBoard().getBoardCell()[a.getRow()][a.getColumn()].setStatus(Board.HIT);
	
						
						
						//adding potential hit coordinates to the arraylist
						if(inputR != 0) {
							if(pl.getBoard().getBoardCell()[inputR-1][inputC].isEnabled()) {
								//finding if this coordinate is already exists in the arraylist
								int similar = 0;
								if(potentialTarget.size() != 0) {	
									for(ShotInfo si: potentialTarget) {
										if(si.getRow() == inputR-1 && si.getColumn() == inputC) {similar++;}
									}
								}
								if(similar == 0) {
									potentialTarget.add(new ShotInfo(true, inputR-1, inputC));
								}
							}
						}
						if(inputR != 10) {
							if(pl.getBoard().getBoardCell()[inputR+1][inputC].isEnabled()) {
								//finding if this coordinate is already exists in the arraylist
								int similar = 0;
								if(potentialTarget.size() != 0) {	
									for(ShotInfo si: potentialTarget) {
										if(si.getRow() == inputR+1 && si.getColumn() == inputC) {similar++;}
									}
								}
								if(similar == 0) {
									potentialTarget.add(new ShotInfo(true, inputR+1, inputC));
								}
							}
						}
						if(inputC != 0) {
							if(pl.getBoard().getBoardCell()[inputR][inputC-1].isEnabled()) {
								//finding if this coordinate is already exists in the arraylist
								int similar = 0;
								if(potentialTarget.size() != 0) {	
									for(ShotInfo si: potentialTarget) {
										if(si.getRow() == inputR && si.getColumn() == inputC-1) {similar++;}
									}
								}
								if(similar == 0) {
									potentialTarget.add(new ShotInfo(true, inputR, inputC-1));
								}
							}
						}
						if(inputC != 10) {
							if(pl.getBoard().getBoardCell()[inputR][inputC+1].isEnabled()) {
								//finding if this coordinate is already exists in the arraylist
								int similar = 0;
								if(potentialTarget.size() != 0) {	
									for(ShotInfo si: potentialTarget) {
										if(si.getRow() == inputR && si.getColumn() == inputC+1) {similar++;}
									}
								}
								if(similar == 0) {
									potentialTarget.add(new ShotInfo(true, inputR, inputC+1));
								}
							}
						}
						
						hit = true;
						
					} else {
						pl.getBoard().getBoardCell()[inputR][inputC].setStatus(Board.MISS);
						hit = false;
					}
				}
				potentialTarget.remove(a);
				if(potentialTarget.size() == 0) {
					targetMode = false;
				}
			} else {
				while(!shotsTaken) {
					inputR = random.nextInt(Board.boardSize-1)+1;
					inputC = random.nextInt(Board.boardSize-1)+1;
					if(pl.getBoard().getBoardCell()[inputR][inputC].isEnabled()) {
						if(isThereAShip(pl.getBoard(), inputR, inputC)) {
							pl.getBoard().getBoardCell()[inputR][inputC].setStatus(Board.HIT);
							
							//adding potential hit coordinates to the arraylist
							if(inputR != 0) {
								if(pl.getBoard().getBoardCell()[inputR-1][inputC].isEnabled()) {
									//finding if this coordinate is already exists in the arraylist
									int similar = 0;
									if(potentialTarget.size() != 0) {	
										for(ShotInfo si: potentialTarget) {
											if(si.getRow() == inputR-1 && si.getColumn() == inputC) {similar++;}
										}
									}
									if(similar == 0) {
										potentialTarget.add(new ShotInfo(true, inputR-1, inputC));
									}
								}
							}
							if(inputR != 10) {
								if(pl.getBoard().getBoardCell()[inputR+1][inputC].isEnabled()) {
									//finding if this coordinate is already exists in the arraylist
									int similar = 0;
									if(potentialTarget.size() != 0) {	
										for(ShotInfo si: potentialTarget) {
											if(si.getRow() == inputR+1 && si.getColumn() == inputC) {similar++;}
										}
									}
									if(similar == 0) {
										potentialTarget.add(new ShotInfo(true, inputR+1, inputC));
									}
								}
							}
							if(inputC != 0) {
								if(pl.getBoard().getBoardCell()[inputR][inputC-1].isEnabled()) {
									//finding if this coordinate is already exists in the arraylist
									int similar = 0;
									if(potentialTarget.size() != 0) {	
										for(ShotInfo si: potentialTarget) {
											if(si.getRow() == inputR && si.getColumn() == inputC-1) {similar++;}
										}
									}
									if(similar == 0) {
										potentialTarget.add(new ShotInfo(true, inputR, inputC-1));
									}
								}
							}
							if(inputC != 10) {
								if(pl.getBoard().getBoardCell()[inputR][inputC+1].isEnabled()) {
									//finding if this coordinate is already exists in the arraylist
									int similar = 0;
									if(potentialTarget.size() != 0) {	
										for(ShotInfo si: potentialTarget) {
											if(si.getRow() == inputR && si.getColumn() == inputC+1) {similar++;}
										}
									}
									if(similar == 0) {
										potentialTarget.add(new ShotInfo(true, inputR, inputC+1));
									}
								}
							}
							
							//testing
//							for(ShotInfo si:potentialTarget) {
//								System.out.println(si.getRow() + " " + si.getColumn());
//							}
							
							shotsTaken = true;
							hit = true;
							if(potentialTarget.size() != 0) {

								//testing
//								ShotInfo a = potentialTarget.get(random.nextInt(potentialTarget.size()));
//								System.out.println(a.getRow() + " " + a.getColumn());

								// turning on targetMode
								targetMode = true;
							}
						} else {
							pl.getBoard().getBoardCell()[inputR][inputC].setStatus(Board.MISS);
							shotsTaken = true;
							hit = false;
						}
					}
				} //while loop ends
				
			}
			
		}
		return new ShotInfo(hit, inputR, inputC);
	}
	
	
} //class ends