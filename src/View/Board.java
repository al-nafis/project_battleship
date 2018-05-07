package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.border.LineBorder;

import Model.Ship;

/**
 * This class simply holds the information about the board.
 * It will be used to create the display board on the JFrame 
 * as well as for placing ships
 * 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-11-2018
 *
 */
public class Board extends PanelWithBackground {
	/**
	 * defines both the width and the length of the board
	 */
	public static final int boardSize = 10+1; //+1 indicates the column and row names

	/**
	 * this static final variable holds an identifiable integer value
	 */
	public static final int  EMPTY = 0;
	
	/**
	 * this static final variable holds an identifiable integer value
	 */
	public static final int HIT = 1;
	
	/**
	 * this static final variable holds an identifiable integer value
	 */
	public static final int MISS = 2;
	
	/**
	 * this static final variable holds an identifiable integer value
	 */
	public static final int SHIP = 3;
	
	/**
	 * it is used as an input to place the ships horizontally on the board
	 */
	public static final int horizontalInput = 0;   //it is also used directly in ship class, thus it is a static
	
	/**
	 * it is used as an input to place the ships vertically on the board
	 */
	public static final int verticalInput = 1; 	//it is also used directly in ship class, thus it is a static

	/**
	 * creates an array of board holding the color values
	 */
	private BoardCell[][] cell;
	
	/**
	 * this method returns the board array
	 * @return cell
	 */
	public BoardCell[][] getBoardCell() {
		return cell;
	}
	
	/**
	 * this is the constructor for the Board class.
	 * It constructs the board in a way that will be easier to display
	 */
	public Board() {
		super("src/images/boardImage.jpg");
		cell = new BoardCell[boardSize][boardSize];
		this.setLayout(new GridLayout(boardSize,boardSize));
		
		
		//setup for board display
		for(int a=0; a<boardSize; a++) {
			for(int b=0; b<boardSize; b++) {
				cell[a][b] = new BoardCell(EMPTY);
				cell[a][b].setContentAreaFilled(false);
				cell[a][b].setBorder(new LineBorder(Color.gray));
				cell[a][b].setFocusable(false);
				this.add(cell[a][b]);
			}
		}
		
		//board row name update
		char row = 'A';
		for(int b=1; b<boardSize; b++) {
			cell[b][0].setText(Character.toString(row));
			row++;
			cell[b][0].setEnabled(false);
			cell[b][0].setContentAreaFilled(false);
			cell[b][0].setBorderPainted(false);
			cell[b][0].setOpaque(true);
			cell[b][0].setBackground(Color.DARK_GRAY);
		}
		
		//board column name update
		int column = 1;
		for(int b=1; b<boardSize; b++) {
			cell[0][b].setText(column+"");
			column++;
			cell[0][b].setEnabled(false);
			cell[0][b].setContentAreaFilled(false);
			cell[0][b].setBorderPainted(false);
			cell[0][b].setOpaque(true);
			cell[0][b].setBackground(Color.DARK_GRAY);
		}
		
		//cells[0][0] content remove
		cell[0][0].setText("");
		cell[0][0].setEnabled(false);
		cell[0][0].setContentAreaFilled(false);
		cell[0][0].setBorderPainted(false);
		cell[0][0].setOpaque(true);
		cell[0][0].setBackground(Color.DARK_GRAY);		
	}//constructor ends
	
	/**
	 * this method checks whether a particular ship can be fit at a specific coordinate in the board
	 * @param ship The ship instance that needs to be placed
	 * @param row row number of the board
	 * @param column column number of the board
	 * @param placingDirection either horizontal or vertical
	 * @return boolean returns true if the ship fits in the board
	 */
	public static boolean checkPlacementValidity(Ship ship, int row, int column, int placingDirection) {
		int rowOnTheBottom = 0;
		int colOnTheRight = 0;
		
		if(placingDirection == verticalInput) {
			for(int i = row; i<boardSize; i++) { 
				rowOnTheBottom++;
			}
			if(rowOnTheBottom >= ship.getLength()) { 
				return true;
			}
		} else { 	//accepts only 2 inputs: 'v' or 'h'
			for(int i = column; i<boardSize; i++) { 
				colOnTheRight++;
			}
			if(colOnTheRight >= ship.getLength()) {
				return true;
			}
		}
		return false;
	} //checkPlacementValidity ends
	
	/**
	 * this method checks if the indicated ship collides with any other ships at the given coordinates. 
	 * @param ship The ship instance that needs to be placed
	 * @param row row number of the board
	 * @param column column number of the board
	 * @param placingDirection either horizontal or vertical
	 * @return boolean returns true if the ship collides with other ships
	 */
	public boolean checkShipCollision(Ship ship, int row, int column, int placingDirection) {
		if(placingDirection == Board.verticalInput) {
			for(int i = 0; i<ship.getLength(); i++) { 
				if(cell[row+i][column].getStatus() == Board.SHIP) {
					return true;
				}
			}
		} else {
			for(int i = 0; i<ship.getLength(); i++) { 
				if(cell[row][column+i].getStatus() == Board.SHIP) {
					return true;
				}
			}
		}
		return false;
	}//checkShipCollision ends
	
	/**
	 * this static method returns a readable coordinate based on the board from the given integers
	 * @param r Y-axis
	 * @param c X-axis
	 * @return String visually understandable output
	 */
	public static final String getCoordinate(int r, int c) {
		char ch = 'A';
		String row = "";
		String column = Integer.toString(c);
		for(int b=1; b<boardSize; b++) {
			if(r==b) {
				row = Character.toString(ch);
			}
			ch++;
		}
		
		return row+column;
	}
	
} //class ends