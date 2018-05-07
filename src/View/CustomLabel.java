package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CustomLabel extends JLabel{

	/**
	 * this static final integer variable holds a specific value that identifies an alignment
	 */
	public static final int CENTER = 0;
	
	/**
	 * this static final integer variable holds a specific value that identifies an alignment
	 */
	public static final int TOP = 1;
	
	/**
	 * this static final integer variable holds a specific value that identifies an alignment
	 */
	public static final int LEFT = 2;
	
	/**
	 * this static final integer variable holds a specific value that identifies an alignment
	 */
	public static final int BOTTOM = 3;

	/**
	 * this static final integer variable holds a specific value that identifies an alignment
	 */
	public static final int RIGHT = 4;
	
	/**
	 * this variable holds the alignment value for the button texts
	 */
	private int textAlign;
	
	/**
	 * this variable holds a Color instance for updating the button text color
	 */
	private Color textColor;
	
	/**
	 * this variable holds a Font instance for updating the button text font
	 */
	private Font textFont;
	
	/**
	 * this is the constructor for the class
	 * @param name display name for the button
	 * @param align alignment for the text on the button(use static variables from this class)
	 */
	public CustomLabel(String name, int align) {
		super(name);
		setOpaque(false);
		setFocusable(false);
		
		textAlign = align;
		setAlignmentdirection(textAlign);
		textFont = new Font("Impact", Font.PLAIN, 40);
		setFont(textFont);
		textColor = new Color(204, 0, 0);
		setForeground(textColor);
	}
	
	/**
	 * this is the constructor for the class
	 * @param name display name for the button
	 * @param align alignment for the text on the button
	 * @param color color for the button text
	 * @param font font for the button text
	 */
	public CustomLabel(String name, int align, Color color, Font font) {
		super(name);
		setOpaque(false);
		//setContentAreaFilled(false);
		//setBorderPainted(false);
		setFocusable(false);
		
		textAlign = align;
		setAlignmentdirection(textAlign);
		
		if(font == null) {
			this.textFont = new Font("Impact", Font.PLAIN, 40);
		} else {
			textFont = font;	
		}
		setFont(textFont);
		
		if(color == null) {
			this.textColor = new Color(204, 0, 0);
		} else {
			textColor = color;
		}
		setForeground(textColor);
	}

	/**
	 * sets the alignment for the font
	 * @param align the direction for the alignment
	 */
	private void setAlignmentdirection(int align) {
		setHorizontalAlignment(align);
	}
	
	/**
	 * this method updates button design on hover
	 */
	public void hoverEffectOn() {
		setForeground(new Color(255, 0, 25));
		setFont(new Font("Impact", Font.BOLD, 40));
	}
	
	/**
	 * this method updates button design on exiting hover
	 */
	public void hoverEffectOff() {
		setForeground(textColor);
		setFont(textFont);
	}
	
} //class ends
