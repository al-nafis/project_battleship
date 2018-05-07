package Controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.LineBorder;

import View.BoardCell;
import View.MenuButton;

/**
 * this class is created to interact with the components when the mouse cursor is interacting with them
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-04-2018
 */
public class MouseEffect implements MouseListener{

	/**
	 * this static final variable is used basically as parameters to pass it on to the method
	 */
	public static final int ForMenuButton = 0;
	/**
	 * this static final variable is used basically as parameters to pass it on to the method
	 */
	public static final int ForBoardCell = 1;

	//public static final int ForShipCell = 2;

	/**
	 * int variable that contains the integer defining the type of button it is interacting with
	 */
	private int buttonType;
	
	/**
	 * this static boolean variable holds the status whether the sound effect option is on or not
	 */
	private static boolean soundStatus;
	
	/**
	 * this  boolean variable holds the status whether the button is enabled or not
	 */
	private boolean buttonEnabled;
	
	/**
	 * holds the instance of MenuButton class
	 */
	private MenuButton menuButton;
	
	/**
	 * this variable holds a BoardCell instance
	 */
	private BoardCell boardCell;
		
	/**
	 * this variable holds the MainController instance
	 */
	private MainController controller;
	
	
	/**
	 * this is one of the constructors of this class
	 * @param button represents a BoardCell instance
	 * @param buttonType int that is used to describe the type of button it is
	 * @param controller MainController instance to get access to audio control
	 */
	public MouseEffect(BoardCell button, int buttonType, MainController controller) {
		this.boardCell = button;
		this.buttonType = buttonType;
		this.controller = controller;
		soundStatus = true;
		this.buttonEnabled = true;
	}
	
	//constructor 2
	/**
	 * this is one of the constructors of this class
	 * @param button represents a BoardCell instance
	 * @param buttonType int that is used to describe the type of button it is
	 * @param controller MainController instance to get access to audio control
	 */
	public MouseEffect(MenuButton button, int buttonType, MainController controller) {
		this.menuButton = button;
		this.buttonType = buttonType;
		this.controller = controller;
		soundStatus = true;
		this.buttonEnabled = true;
	}
	
	/**
	 * this static simply updates the sound effect status
	 * @param status true means sound is on
	 */
	public static void setSoundStatus(boolean status) {
		soundStatus = status;
	}
	
	/**
	 * this method changes the button enabling status
	 * @param status true means button is enabled
	 */
	public void setButtonEnabled(boolean status) {
		buttonEnabled = status;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// applies only if the button represents a menuItem
		if(buttonType == ForMenuButton) {
			if(buttonEnabled) {
				menuButton.hoverEffectOn();
			}
			if(soundStatus) {
				if(buttonEnabled) {
					controller.getAudioControl().cellHoverSound();
				}
			}
		}
		
		//applies only if the button represents a boardCell
		if(buttonType == ForBoardCell) {
			if(boardCell.isEnabled()) {
				boardCell.setBorder(new LineBorder(Color.white));
				controller.getAudioControl().cellHoverSound();
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// applies only if the button represents a menuItem
		if(buttonType == ForMenuButton) {
			menuButton.hoverEffectOff();
		}
		
		//applies only if the button represents a boardCell
		if(buttonType == ForBoardCell) {
			if(boardCell.isEnabled()) {
				boardCell.setBorder(new LineBorder(Color.gray));
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//applies only if the button represents a boardCell
		if(buttonType == ForBoardCell) {
			if(boardCell.isEnabled()) {
				boardCell.setBorder(new LineBorder(Color.gray));
			}
		}
		
		// applies only if the button represents a menuItem
		if(buttonType == ForMenuButton) {
			menuButton.hoverEffectOff();
		}
	}

} //class ends
