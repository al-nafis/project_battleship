package Model;

import java.util.Random;

import View.Board;

/**
 * this is an abstract parent class for User and Computer classes
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-15-2018
 *
 */
public abstract class Player {
	
	
	/**
	 * this property defines how  many ships will be in the game
	 */
	public static final int totalNumberOfShips = 5; 

	/**
	 * contains the number of total shot taken by the player
	 */
	private int totalShotsTaken = 0;
	
	/**
	 * this stores a random value
	 */
	Random rnd = new Random();
	
	
	/**
	 * holds the name of the Player
	 */
	private String name;
	
	
	/**
	 * this creates the board which will be displayed in the JFrame where user will play the game
	 */
	private Board board;
	
	/**
	 * this is an array that contains the instances of ship class
	 */
	private Ship[]  shipArray;
	
	/**
	 * it represents the remaining alive ships on the board
	 */
	private int shipsRemaining;
	
	/**
	 * returns the total amount of shots taken by the player
	 * @return totalShotsTaken
	 */
	public int getTotalShotsTaken() {
		return totalShotsTaken;
	}
	
	/**
	 * this method increments the variable by one
	 */
	public void incrementTotalShotsTaken() {
		totalShotsTaken++;
	}
	
	/**
	 * this method returns the value of name property
	 * @return name name of the  player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * this method returns the board, an instance of the Board class
	 * @return board 
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * this method returns the ship array
	 * @return shipArray[]
	 */
	public Ship[] getShipArray() {
		return shipArray;
	}
	
	/**
	 * this method returns the number of ships that are alive on the board
	 * @return shipsRemaining
	 */
	public int getShipsRemaining() {
		return shipsRemaining;
	}
	
	/**
	 * this method decreases the value of shipsRemaining by 1
	 */
	public void loseShip(){
		shipsRemaining--;
	}
	
	/**
	 * this constructor assigns the name property to the given parameter 
	 * @param n name of the Player
	 */
	public Player(String n) {
		this.name = n;
		board = new Board();
		shipArray = new Ship[totalNumberOfShips];
		shipsRemaining = totalNumberOfShips;
		
		// initializing the ships
		shipArray[0] = new Ship("Destroyer", 2);
		shipArray[1] = new Ship("Submarine", 3);
		shipArray[2] = new Ship("Cruiser", 3);
		shipArray[3] = new Ship("Battleship", 4);
		shipArray[4] = new Ship("Carrier", 5);		
	}
	
	/**
	 * this method takes a Ship instance and place it on the this class's board array
	 * @param shp Ship
	 */
	public void randomlyPlaceShip(Ship shp) {
		
		
		int inputR;
		int inputC;
		int placingDirection;
		
		boolean correct = false;
		int shipLocationCount;
		
		//while loop starts here

		shipLocationCount = 0;
		while(!correct) {
			
			//getting random board row and column value
			//inside the parenthesis, it is picking a number between 0 to the size of the board
			//excluding the rows and columns for the row and column names
			//and outside, it is making it from 1 to 10 by adding 1
			inputR = rnd.nextInt(Board.boardSize-1)+1; 
			inputC = rnd.nextInt(Board.boardSize-1)+1; 
			
			//getting random values to determine ships horizontal or vertical placement
			if(rnd.nextInt(2) == 0) {
				placingDirection = Board.horizontalInput;
			} else {
				placingDirection = Board.verticalInput;
			}
			
			if(Board.checkPlacementValidity(shp, inputR, inputC, placingDirection)) {		
				
				if(!board.checkShipCollision(shp, inputR, inputC, placingDirection)) {
					
					if(placingDirection == Board.verticalInput) {
						for(int i = 0; i<shp.getLength(); i++) {
							shp.setLocation(inputR+i, inputC, shipLocationCount);
							board.getBoardCell()[inputR+i][inputC].setStatus(Board.SHIP);
							shipLocationCount++;
						}
						shp.setPlacingDirection(placingDirection);
						shp.setIsPlacedOnBoard(true);
						
						//testing
//						System.out.println(shp.getName() + " is set at " + Board.getCoordinate(inputR, inputC) + " Placing Direction: " + placingDirection);

					} else {
						
						for(int i = 0; i<shp.getLength(); i++) {
							shp.setLocation(inputR, inputC+i, shipLocationCount);
							board.getBoardCell()[inputR][inputC+i].setStatus(Board.SHIP);
							shipLocationCount++;
						}
						shp.setPlacingDirection(placingDirection);
						shp.setIsPlacedOnBoard(true);
						
						//testing
//						System.out.println(shp.getName() + " is set at " + Board.getCoordinate(inputR, inputC) + " Placing Direction: " + placingDirection);

					}
					correct = true;
				
				} else {
					//testing
//					System.out.println(shp.getName() + " collides with one or more ships at " + Board.getCoordinate(inputR, inputC) + " Placing Direction: " + placingDirection);
				} //checkShipCollision - if statement ends
			
			} else {
				//testing
//				System.out.println(shp.getName() + " cannot fit at " + Board.getCoordinate(inputR, inputC) + " Placing Direction: " + placingDirection);
			} // checkPlacementValidity - if statement ends
			
		}//while loop ends
		
	} //placeShips method ends
	
	/**
	 * this method returns the ship that was hit
	 * @param player Player instance
	 * @param row row number
	 * @param column column number
	 * @return Ship Ship instance that matches with the given coordinates
	 */
	public Ship shipThatHasBeenHit(Player player, int row, int column) {
		Ship shipIdentifier = null;
		for(Ship s:player.shipArray) {
			for(int a=0; a<s.getLocation().length;a++) {
				if(s.getLocation()[a][0] == row && s.getLocation()[a][1] == column) {
					shipIdentifier = s;
				}
			}
		}	
		return shipIdentifier;
	}//shipThatHasBeenHit ends
	
	/**
	 * this method checks if there is a ship at a specific location on the board
	 * @param b board
	 * @param r row
	 * @param c column
	 * @return boolean true means there is a ship
	 */
	public boolean isThereAShip(Board b, int r, int c) {
		if(b.getBoardCell()[r][c].getStatus() == Board.SHIP) {
			return true;
		}
		return false;
	}
	
} //class ends
