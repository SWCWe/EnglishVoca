package view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.EngController;
import util.MyUtill;
import vo.Eng;

public class MainGUI extends JFrame {
	
	public static JPanel[] panels = new JPanel[6];
	public static List<Eng> list = new EngController().loadEngList();
	
	public MainGUI(int width, int height, String title) {
		MyUtill.init(this, width, height, title);
		panels[0] = new MainPanel(this); //����
		panels[1] = new TablePanel(this); //���
		panels[2] = new StudyGUI(this); //study
		panels[3] = new TestGUI(this); //test
		panels[4] = new InsertGUI(this); //�߰�
		panels[5] = new DeleteGUI(this); //����
		setResizable(false);
		add(panels[0]);
	}
}