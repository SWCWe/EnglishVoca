package run;

import java.util.List;
import io.EngIO;
import view.MainGUI;
import vo.Eng;

public class EngRun {
	public static void main(String[] args) {
		//���� ȭ������ ����
		new MainGUI(330, 500, "���� �ܾ���").setVisible(true);
		
		EngIO io = new EngIO();
		List<Eng> list = io.loadEngList();
		System.out.println(list.size());
		System.out.println(list);
	}
}