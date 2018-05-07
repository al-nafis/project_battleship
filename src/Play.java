import Controller.MainController;

/**
 * This class contains the main method that simply calls the singleton instance of the controller class 'Game'
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-03-2018
 */
public class Play {
	
	public static void main(String[] args) {
		MainController.getInstance();
	}
} //class ends