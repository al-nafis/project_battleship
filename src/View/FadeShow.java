package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * this class contains a static method that is used to add a fadeOut effect 
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-13-2018
 */
public class FadeShow {
	
	/**
	 * this variable is used to define the opacity
	 */
	public static int i;
	
	/**
	 * instance of Timer class used to initiate the fade effect
	 */
	public static Timer timer;
	
	/**
	 * this method does the fade effect
	 * @param frame the JFrame where the effects happen
	 * @param panel1 the fade effect makes transition from
	 * @param panel2 the fade effect makes transition to
	 */
	public static void doIt(JFrame frame, JComponent panel1, JComponent panel2) {
		JPanel tint = new JPanel();
		tint.setOpaque(true);
		tint.setBackground(new Color(0,0,0,255));
		tint.setBounds(panel1.getBounds().x+1, panel1.getBounds().y, panel1.getWidth(), panel1.getHeight());
		panel1.add(tint);
		i = 5;
		
		ActionListener act = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tint.setBackground(new Color(0,0,0,i));
				i += 25;
				if(i > 255) {
					tint.setVisible(false);
					panel1.remove(tint);
					panel1.setVisible(false);
					frame.remove(panel1);
					frame.add(panel2);
					timer.stop();
				}
			}};
		timer = new Timer(50, act);
		timer.start();
		
	} // method ends
	
} //class ends
