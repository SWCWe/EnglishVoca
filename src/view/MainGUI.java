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
		panels[0] = new MainPanel(this); // 메인
		panels[1] = new TablePanel(this); // 목록
		panels[2] = new StudyGUI(this); // 공부
		panels[3] = new TestGUI(this); // 테스트
		panels[4] = new InsertGUI(this); // 추가
		panels[5] = new DeleteGUI(this); // 삭제
		setResizable(false);
		add(panels[0]);
	}
}