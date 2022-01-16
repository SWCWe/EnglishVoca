package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import controller.EngController;
import vo.Eng;
import util.MyUtill;
//Panel
public class MainPanel extends JPanel {
	
	private JFrame parent;
	private BufferedImage image;
	private List<Eng> list = MainGUI.list;

	public MainPanel(JFrame parent) {

		this.parent = parent;
		try {
			image = ImageIO.read(new File("images/mainImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBackground(Color.cyan);
		setLayout(null);
		JButton btn1 = new JButton("공부할 단어 목록");
		JButton btn2 = new JButton("공부 시작");
		JButton btn3 = new JButton("단어 시험");
		btn1.setBounds(73, 250, 180, 45);
		btn2.setBounds(73, 300, 180, 45);
		btn3.setBounds(73, 350, 180, 45);

		btn1.addActionListener(addListener(1));
		btn2.addActionListener(addListener(2));
		btn3.addActionListener(addListener(3));

		add(btn1);
		add(btn2);
		add(btn3);
	}
	
	public ActionListener addListener(int num) {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list = new EngController().loadEngList();
				if (num == 2) {
					if (list.size() < 1) {
						JOptionPane.showMessageDialog(null, "단어장에 단어 1개 이상 필요");
						return;
					}
				} else if (num == 3) {
					if (list.size() < 3) {
						JOptionPane.showMessageDialog(null, "단어장에 단어 3개 이상 필요");
						return;
					}
				}
				JPanel nextPanel = MainGUI.panels[num];
				MyUtill.changePanel(parent, MainPanel.this, nextPanel);
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