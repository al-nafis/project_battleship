package View;

import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Model.Ship;

/**
 * This class represents a cell for the Board
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-13-2018
 *
 */
public class BoardCell extends JButton{
	
	/**
	 * this String contains the status of the button whether it represents 'hit', 'miss', 'ship' or 'empty'
	 */
	private int status;
	
	/**
	 * updates the status (use Board's static Strings hit/miss/empty/ship)
	 * @param s String that states the JButton's status
	 */
	public void setStatus(int s) {
		status = s;
	}
	
	/**
	 * returns the status
	 * @return String that states the JButton's status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * this method sets the status variable to Board.HIT and adds the hit image to the JButton
	 */
	public void setHit() {
		status = Board.HIT;
		try {
		    setIcon(resizeIcon("src/images/hit.png", getWidth()/4*3, getHeight()/4*3));
		    setDisabledIcon(resizeIcon("src/images/hit.png", getWidth()/4*3, getHeight()/4*3));
		} catch (Exception ex) {
		    System.out.println(ex);
		    File imageCheck = new File("src/images/hit.png");

		    if(imageCheck.exists()) {
		        System.out.println("Image file found!");
		    } else {
		        System.out.println("Image file not found!");
		    }
		}
	}
	
	/**
	 * this method sets the status variable to Board.MISS and adds the miss image to the JButton
	 */
	public void setMiss() {
		status = Board.MISS;
		try {
		    setIcon(resizeIcon("src/images/miss.png", getWidth()/4*3, getHeight()/4*3));
		    setDisabledIcon(resizeIcon("src/images/miss.png", getWidth()/4*3, getHeight()/4*3));
		} catch (Exception ex) {
		    System.out.println(ex);
		    File imageCheck = new File("src/images/miss.png");

		    if(imageCheck.exists()) {
		        System.out.println("Image file found!");
		    } else {
		        System.out.println("Image file not found!");
		    }
		}
	}
	
	/**
	 * takes the String location path and uses it to add the specific image to the JButton
	 * @param path the file's location
	 */
	public void setImage(String path) {
		try {
		    setIcon(resizeIcon(path, getWidth(), getHeight()));
		    setDisabledIcon(resizeIcon(path, getWidth(), getHeight()));
		} catch (Exception ex) {
		    System.out.println(ex);
		    File imageCheck = new File(path);

		    if(imageCheck.exists()) {
		        System.out.println("Image file found!");
		    } else {
		        System.out.println("Image file not found!");
		    }
		}
	}	
	
	//overload
	/**
	 * this method takes the parameters and uses them to add the specific image to the JButton
	 * @param path the file's location
	 * @param width the width of the image
	 * @param height the height of the image
	 */
	public void setImage(String path, int width, int height) {
		try {
		    setIcon(resizeIcon(path, width, height));
		    setDisabledIcon(resizeIcon(path, width, height));
		} catch (Exception ex) {
		    System.out.println(ex);
		    File imageCheck = new File(path);

		    if(imageCheck.exists()) {
		        System.out.println("Image file found!");
		    } else {
		        System.out.println("Image file not found!");
		    }
		}
	}
	
	/**
	 * this method sets the right image on its cells based on the given parameters that it uses to locate it on the board
	 * @param ship the ship is is adding images of
	 * @param hitStatus whether it is a hit
	 * @param row Y-axis
	 * @param column X-axis
	 */
	public void setTheRightShipImage(Ship ship, boolean hitStatus, int row, int column) {
		int imageNumber = 0;
		String imageAlignment;
		String shipName;
		String hitOrNot;
		
		shipName = ship.getName();
		if (ship.getPlacingDirection() == Board.verticalInput) {
			imageAlignment = "V";
		} else {
			imageAlignment = "";
		}
		for (int i=0; i<ship.getLocation().length; i++) {
			if(ship.getLocation()[i][0] == row && ship.getLocation()[i][1] == column) {
				imageNumber = i+1;
			}
		}
		if(hitStatus) {
			hitOrNot = "Hit";
		} else {
			hitOrNot = "";
		}
		
		String path = "src/images/ships/" + shipName + "/" + shipName + imageNumber + imageAlignment + hitOrNot + ".png";
		setImage(path, 50, 50);
	}
	
	/**
	 * this static method simply resizes the image and returns it
	 * @param path location of the image
	 * @param width width of the image
	 * @param height height of the image
	 * @return resized image
	 */
	public static Icon resizeIcon(String path, int width, int height) {
		ImageIcon icon = new ImageIcon(path);
	    Image img = icon.getImage();  
	    Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);  
	    return new ImageIcon(resizedImage);
	}
	
	/**
	 * this constructor sets the JButton status and adds an image according to it
	 * @param s String that states the JButton's status
	 */
	public BoardCell(int s) {
		status = s;
	}

} //class ends