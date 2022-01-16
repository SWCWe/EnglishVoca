package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vo.Eng;
import controller.EngController;
import util.MyUtill;
import javax.imageio.ImageIO;

public class DeleteGUI extends JPanel { // 삭제
	private JFrame parent;
	private EngController controller = new EngController();
	private JTextField txt;
	private BufferedImage image;
	private List<Eng> list = MainGUI.list;

	public DeleteGUI(JFrame parent) {
		this.parent = parent;

		try {
			image = ImageIO.read(new File("images/deleteImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBackground(Color.cyan);
		setLayout(null);

		JButton btn1 = new JButton("뒤로");
		JButton btn2 = new JButton("삭제");
		JLabel label = new JLabel("단어");
		txt = new JTextField("");

		btn1.setBounds(265, 0, 60, 45);
		btn2.setBounds(260, 250, 60, 45);
		label.setBounds(15, 250, 50, 45);
		txt.setBounds(50, 250, 200, 45);

		btn1.addActionListener(addListener(1));
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String word = txt.getText();
				Eng temp = new Eng(word, "", "");
				list = new EngController().loadEngList();
				
				if (list.contains(temp)) {
					controller.deleteList(new Eng(word, "", ""));
					txt.setText("");
					List<Eng> list = new EngController().loadEngList();
					
					System.out.println("Delete");
					System.out.println("list size : " + list.size());
					System.out.println("list : " + list);
					JOptionPane.showMessageDialog(null, "단어가 삭제 되었습니다.");
				}else
					JOptionPane.showMessageDialog(null, "현재 목록에는 입력하신 단어가 없습니다.");
			}
		});

		add(btn1);
		add(btn2);
		add(label);
		add(txt);
	}

	public ActionListener addListener(int num) {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel nextPanel = MainGUI.panels[num];
				MyUtill.changePanel(parent, DeleteGUI.this, nextPanel);
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