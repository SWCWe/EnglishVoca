package view;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JFrame;
import controller.EngController;
import vo.Eng;
import util.MyUtill;


public class MainGUI extends JFrame {
	
	public static JPanel[] panels = new JPanel[6];
	public static List<Eng> list = new EngController().loadEngList();
	
	public MainGUI(int w, int h, String s) {
		MyUtill.init(this, w, h, s);
		panels[0] = new MainPanel(this); // ����
		panels[1] = new TablePanel(this); // ���
		panels[2] = new StudyGUI(this); // ����
		panels[3] = new TestGUI(this); // �׽�Ʈ
		panels[4] = new InsertGUI(this); // �߰�
		panels[5] = new DeleteGUI(this); // ����
		setResizable(false);
		add(panels[0]);
	}
}