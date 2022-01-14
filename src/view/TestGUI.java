package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.EngController;
import util.MyUtill;
import vo.Eng;

public class TestGUI extends JPanel{
	private JFrame parent;
	private List<Eng> list = MainGUI.list;
	private int size = list.size();
	private BufferedImage image;
	private JLabel txt1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private Eng q,a1,a2,a3;

	public TestGUI(JFrame parent) {
		this.parent = parent;
		
		try {
			image = ImageIO.read(new File("images/testImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setLayout(null);
		
		JButton btn1 = new JButton("메인화면");
		txt1 = new JLabel();
		btn2 = new JButton();
		btn3 = new JButton();
		btn4 = new JButton();
		Font font1 = new Font("맑은고딕", Font.BOLD, 27);
		txt1.setFont(font1);
		txt1.setForeground(Color.white);
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		reload();
		
		btn1.setBounds(230, 0, 100, 45);
		txt1.setBounds(75, 220, 180, 45);
		btn2.setBounds(75, 280, 180, 45);
		btn3.setBounds(75, 330, 180, 45);
		btn4.setBounds(75, 380, 180, 45);

		btn1.addActionListener(addListener(0));
		btn2.addActionListener(addListener());
		btn3.addActionListener(addListener());
		btn4.addActionListener(addListener());

		add(btn1);
		add(txt1);
		add(btn2);
		add(btn3);
		add(btn4);

	}

	public ActionListener addListener(int num){
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel nextPanel = MainGUI.panels[num];
				MyUtill.changePanel(parent, TestGUI.this, nextPanel);
				reload();
			}
		};
		return listener;
	}
	
	public ActionListener addListener(){
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				if(q.getMeaning().equals(btn.getText())) {
					JOptionPane.showMessageDialog(null, "정답");
				}else
					JOptionPane.showMessageDialog(null, "오답\n 정답 : "+q.getWord()+" => "+q.getMeaning());
				reload();
			}
		};
		return listener;
	}
	
	public void reload() {
		list=new EngController().loadEngList();
		size = list.size();	
		if(list.size()!=0) {
			Set<Integer> set = new HashSet<>();
			
			while (true) {
				set.add((int) (Math.random() * list.size()));
				if (set.size() == 3)
					break;
			}

			int[] arr = new int[3];
			int i = 0;
			for (int a : set) {
				arr[i++] = a;
			}
			
			q=list.get(arr[(int)(Math.random()*3)]);
			txt1.setText(q.getWord());
			
			shuffle(arr, 10);
			a1=list.get(arr[0]);
			a2=list.get(arr[1]);
			a3=list.get(arr[2]);
			btn2.setText(a1.getMeaning());
			btn3.setText(a2.getMeaning());
			btn4.setText(a3.getMeaning());
			
		}else {
			JPanel nextPanel = MainGUI.panels[0];
			MyUtill.changePanel(parent, TestGUI.this, nextPanel);
		}
		
	}
	
	public void shuffle(int[] array, int count) {
		int temp, temp2, randomNum1, randomNum2;

		for (int i = 0; i < count; i++) {
			randomNum1 = (int) (Math.random() * array.length);
			temp = array[randomNum1];
			randomNum2 = (int) (Math.random() * array.length);
			temp2 = array[randomNum2];
			array[randomNum1] = temp2;
			array[randomNum2] = temp;
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}