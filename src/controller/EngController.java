package controller;

import java.util.List;
import io.EngIO;
import vo.Eng;

public class EngController {
	private EngIO io = new EngIO();
	
	public void insertList(Eng eng) {
		io.insertList(eng);
	}
	
	public void deleteList(Eng eng) {
		io.deleteList(eng);
	}
	
	//List<Eng>
	public void modifyList(List<Eng> list) {
		io.modifyList(list);
	}
		
	//List<Eng>
	public List<Eng> loadEngList(){
		return io.loadEngList();
	}
	
}//