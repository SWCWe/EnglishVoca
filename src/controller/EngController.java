package controller;

import java.util.List;
import io.EngIO;
import vo.Eng;

public class EngController {
	private EngIO io = new EngIO();
	
	//IO에서 값을 추가 후 파일로 저장
	public void insertList(Eng eng) {
		io.insertList(eng);
	}
	
	//IO에서 값을 제거 후 파일로 저장
	public void deleteList(Eng eng) {
		io.deleteList(eng);
	}
	
	//IO에서 파일을 읽어와 List<Eng>
	public List<Eng> loadEngList(){
		return io.loadEngList();
	}
	
	//IO에서 수정된내용을 List<Eng>
	public void modifyList(List<Eng> list) {
		io.modifyList(list);
	}
}