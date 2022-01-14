package util;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyUtill {
	
	//Frame�� ����ֱ� ���� ��������
	public static void init(JFrame f, int width, int height, String title) {
		f.setTitle(title);
		f.setSize(width,height);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Frame���� pannel ��ȯ�� ���� method
	public static void changePanel(JFrame parent, JPanel current, JPanel next) {
		parent.remove(current);
		parent.add(next);
		parent.revalidate();
		parent.repaint(); 
		
	}
	
}