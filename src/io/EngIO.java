package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import vo.Eng;

public class EngIO {

	// 추가 (파일을 읽어오고 그뒤에 입력받은 값을 덧붙여서 출력)
	public void insertList(Eng eng) {
		List<Eng> list = loadEngList();
		list.add(eng);
		File f = new File("Voca.txt");

		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))) {

			for (Eng e : list) {
				oos.writeObject(e);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteList(Eng eng) {
		List<Eng> list = loadEngList();
		//equals, hashcode를 override해줬기 때문에 가능
		list.remove(eng);
		File f = new File("Voca.txt"); //engList.txt

		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))) {

			for (Eng e : list) {
				oos.writeObject(e);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 목록 읽기
	public List<Eng> loadEngList() {
		List<Eng> list = new ArrayList<>();
		File f = new File("Voca.txt");
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));) {
			while (true) {
				Eng eng = (Eng) ois.readObject();
				list.add(eng);
			}
		} catch (FileNotFoundException e) {
			try {
				// 최초 등록시 오류가 발생할 수 있음
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (EOFException e) {
			// 처리 코드 없음.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 변경된 내용 덮어쓰기
	public void modifyList(List<Eng> list) {
		File f = new File("Voca.txt");
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))) {

			for (Eng e : list) {
				oos.writeObject(e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}