package util;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyUtill {
	public static void init(JFrame f, int w, int h, String s) {
		f.setTitle(s);
		f.setSize(w, h);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void changePanel(JFrame p, JPanel current, JPanel n) {
		p.remove(current);
		p.add(n);
		p.revalidate();
		p.repaint(); 
	}
	
}