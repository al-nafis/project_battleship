package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * This class is used display a pause menu 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-16-2018
 *
 */
public class PauseMenu{

	/**
	 * this JPanel contains the contents for the pause menu
	 */
	private PanelWithBackground windowPanel;
	
	/**
	 * this is a MainFrmae instance variable
	 */
	private MainFrame window;
	
	/**
	 * this JPanel contains the buttons
	 */
	private JPanel panel;
	
	/**
	 * this JButton resumes the game
	 */
	private MenuButton resume;
	
	/**
	 * this JButton goes to the options menu
	 */
	private MenuButton options;
	
	/**
	 * this JButton quits the game and goes to the main menu
	 */
	private MenuButton quitMatch;
	
	/**
	 * this method returns the panel
	 * @return panel
	 */
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * this JPanel returns the windowPanel
	 * @return windowPanel
	 */
	public PanelWithBackground getWindowPanel() {
		return windowPanel;
	}
	
	/**
	 * this JPanel returns the resumeButton
	 * @return resume
	 */
	public MenuButton getResumeButton() {
		return resume;
	}

	/**
	 * this method returns the option button
	 * @return options
	 */
	public MenuButton getOptionsButton() {
		return options;
	}

	/**
	 * this method returns the quitMatch button
	 * @return quitMatch
	 */
	public MenuButton getQuitMatchButton() {
		return quitMatch;
	}

	/**
	 * this is the constructor
	 * @param mainFrame it is used to display the the contents on it
	 */
	public PauseMenu(MainFrame mainFrame) {
		windowPanel = new PanelWithBackground("src/images/pauseBackground.jpg");
		window = mainFrame;
		windowPanel.setBackground(new Color(0,0,0,150));
		windowPanel.setBounds(0, 0, window.getWidth(), window.getHeight());
		
		panel = new JPanel(new GridLayout(3,1));
		panel.setOpaque(false);
		panel.setBounds(50, 480, 240, 200);
		
		resume = new MenuButton("Resume", MenuButton.LEFT);
		options = new MenuButton("Options", MenuButton.LEFT);
		quitMatch = new MenuButton("Quit Match", MenuButton.LEFT);
		
		panel.add(resume);
		panel.add(options);
		panel.add(quitMatch);
		
		windowPanel.add(panel);
		
		window.add(windowPanel);
	}
	
}
