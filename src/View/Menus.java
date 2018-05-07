package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * this class displays the menu items
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-06-2018
 *
 */
public class Menus {
	
	/**
	 * holds the only instance of the class 'Menus'
	 */
	private static Menus instance = new Menus();
	
	/**
	 * this method returns the only instance of the class 'Menus'
	 * @return instance
	 */
	public static Menus getInstance() {
		return instance;
	}
	
	/**
	 * this variable holds a Color instance that defines the menu title color
	 */
	private Color menuTitleItemColor;
	
	/**
	 * this variable holds a Font instance that defines the menu title font
	 */
	private Font menuTitleItemFont;
	
	/**
	 * This variable contains the main menu of the application
	 */
	private PanelWithBackground mainMenu;
	
	/**
	 * this button proceeds towards a new game
	 */
	private MenuButton newGameButton;
	
	/**
	 * this button takes to the option menu
	 */
	private MenuButton optionsButton;
	
	/**
	 * this button shows the credits
	 */
	private MenuButton creditsButton;
	
	/**
	 * this variable holds the menu options in the main menu
	 */
	private JPanel menuBox;
	
	/**
	 * this variable holds the difficulty options in the main menu
	 */
	private JPanel difficultyBox;
	
	/**
	 * this variable holds a MenuButton class instance for easier difficulty button
	 */
	private MenuButton easyDifficulty;
	
	/**
	 * this variable holds a MenuButton class instance for normal difficulty button
	 */
	private MenuButton normalDifficulty;
	
	/**
	 * this variable holds a MenuButton class instance for the button to go back to the main menu from difficulty settings
	 */
	private MenuButton goBackFromNewGame;
	
	/**
	 * this JPanel variable contains the buttons for all options for audio settings
	 */
	private JPanel optionBox;

	/**
	 * this variable holds a MenuButton class instance for music on button
	 */
	private MenuButton musicOn;
	
	/**
	 * this variable holds a MenuButton class instance for music off button
	 */
	private MenuButton musicOff;
	
	/**
	 * this variable holds a MenuButton class instance for sound on button
	 */
	private MenuButton soundOn;
	
	/**
	 * this variable holds a MenuButton class instance for sound off button
	 */
	private MenuButton soundOff;
	
	/**
	 * this variable holds a MenuButton class instance for going back to the main menu from options
	 */
	private MenuButton goBackFromOptions;
	
	/**
	 * this JPanel instance holds the on and off button for music control
	 */
	private JPanel musicButtonsPanel;
	
	/**
	 * this JPanel instance holds the on and off button for sound control
	 */
	private JPanel soundButtonsPanel;		
	
	/**
	 * this JPanel variable contains the credits section
	 */
	private JPanel creditBox;
	
	/**
	 * this JPanel contains the core information of the credit section
	 */
	private JPanel creditPanel;
	
	/**
	 * this variable holds a MenuButton class instance for going back to the main menu from credits
	 */
	private MenuButton goBackFromCredits;
	
	
	
	
	/////////////////
	// All getters //
	/////////////////
		
	/**
	 * this is a getter method
	 * @return menuTitleItemColor
	 */
	public Color getMenuTitleItemColor() {
		return menuTitleItemColor;
	}
	
	/**
	 * this is a getter method
	 * @return menuTitleItemFont
	 */
	public Font getMenuTitleItemFont() {
		return menuTitleItemFont;
	}
	
	/**
	 * this is a getter method
	 * @return optionBox
	 */
	public JPanel getOptionBox() {
		return optionBox;
	};
	
	/**
	 * this is a getter method
	 * @return newGameButton
	 */
	public MenuButton getNewGameButton() {
		return newGameButton;
	}

	/**
	 * this is a getter method
	 * @return optionsButton
	 */
	public MenuButton getOptionsButton() {
		return optionsButton;
	}

	/**
	 * this is a getter method
	 * @return creditsButton
	 */
	public MenuButton getCreditsButton() {
		return creditsButton;
	}

	/**
	 * this is a getter method
	 * @return easyDifficulty
	 */
	public MenuButton getEasyDifficultyButton() {
		return easyDifficulty;
	}

	/**
	 * this is a getter method
	 * @return normalDifficulty
	 */
	public MenuButton getNormalDifficultyButton() {
		return normalDifficulty;
	}

	/**
	 * this is a getter method
	 * @return goBackFromNewGame
	 */
	public MenuButton getGoBackFromNewGameButton() {
		return goBackFromNewGame;
	}

	/**
	 * this is a getter method
	 * @return musicOn
	 */
	public MenuButton getMusicOnButton() {
		return musicOn;
	}

	/**
	 * this is a getter method
	 * @return musicOff
	 */
	public MenuButton getMusicOffButton() {
		return musicOff;
	}

	/**
	 * this is a getter method
	 * @return soundOn
	 */
	public MenuButton getSoundOnButton() {
		return soundOn;
	}

	/**
	 * this is a getter method
	 * @return soundOff
	 */
	public MenuButton getSoundOffButton() {
		return soundOff;
	}

	/**
	 * this is a getter method
	 * @return goBackFromOptions
	 */
	public MenuButton getGoBackFromOptionsButton() {
		return goBackFromOptions;
	}
	
	/**
	 * this is a getter method
	 * @return goBackFromCredits
	 */
	public MenuButton getGoBackFromCreditsButton() {
		return goBackFromCredits;
	}
	
	/**
	 * returns the mainMenu instance of PanelWithBackground class
	 * @return mainMenu
	 */
	public PanelWithBackground getMainMenu() {
		return mainMenu;
	}

	/**
	 * this constructor calls the methods to create the visual menu contents
	 */
	private Menus() {
		initializeAllInstances();
		makeMainMenu();
		makeDifficultyMenu();
		makeOptionsMenu();
		makeCreditsMenu();
	}
	
	/**
	 * this method takes the MainWindow instance and uses it to change the cursor icon
	 * @param window instance of the MainWindow
	 */
	public void changeCursorIcon(MainFrame window) {
		Image cursorImage = new ImageIcon("src/images/cursor.png").getImage();
	    Point hotspot = new Point(16, 16);
	    String cursorName = "Cursor";
	    window.setCursor(window.getToolkit().createCustomCursor(cursorImage, hotspot, cursorName));
	}
	
	/**
	 * this method initializes all the required class instances
	 */
	public void initializeAllInstances() {
		//Common Instances
		menuTitleItemColor = new Color(189,0,134);
		menuTitleItemFont = new Font("Impact", Font.BOLD, 40);
		
		//Main Menu
		mainMenu = new PanelWithBackground("src/images/titleImage.jpg");
		menuBox = new JPanel();
		newGameButton = new MenuButton("New Game", MenuButton.LEFT);
		optionsButton = new MenuButton("Options", MenuButton.LEFT);
		creditsButton = new MenuButton("Credits", MenuButton.LEFT);
		
		//Difficulty Menu
		difficultyBox = new JPanel();
		easyDifficulty = new MenuButton("Easy", MenuButton.LEFT);
		normalDifficulty = new MenuButton("Normal", MenuButton.LEFT);
		goBackFromNewGame = new MenuButton("Back",  MenuButton.LEFT);
		
		//Options Menu
		optionBox = new JPanel();
		musicOn = new MenuButton("On", MenuButton.LEFT);
		musicOff = new MenuButton("Off", MenuButton.LEFT);
		soundOn = new MenuButton("On", MenuButton.LEFT);
		soundOff = new MenuButton("Off", MenuButton.LEFT);
		goBackFromOptions = new MenuButton("Back", MenuButton.LEFT);
		musicButtonsPanel = new JPanel();
		soundButtonsPanel = new JPanel();
		
		//credits Menu
		creditBox = new JPanel();
		goBackFromCredits = new MenuButton("Back", MenuButton.LEFT);
		creditPanel = new JPanel();
	}
	
	
	
	///////////////////////////////////
	// methods for making menu items //
	///////////////////////////////////
	
	/**
	 * this method creates the main menu for the game
	 */
	private void makeMainMenu() {
		menuBox.setBounds(50, 480, 240, 200);
		menuBox.setOpaque(false);
		menuBox.setLayout(new GridLayout(3,1));
		menuBox.add(newGameButton);
		menuBox.add(optionsButton);
		menuBox.add(creditsButton);
		mainMenu.add(menuBox);
	}
	
	/**
	 * this method creates the difficulty menu
	 */
	private void makeDifficultyMenu() {
		difficultyBox.setBounds(50, 415, 350, 267);
		difficultyBox.setOpaque(false);
		difficultyBox.setLayout(new GridLayout(4,1));
		difficultyBox.add(new CustomLabel("Choose Difficulty", CustomLabel.LEFT, menuTitleItemColor, menuTitleItemFont));
		difficultyBox.add(easyDifficulty);
		difficultyBox.add(normalDifficulty);
		difficultyBox.add(goBackFromNewGame);
		mainMenu.add(difficultyBox);
		difficultyBox.setVisible(false);
	}
	
	/**
	 * this method creates the options menu
	 */
	private void makeOptionsMenu() {
		optionBox.setBounds(50, 345, 350, 335);
		optionBox.setOpaque(false);
		optionBox.setLayout(new GridLayout(5,1));
		musicButtonsPanel.setLayout(new FlowLayout(3,20,0));
		musicButtonsPanel.setOpaque(false);
		musicOn.setPreferredSize(new Dimension(90,60));
		musicOff.setPreferredSize(new Dimension(90,60));
		soundButtonsPanel.setLayout(new FlowLayout(3,20,0));
		soundButtonsPanel.setOpaque(false);
		soundOn.setPreferredSize(new Dimension(90,60));
		soundOff.setPreferredSize(new Dimension(90,60));
		musicButtonsPanel.add(musicOn);
		musicButtonsPanel.add(musicOff);
		soundButtonsPanel.add(soundOn);
		soundButtonsPanel.add(soundOff);
		optionBox.add(new CustomLabel("Music", CustomLabel.LEFT, menuTitleItemColor, menuTitleItemFont));
		optionBox.add(musicButtonsPanel);
		optionBox.add(new CustomLabel("Sound", CustomLabel.LEFT, menuTitleItemColor, menuTitleItemFont));
		optionBox.add(soundButtonsPanel);
		optionBox.add(goBackFromOptions);
	}
	
	/**
	 * this method creates the Credits section
	 */
	private void makeCreditsMenu() {
		//only for this method
		Font font = new Font("Arial", Font.BOLD, 30);
		
		creditBox.setBounds(50, 615, 350, 66);
		creditBox.setOpaque(false);
		creditBox.setLayout(new GridLayout(1,1));
		creditBox.add(goBackFromCredits);
		creditPanel.setBounds(250, 100, 800, 530);
		creditPanel.setLayout(new GridLayout(12,1));
		creditPanel.setBackground(new Color(255,255,255,200));
		creditPanel.add(new CustomLabel("Music", CustomLabel.CENTER, menuTitleItemColor, menuTitleItemFont));
		creditPanel.add(new CustomLabel("www.zapsplat.com", CustomLabel.CENTER, null, font));
		creditPanel.add(new CustomLabel("Grzegorz Majcherczyk", CustomLabel.CENTER, null, font));
		creditPanel.add(new CustomLabel("www.purple-planet.com", CustomLabel.CENTER, null, font));
		creditPanel.add(new CustomLabel("",CustomLabel.CENTER,null,null)); //shortcut for linebreak
		creditPanel.add(new CustomLabel("Images", CustomLabel.CENTER, menuTitleItemColor, menuTitleItemFont));
		creditPanel.add(new CustomLabel("www.mdkholy.me", CustomLabel.CENTER, null, font));
		creditPanel.add(new CustomLabel("www.pluspng.com", CustomLabel.CENTER, null, font));
		creditPanel.add(new CustomLabel("www.forum.worldofwarships.com", CustomLabel.CENTER, null, font));
		creditPanel.add(new CustomLabel("",CustomLabel.CENTER,null,null)); //shortcut for linebreak
		creditPanel.add(new CustomLabel("Mouse Cursor Icon", CustomLabel.CENTER, menuTitleItemColor, menuTitleItemFont));
		creditPanel.add(new CustomLabel("dmitri13", CustomLabel.CENTER, null, font));
		mainMenu.add(creditBox);
		mainMenu.add(creditPanel);
		creditBox.setVisible(false);
		creditPanel.setVisible(false);
	}
	
	
	
	///////////////////////////////////////
	// methods for displaying and hiding //
	///////////////////////////////////////
	
	/**
	 * this method takes a JFrame as a parameter and adds the Main Menu window to it
	 * @param frame The main frame
	 */
	public void addMainMenuToTheWindow(JFrame frame) {
		frame.add(mainMenu);
	}

	/**
	 * this method adds the mainMenu JPanel to the main frame
	 */
	public void showMainMenu() {
		mainMenu.setVisible(true);
	}
	
	/**
	 * this method hides the mainMenu JPanel from the main frame
	 */
	public void hideMainMenu() {
		mainMenu.setVisible(false);
	}

	/**
	 * removes the menuBox from the JPanel
	 */
	public void removeMenuBox() {
		menuBox.setVisible(false);
	}
	
	/**
	 * shows the menuBox on the JPanel
	 */
	public void showMenuBox() {
		menuBox.setVisible(true);
	}
	
	/**
	 * this method makes the difficulty menu box invisible
	 */
	public void removeDifficultyMenu() {
		difficultyBox.setVisible(false);
	}
	
	/**
	 * this method makes the difficulty menu box visible
	 */
	public void showDifficultyMenu() {
		difficultyBox.setVisible(true);
	}
	
	/**
	 * this method makes the options box invisible
	 */
	public void removeOptionsMenu() {
		optionBox.setVisible(false);
	}
	
	/**
	 * this method makes the options box visible
	 */
	public void showOptionsMenu() {
		optionBox.setVisible(true);
	}
	
	/**
	 * this method makes the credit box invisible
	 */
	public void removeCreditMenu() {
		creditBox.setVisible(false);
		creditPanel.setVisible(false);
	}
	
	/**
	 * this method makes the credit box visible
	 */
	public void showCreditMenu() {
		creditBox.setVisible(true);
		creditPanel.setVisible(true);
	}
	
} //class ends
