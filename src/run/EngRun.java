package run;

import java.util.List;
import vo.Eng;
import io.EngIO;
import view.MainGUI;

public class EngRun {
	public static void main(String[] args) {
		//����
		new MainGUI(330, 500, "���� �ܾ���").setVisible(true);
		
		EngIO io = new EngIO();
		List<Eng> list = io.loadEngList();
		System.out.println(list.size());
		System.out.println(list);
	}
}