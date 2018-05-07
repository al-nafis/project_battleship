package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Model.Player;

/**
 * this class is used to display the ship station where user places their ships
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-12-2018
 *
 */
public class GameWindow {
	
	/**
	 * this JPanel displays an image notifying the game has started
	 */
	private PanelWithBackground gameHasBegunPanel;
	
	/**
	 * this JPanel contains the all the contents for placing the ships
	 */
	private PanelWithBackground planningPanel;
	
	/**
	 * this JPanel contains the contents like instructions, ships images, and buttons
	 */
	private JPanel shipStation;
	
	/**
	 * this JLabel contains the instructions content 
	 */
	private JPanel labelPanel;
	
	/**
	 * this JTextArea displays the instruction messages 
	 */
	private JTextArea instructions;
	
	/**
	 * this JPanel contains the ships panels
	 */
	private JPanel shipsPanel;
	
	/**
	 * this JPanel contains the JButtons
	 */
	private JPanel buttonsPanel;
	
	/**
	 * this JButton is used to go to the pause menu
	 */
	private MenuButton menuButton;
	
	/**
	 * this JButton is used to place the ships into the board
	 */
	private MenuButton placeButton;
	
	/**
	 * this JButton advances to the main game window
	 */
	private MenuButton fightButton;
	
	/**
	 * returns the menu button
	 * @return menuButton
	 */
	public MenuButton getMenuButton() {
		return menuButton;
	}

	/**
	 * returns the placeButton
	 * @return placeButton
	 */
	public MenuButton getPlaceButton() {
		return placeButton;
	}

	/**
	 * returns the fightButton
	 * @return fightButton
	 */
	public MenuButton getFightButton() {
		return fightButton;
	}

	/**
	 * this variable contains the singleton instance of MainFrame class
	 */
	private MainFrame mainFrame;
	
	/**
	 * returns the planningPanel
	 * @return planningPanel
	 */
	public PanelWithBackground getPlanningPanel() {
		return planningPanel;
	}
	
	/**
	 * this is the constructor
	 * @param window MainFrame instance
	 * @param user User instance
	 */
	public GameWindow(MainFrame window, Player user) {
		this.mainFrame = window;
		gameHasBegunPanel = new PanelWithBackground("src/images/war_has_begun.jpg");
		mainFrame.add(gameHasBegunPanel);
		
		// main panel for the planning section
		planningPanel = new PanelWithBackground("src/images/inGameImage.jpg");
		
		// panel on the left
		shipStation = new JPanel(new BorderLayout());
		shipStation.setBounds(0, 0, 500, 800);
		shipStation.setBackground(new Color(242,242,242));
		shipStation.setOpaque(false);
		
		// the labels inside the shipStation
		labelPanel = new JPanel(new GridLayout(2,1,0,10));
		labelPanel.setOpaque(false);
		labelPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
		
		labelPanel.add(new CustomLabel("Place Your Ships", CustomLabel.LEFT, window.getMenus().getMenuTitleItemColor(),  window.getMenus().getMenuTitleItemFont()));
		instructions = new JTextArea("Click on Place button until you find the right setup for the ships. Then click Fight\r\n ");
		instructions.setFont(instructions.getFont().deriveFont(20f));
		instructions.setEditable(false);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setHighlighter(null);
		instructions.setOpaque(false);
		labelPanel.add(instructions);
		
		shipStation.add(labelPanel, BorderLayout.NORTH);
		
		// displayable ships panel
		shipsPanel = new JPanel(new FlowLayout(5,18,5));
		shipsPanel.setOpaque(false);
		shipsPanel.setBorder(new EmptyBorder(0, 10, 20, 10));
		//adding the ships
		for(int i=0; i<Player.totalNumberOfShips;i++) {
			shipsPanel.add(user.getShipArray()[i].getPanel());
		}
		
		shipStation.add(shipsPanel, BorderLayout.CENTER);
		
		//adding buttons
		buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		buttonsPanel.setBorder(new EmptyBorder(20, 0, 60, 0));
		buttonsPanel.setLayout(new FlowLayout(1));
		menuButton = new MenuButton("Menu", MenuButton.CENTER);
		menuButton.setPreferredSize(new Dimension(135,60));
		placeButton = new MenuButton("Place", MenuButton.CENTER);
		placeButton.setPreferredSize(new Dimension(185,60));
		fightButton = new MenuButton("Fight", MenuButton.CENTER);
		fightButton.setPreferredSize(new Dimension(125,60));
		
		buttonsPanel.add(menuButton);
		buttonsPanel.add(placeButton);
		buttonsPanel.add(fightButton);
		
		shipStation.add(buttonsPanel, BorderLayout.SOUTH);
		
		// setting up the board display
		user.getBoard().setBounds(shipStation.getWidth()-3, -3, mainFrame.getContentPane().getWidth()-shipStation.getWidth()+5, mainFrame.getContentPane().getHeight()+5);
//		user.getDefenseBoard().setOpaque(false);
		
		planningPanel.add(shipStation);
		planningPanel.add(user.getBoard());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1500);
					FadeShow.doIt(window, gameHasBegunPanel, planningPanel);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}}
		).start();
		
		
		
		
	}
	

} //class ends
