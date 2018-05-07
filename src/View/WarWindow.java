package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Model.Player;

/**
 * This class is used display a actual game 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-22-2018
 */
public class WarWindow {

	/**
	 * contains MainFrame instance
	 */
	private MainFrame window;
	
	/**
	 * contains JPanel that displays the actual game contents
	 */
	private PanelWithBackground warPanel;
	
	/**
	 * this Jpanel shows the user's board
	 */
	private Board leftBoard;
	
	/**
	 * this JPanel shows the computer's board
	 */
	private Board rightBoard;
	
	/**
	 * this JPanel displays the enemy fleets and instructions
	 */
	private JPanel topPanel;
	
	/**
	 * this Jpanel contains the buttons and the scorePanel
	 */
	private JPanel borderPanel;
	
	/**
	 * this contains a JButton that goes to the pause menu and later becomes quitGame button that directly takes to the main menu
	 */
	private MenuButton menuButton;
	
	/**
	 * this JPanel displays enemy's fleet
	 */
	private JPanel shipsPanel;
	
	/**
	 * this JPanel displays the instructions contents
	 */
	private JPanel instructionPanel;
	
	/**
	 * this CustomLabel displays a message
	 */
	private CustomLabel header;
	
	/**
	 * this JTextArea instance contains the instruction messages
	 */
	private JTextArea instruction;
	
	/**
	 * this JLabel displays the messages about destroyed ships
	 */
	private CustomLabel update;
		
	/**
	 * this JPanel holds the scoring contents
	 */
	private JPanel scorePanel;
	
	/**
	 * this variable displays the last shot coordinate by user
	 */
	private CustomLabel userLastShot;
	
	/**
	 * this variable displays the total amount of shot taken by the user
	 */
	private CustomLabel userTotalShot;
	
	/**
	 * this variable displays the last shot coordinate by computer
	 */
	private CustomLabel pcLastShot;
	
	/**
	 * this variable displays the total amount of shot taken by the computer
	 */
	private CustomLabel pcTotalShot;
	
	/**
	 * returs the menuButton
	 * @return menuButton
	 */
	public MenuButton getMenuButton() {
		return menuButton;
	}
	
	/**
	 * returs the rightBoard
	 * @return rightBoard
	 */
	public Board getRightBoard() {
		return rightBoard;
	}
	
	/**
	 * returs the topPanel
	 * @return topPanel
	 */
	public JPanel getTopPanel() {
		return topPanel;
	}

	/**
	 * returs the warPanel
	 * @return warPanel
	 */
	public PanelWithBackground getWarPanel() {
		return warPanel;
	}
	
	/**
	 * returs the update
	 * @return update
	 */
	public CustomLabel getUpdate() {
		return update;
	}
	
	/**
	 * this is the constructor that initialize the actual visual game contents and displays them
	 * @param f MainFrame instance
	 * @param user Player instance
	 * @param pc Player instance
	 */
	public WarWindow(MainFrame f, Player user, Player pc) {
		window = f;
		warPanel = new PanelWithBackground("src/images/inGameImage.jpg");
		warPanel.setBounds(0, 0, window.getWidth(), window.getHeight());
		
		topPanel = new JPanel(new GridLayout(1,2));
		topPanel.setOpaque(false);
		topPanel.setBounds(0, 0, window.getContentPane().getWidth(), 200);
		
		instructionPanel = new JPanel(new GridLayout(3,1));
		instructionPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		instructionPanel.setOpaque(false);
		
		header = new CustomLabel("Fight for your Life, soldier!", CustomLabel.LEFT);
		instruction = new JTextArea("Select a coordinate from the enemy's board on the right to attack");
		instruction.setForeground(Color.DARK_GRAY);
		instruction.setFont(new Font("Courier", Font.ITALIC, 20));
		instruction.setEditable(false);
		instruction.setLineWrap(true);
		instruction.setWrapStyleWord(true);
		instruction.setHighlighter(null);
		instruction.setOpaque(false);
		update = new CustomLabel("", CustomLabel.LEFT, Color.DARK_GRAY, new Font("Courier", Font.BOLD, 25));
		
		instructionPanel.add(header);
		instructionPanel.add(instruction);
		instructionPanel.add(update);
		
		shipsPanel = new JPanel(new FlowLayout(2,50,0));
		//adding the ships
		for(int i=0; i<Player.totalNumberOfShips;i++) {
			pc.getShipArray()[i].shipVerticalViewLayout();
			pc.getShipArray()[i].addVerticalShipImages(false, 60, 38);
			for(BoardCell cell : pc.getShipArray()[i].getCell()) {
				cell.setPreferredSize(new Dimension(38,38));
			}
			shipsPanel.add(pc.getShipArray()[i].getPanel());
		}
		shipsPanel.setOpaque(false);
		
		
		topPanel.add(instructionPanel);
		topPanel.add(shipsPanel);
		
		//setting up the boards
		leftBoard = user.getBoard();
		rightBoard = pc.getBoard();
		
		leftBoard.setBounds(0, topPanel.getHeight(), window.getContentPane().getHeight()-topPanel.getHeight(), window.getContentPane().getHeight()-topPanel.getHeight());
		rightBoard.setBounds(window.getContentPane().getWidth()-(window.getContentPane().getHeight()-topPanel.getHeight()), topPanel.getHeight(), window.getContentPane().getHeight()-topPanel.getHeight(), window.getContentPane().getHeight()-topPanel.getHeight());
		
		//resizing the shipsImages on user board
		for(int i=0; i<Player.totalNumberOfShips; i++) {			
			//setting up the ship images for the defenseBoard
			String plDirec;
			if(user.getShipArray()[i].getPlacingDirection() == Board.verticalInput) {plDirec = "V";} else {plDirec = "";}
			for(int j=0; j<user.getShipArray()[i].getLength(); j++) {
				user.getBoard().getBoardCell()[user.getShipArray()[i].getLocation()[j][0]][user.getShipArray()[i].getLocation()[j][1]].setImage("src/images/ships/" + user.getShipArray()[i].getName() + "/" + user.getShipArray()[i].getName() + (j+1) + plDirec +".png", 50, 50);
			}
		}
		
		
		//a border between two boards
		borderPanel = new JPanel(new FlowLayout(1,0,80));
		borderPanel.setBounds(window.getContentPane().getHeight()-topPanel.getHeight(), topPanel.getHeight(), window.getContentPane().getWidth()-(leftBoard.getWidth()+rightBoard.getWidth()), window.getContentPane().getHeight()-topPanel.getHeight());
		borderPanel.setOpaque(false);
		
		scorePanel = new JPanel(new GridLayout(2,1,0,120));
		scorePanel.setOpaque(false);
		
		JPanel forUser = new JPanel(new GridLayout(3,1));
		forUser.setOpaque(false);
		JPanel forPc = new JPanel(new GridLayout(3,1));
		forPc.setOpaque(false);
		
		userLastShot = new CustomLabel("Last Shot: ", CustomLabel.LEFT, Color.black, new Font("Courier", Font.BOLD, 14));		
		userTotalShot = new CustomLabel("Total Shot(s): ", CustomLabel.LEFT, Color.black, new Font("Courier", Font.BOLD, 14));		
		pcLastShot = new CustomLabel("Last Shot: ", CustomLabel.LEFT, Color.black, new Font("Courier", Font.BOLD, 14));		
		pcTotalShot = new CustomLabel("Total Shot(s): ", CustomLabel.LEFT, Color.black, new Font("Courier", Font.BOLD, 14));
		
		forUser.add(new CustomLabel("User Stats", CustomLabel.LEFT, Color.black, new Font("Courier", Font.BOLD, 22)));
		forUser.add(userLastShot);
		forUser.add(userTotalShot);
		forPc.add(new CustomLabel("Enemy Stats", CustomLabel.LEFT, Color.black, new Font("Courier", Font.BOLD, 22)));
		forPc.add(pcLastShot);
		forPc.add(pcTotalShot);
		
		scorePanel.add(forUser);
		scorePanel.add(forPc);
		
		menuButton = new MenuButton("Menu", MenuButton.CENTER);
		menuButton.setPreferredSize(new Dimension(135,60));
		
		borderPanel.add(scorePanel);
		borderPanel.add(menuButton);
		
		
		warPanel.add(topPanel);
		warPanel.add(leftBoard);
		warPanel.add(rightBoard);
		warPanel.add(borderPanel);
		
		window.add(warPanel);
	} //constructor ends
	
	
	/**
	 * updates the header labels that user has won
	 */
	public void setUserWinner() {
		header.setText("Congratulations! You have won");
		instruction.setText("You have defeated the enemy. Your nation salutes you.");
	}
	
	/**
	 * updates the header labels that computer has won
	 */
	public void setPcWinner() {
		header.setText("You have lost!");
		instruction.setText("You have been defeated by the enemy.");
	}
	
	/**
	 * displays the user's last shot coordinate
	 * @param coordinate expressed in a undertandable way
	 */
	public void updateUserLastShot(String coordinate) {
		userLastShot.setText("Last Shot: " + coordinate);
	}
	
	/**
	 * displays the computer's last shot coordinate
	 * @param coordinate expressed in a undertandable way
	 */
	public void updatePcLastShot(String coordinate) {
		pcLastShot.setText("Last Shot: " + coordinate);
	}
	
	/**
	 * displays the total shots taken by the user
	 * @param n number of total shots taken
	 */
	public void updateUserTotalShot(int n) {
		userTotalShot.setText("Total Shot(s): " + n);
	}
	
	/**
	 * displays the total shots taken by the computer
	 * @param n number of total shots taken
	 */
	public void updatePcTotalShot(int n) {
		pcTotalShot.setText("Total Shot(s): " + n);
	}
	
	/**
	 * updates the update label which ship has been sunk
	 * @param str String that will be displayed
	 */
	public void setUpdate(String str) {
		update.setText(str);
	}
	
	
} //class ends
