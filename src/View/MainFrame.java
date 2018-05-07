package View;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * This class is the main window  which creates a visual representation of the application by extending the class JFrame
 * 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-03-2018
 *
 */
public class MainFrame extends JFrame{

	/**
	 * holds the only instance of the class 'MainWindow'
	 */
	private static MainFrame instance = new MainFrame();
	
	/**
	 * this method returns the only instance of the class 'MainWindow'
	 * @return instance
	 */
	public static MainFrame getInstance() {
		return instance;
	}
	
	/**
	 * this variable contains the only singleton instance of the class Menus
	 */
	private Menus menus;
	

	/**
	 * this method returns the instance of the class Menus
	 * @return menus
	 */
	public Menus getMenus() {
		return menus;
	}
	
	/**
	 * this is the constructor of the class
	 */
	private MainFrame() {		
		//creating the main frame
		setTitle("A Game of Battleship");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1300,800);
		setResizable(false);
		
		//initialing the menu
		menus = Menus.getInstance();
		menus.addMainMenuToTheWindow(this);
		menus.showMainMenu();
		menus.changeCursorIcon(this);
	
		setVisible(true); //should be at the bottom of the constructor
	}
	
		
	
} //class ends
