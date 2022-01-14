package controller;

import java.util.List;
import io.EngIO;
import vo.Eng;

public class EngController {
	private EngIO io = new EngIO();
	
	//IO���� ���� �߰� �� ���Ϸ� ����
	public void insertList(Eng eng) {
		io.insertList(eng);
	}
	
	//IO���� ���� ���� �� ���Ϸ� ����
	public void deleteList(Eng eng) {
		io.deleteList(eng);
	}
	
	//IO���� ������ �о�� List<Eng>
	public List<Eng> loadEngList(){
		return io.loadEngList();
	}
	
	//IO���� �����ȳ����� List<Eng>
	public void modifyList(List<Eng> list) {
		io.modifyList(list);
	}
}