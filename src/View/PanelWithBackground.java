package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This class is used to draw an image to a JPanel as a background
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-04-2018
 *
 */
public class PanelWithBackground extends JPanel{
	
	/**
	 * this variable holds an instance of Image class
	 */
	private Image image;
	
	/**
	 * this is the constructor 
	 * @param imagePath location of the image
	 */
	public PanelWithBackground(String imagePath) {
	  this.image = new ImageIcon(imagePath).getImage();
	  Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
	  setPreferredSize(size);
	  setMinimumSize(size);
	  setMaximumSize(size);
	  setSize(size);
	  setLayout(null);
	}
	
	/**
	 * this method draws the image
	 */
	public void paintComponent(Graphics g) {
	  g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
} //class ends
