package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.EngController;
import vo.Eng;
import util.MyUtill;

//삽입
public class InsertGUI extends JPanel{
	private JFrame parent;
	private EngController controller = new EngController();
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private BufferedImage image;
	
	
	public InsertGUI(JFrame parent) {
		this.parent = parent;
		
		try {
			image = ImageIO.read(new File("images/addImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setLayout(null);
		
		JButton btn1 = new JButton("back");
		JButton btn2 = new JButton("추가");
		txt1 = new JTextField("");
		txt2 = new JTextField("");
		txt3 = new JTextField("");
		
		JLabel label1 = new JLabel("단어");
		JLabel label2 = new JLabel("뜻");
		JLabel label3 = new JLabel("메모");

		btn1.setBounds(260, 0, 65, 45);
		btn2.setBounds(265, 300, 60, 45);
		txt1.setBounds(73, 250, 180, 45);
		txt2.setBounds(73, 300, 180, 45);
		txt3.setBounds(73, 350, 180, 45);
		
		label1.setBounds(33, 250, 80, 45);
		label2.setBounds(33, 300, 80, 45);
		label3.setBounds(33, 350, 80, 45);

		btn1.addActionListener(addListener(1));
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = txt1.getText();
				String meaning = txt2.getText();
				String sentence = txt3.getText();
				controller.insertList(new Eng(word, meaning, sentence));
				txt1.setText("");
				txt2.setText("");
				txt3.setText("");
				List<Eng> list = new EngController().loadEngList();
				System.out.println("Insert패널 단어 추가됨");
				System.out.println("list size : "+list.size());
				System.out.println("list : "+list);
				JOptionPane.showMessageDialog(null, "단어 추가 완료");
			}
		});

		add(btn1);
		add(btn2);
		add(txt1);
		add(txt2);
		add(txt3);
		add(label1);
		add(label2);
		add(label3);
	}
	
	public ActionListener addListener(int num){
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel nextPanel = MainGUI.panels[num];
				MyUtill.changePanel(parent, InsertGUI.this, nextPanel);
			}
		};
		return listener;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}