package util;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyUtill {
	
	//Frame을 띄워주기 위한 도구모음
	public static void init(JFrame f, int width, int height, String title) {
		f.setTitle(title);
		f.setSize(width,height);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Frame에서 pannel 전환을 위한 method
	public static void changePanel(JFrame parent, JPanel current, JPanel next) {
		parent.remove(current);
		parent.add(next);
		parent.revalidate();
		parent.repaint(); 
		
	}
	
}