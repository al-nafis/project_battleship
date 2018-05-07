package Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JPanel;

import View.Board;
import View.BoardCell;

/**
 * this class represents a ship 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-17-2018
 *
 */
public class Ship {

	/**
	 * this property holds the name of the ship
	 */
	private String name;
	
	/**
	 * this property holds the length of the ship
	 */
	private int length;
	
	/**
	 * it creates an array of strings that has the location coordinates of the ship on the board
	 */
	private int[][] location;
	
	/**
	 * this defines the health or life points for the ship
	 */
	private int health;
	
	/**
	 * this variable contains a boolean that states whether this instance of Ship is placed in a Board
	 */
	private boolean placedOnBoard = false;
	
	/**
	 * this variable holds the number that defines the placing direction of the ship
	 */
	private int placingDirection;
	
	/**
	 * this setter method sets the value for placedOnBoard variable
	 * @param b true means it is placed on board
	 */
	public void setIsPlacedOnBoard(boolean b) {
		placedOnBoard = b;
	}
	
	/**
	 * this setter method sets the value for placingDirection variable
	 * @param n vertical or horizontally
	 */
	public void setPlacingDirection(int n) {
		placingDirection = n;
	}
	
	/**
	 * this method returns the boolean value for placedOnBoard
	 * @return true means the ship is placed on board
	 */
	public boolean isPlacedOnBoard() {
		return placedOnBoard;
	}
	
	/**
	 * this method returns the value for placingDirection
	 * @return placingDirection
	 */
	public int getPlacingDirection() {
		return placingDirection;
	}
	
	/**
	 * this method returns the value of property "name" of the ship instance	
	 * @return name the ship's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * this method return the length of the ship
	 * @return length the ship's length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * this method returns the array
	 * @return location 
	 */
	public int[][] getLocation() {
		return location;
	}
	
	/**
	 * creates a JPanel where the JButtons will be added
	 */
	private JPanel panel;
	
	/**
	 * returns the JPanel
	 * @return JPanel
	 */
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * creates an array that contains BoardCell instances
	 */
	private BoardCell cell[];
	
	/**
	 * returns BoardCell array
	 * @return cell
	 */
	public BoardCell[] getCell() {
		return cell;
	}
	
	/**
	 * this method returns health property
	 * @return health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * this method decreases the ships health property by 1
	 */
	public void loseHealth() {
		health--;
	}
	
	
	/**
	 * this method stores the location coordinates to the location array
	 * @param row row location
	 * @param column column location
	 * @param index indicates one cell of total length of the ship
	 */
	public void setLocation(int row, int column, int index) {
		this.location[index][0] = row;
		this.location[index][1] = column;
	}
	
	/**
	 * this method creates a new integer multidimensional array
	 */
	public void createNewLocationInstance() {
		this.location = new int[length][2];
	}

	/**
	 * this constructor creates an instance of Ship class and assigns the properties to the given values
	 * @param name name of the ship
	 * @param length length of the ship
	 */
	public Ship(String name, int length) {
		this.name = name;
		this.length = length;
		this.location = new int[length][2];
		this.health = length;
		cell = new BoardCell[length];
		panel = new JPanel();
		shipHorizontalViewLayout();
		panel.setBackground(Color.RED);
		panel.setOpaque(false);
		for (int i=0; i<cell.length; i++) {
			cell[i] = new BoardCell(Board.EMPTY);
			cell[i].setFocusable(false);
			cell[i].setContentAreaFilled(false);
			cell[i].setBorderPainted(false);
			cell[i].setOpaque(false);
			cell[i].setPreferredSize(new Dimension(90, 90));
			panel.add(cell[i]);
		}
		addHorizontalShipImages(false, 90, 90);
	} //constructor ends
	
	/**
	 * this method creates a horizontal layout to the panel
	 */
	public void shipHorizontalViewLayout() {
		panel.setLayout(new GridLayout(1, length));
	}	
	
	/**
	 * this method creates a vertical layout to the panel
	 */
	public void shipVerticalViewLayout() {
		panel.setLayout(new GridLayout(length, 1));
	}
	
	/**
	 * this method adds horizontal images to the buttons in panel
	 * @param hitStatus if the button is hit then gets different image with hit sign on it
	 * @param width width of the image
	 * @param height height of the image
	 */
	public void addHorizontalShipImages(boolean hitStatus, int width, int height) {
		for (int i=1; i<cell.length+1; i++) {
			if(hitStatus) {
				try {
					cell[i-1].setIcon(BoardCell.resizeIcon("src/images/ships/" + name.toLowerCase() + "/" + name.toLowerCase() + i + "Hit.png", width, height));
				} catch (Exception ex) {
				    System.out.println(ex);
				    File imageCheck = new File("src/images/ships/" + name.toLowerCase() + "/" + name.toLowerCase() + i + "Hit.png");

				    if(imageCheck.exists()) {
				        System.out.println("Image file found!");
				    } else {
				        System.out.println("Image file not found!");
				    }
				}
				
			} else {
				try {
					cell[i-1].setIcon(BoardCell.resizeIcon("src/images/ships/" + name.toLowerCase() + "/" + name.toLowerCase() + i + ".png", width, height));
				} catch (Exception ex) {
				    System.out.println(ex);
				    File imageCheck = new File("src/images/ships/" + name.toLowerCase() + "/" + name.toLowerCase() + i + ".png");

				    if(imageCheck.exists()) {
				        System.out.println("Image file found!");
				    } else {
				        System.out.println("Image file not found!");
				    }
				}
			}
		}
	}

	/**
	 * this method adds vertical images to the buttons in panel
	 * @param hitStatus if the button is hit then gets different image with hit sign on it
	 * @param width width of the image
	 * @param height height of the image
	 */
	public void addVerticalShipImages(boolean hitStatus, int width, int height) {
		for (int i=1; i<cell.length+1; i++) {
			if(hitStatus) {
				try {
					cell[i-1].setIcon(BoardCell.resizeIcon("src/images/ships/" + name.toLowerCase() + "/" + name.toLowerCase() + i + "VHit.png", width, height));
				} catch (Exception ex) {
				    System.out.println(ex);
				    File imageCheck = new File("src/images/ships/" + name.toLowerCase() + "/" + name.toLowerCase() + i + "VHit.png");

				    if(imageCheck.exists()) {
				        System.out.println("Image file found!");
				    } else {
				        System.out.println("Image file not found!");
				    }
				}	
			} else {
				try {
					cell[i-1].setIcon(BoardCell.resizeIcon("src/images/ships/" + name.toLowerCase() + "/" + name.toLowerCase() + i + "V.png", width, height));
				} catch (Exception ex) {
				    System.out.println(ex);
				    File imageCheck = new File("src/images/ships/" + name.toLowerCase() + "/" + name.toLowerCase() + i + "V.png");

				    if(imageCheck.exists()) {
				        System.out.println("Image file found!");
				    } else {
				        System.out.println("Image file not found!");
				    }
				}
			}
		}
	}
	
} //class ends
