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
import java.util.List;
import java.util.ArrayList;
import vo.Eng;

public class EngIO {
	//입력
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
		//hashcode
		list.remove(eng);
		File f = new File("Voca.txt");

		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))) {

			for (Eng e : list) {
				oos.writeObject(e);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 있는 것
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
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (EOFException e) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

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