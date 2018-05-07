package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.MainFrame;
import View.PanelWithBackground;
import View.PauseMenu;
/**
 * this class holds the JButton action listeners
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-20-2018
 */
public class PauseMenuActionListener {

	/**
	 * the constructor initializes the actionlisteners
	 * @param pauseMenu PauseMenu instance
	 * @param controller MainController instance
	 * @param window MainWindow instance
	 * @param currentPanel PanelWithBackground instance that is used to be added in the mainFrame
	 */
	public PauseMenuActionListener(PauseMenu pauseMenu, MainController controller, MainFrame window, PanelWithBackground currentPanel) {
		
		pauseMenu.getResumeButton().addMouseListener(new MouseEffect(pauseMenu.getResumeButton(), MouseEffect.ForMenuButton, controller));
		pauseMenu.getOptionsButton().addMouseListener(new MouseEffect(pauseMenu.getOptionsButton(), MouseEffect.ForMenuButton, controller));
		pauseMenu.getQuitMatchButton().addMouseListener(new MouseEffect(pauseMenu.getQuitMatchButton(), MouseEffect.ForMenuButton, controller));

		
		pauseMenu.getResumeButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				controller.getAudioControl().goBackwardSound();
				pauseMenu.getWindowPanel().setVisible(false);
				window.remove(pauseMenu.getWindowPanel());
				window.add(currentPanel);
				currentPanel.setVisible(true);
			}
		});
		
		pauseMenu.getOptionsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				controller.getAudioControl().goForwardSound();
				pauseMenu.getPanel().setVisible(false);
				pauseMenu.getWindowPanel().remove(pauseMenu.getPanel());
				
				if(controller.getAudioControl().getBackgroundMusic().isMusicPlaying()) {
					controller.getMusicOnButtonMouseListener().setButtonEnabled(false);
					controller.getMusicOffButtonMouseListener().setButtonEnabled(true);
					window.getMenus().getMusicOnButton().setEnabled(false);
					window.getMenus().getMusicOffButton().setEnabled(true);
				} else {
					controller.getMusicOnButtonMouseListener().setButtonEnabled(true);
					controller.getMusicOffButtonMouseListener().setButtonEnabled(false);
					window.getMenus().getMusicOnButton().setEnabled(true);
					window.getMenus().getMusicOffButton().setEnabled(false);
				}
				if(controller.getAudioControl().getSoundEffectStatus()) {
					controller.getSoundOnButtonMouseListener().setButtonEnabled(false);
					controller.getSoundOffButtonMouseListener().setButtonEnabled(true);
					window.getMenus().getSoundOnButton().setEnabled(false);
					window.getMenus().getSoundOffButton().setEnabled(true);
				} else {
					controller.getSoundOnButtonMouseListener().setButtonEnabled(true);
					controller.getSoundOffButtonMouseListener().setButtonEnabled(false);
					window.getMenus().getSoundOffButton().setEnabled(false);
					window.getMenus().getSoundOnButton().setEnabled(true);
				}
				
				pauseMenu.getWindowPanel().add(window.getMenus().getOptionBox());
				window.getMenus().showOptionsMenu();
				
				//overriding MainController.goBackFromOptionsButton
				window.getMenus().getGoBackFromOptionsButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						window.getMenus().removeOptionsMenu();
						pauseMenu.getWindowPanel().remove(window.getMenus().getOptionBox());
						pauseMenu.getWindowPanel().add(pauseMenu.getPanel());
						pauseMenu.getPanel().setVisible(true);
						controller.getAudioControl().goBackwardSound();
						
						window.getMenus().getGoBackFromOptionsButton().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ev) {
								window.getMenus().removeOptionsMenu();
								window.getMenus().showMenuBox();
								controller.getAudioControl().goBackwardSound();
							}
						});
						
					}
				});
			}
		});
		
		pauseMenu.getQuitMatchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				controller.getAudioControl().goForwardSound();
				pauseMenu.getWindowPanel().setVisible(false);
				window.remove(pauseMenu.getWindowPanel());
				window.add(window.getMenus().getMainMenu());
				window.getMenus().showMainMenu();
				controller.getAudioControl().updateTransitionBGMStatus(controller.getAudioControl().getBackgroundMusic().isMusicPlaying());
				controller.getAudioControl().getBackgroundMusic().stopMusic();
				controller.getAudioControl().getBackgroundMusic().closeClip();
				controller.getAudioControl().playMenuBackgroundMusic();
			}
		});
		
		
		
		
	}
	
} //class ends