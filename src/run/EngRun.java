package run;

import java.util.List;
import io.EngIO;
import view.MainGUI;
import vo.Eng;

public class EngRun {
	public static void main(String[] args) {
		//메인 화면으로 연결
		new MainGUI(330, 500, "영어 단어장").setVisible(true);
		
		EngIO io = new EngIO();
		List<Eng> list = io.loadEngList();
		System.out.println(list.size());
		System.out.println(list);
	}
}