package Model;

import View.Board;

/**
 * This class holds the information about the gamer
 * 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-15-2018
 */
public class User extends Player{

	/**
	 * the constructor creates an instance of Board
	 */
	public User() {
		super("User");
		
	}
	
	/**
	 * this method checks if the attack hit the ships or not
	 * @param pl Player instance
	 * @param row row number
	 * @param column column number
	 * @return boolean hit or miss
	 */
	public boolean shootsAt(Player pl, int row, int column) {		
		if(isThereAShip(pl.getBoard(), row, column)) {
			pl.getBoard().getBoardCell()[row][column].setStatus(Board.HIT);
			return true;
		} else {
			pl.getBoard().getBoardCell()[row][column].setStatus(Board.MISS);
			return false;
		}
	}
	
} //class ends
