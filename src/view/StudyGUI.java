package view;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import vo.Eng;
import controller.EngController;
import util.MyUtill;

public class StudyGUI extends JPanel {

	private JFrame parent;
	private JLabel txt1;
	private JLabel txt2;
	private JLabel txt3;
	private JLabel sizeL;
	private JLabel sizeLabel;
	private List<Eng> list = MainGUI.list;
	int size = list.size();	
	int ran = (int)(Math.random()*size);
	private BufferedImage image;
	
	
	public StudyGUI(JFrame parent) {
		this.parent = parent;
		
		try {
			image = ImageIO.read(new File("images/startImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(null);
		
		JButton btn1 = new JButton("¸ÞÀÎÈ­¸é");
		JButton btn2 = new JButton("Next");
		txt1 = new JLabel();
		txt2 = new JLabel();
		txt3 = new JLabel();
		sizeL = new JLabel();
		sizeLabel = new JLabel();

		reload();

		btn1.setBounds(230, 0, 100, 45);
		btn2.setBounds(135, 400, 60, 45);
		txt1.setBounds(73, 110, 180, 45); 
		txt2.setBounds(73, 260, 180, 45); 
		txt3.setBounds(20, 360, 300, 45);
		sizeL.setBounds(5, 20, 130, 20);
		sizeLabel.setBounds(120, 20, 20, 20);
		
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.setHorizontalAlignment(SwingConstants.CENTER);
		txt3.setHorizontalAlignment(SwingConstants.CENTER);
		sizeL.setHorizontalAlignment(SwingConstants.CENTER);
		sizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		Font font1 = new Font("¸¼Àº°íµñ", Font.BOLD, 27);
		txt1.setFont(font1);
		Font font2 = new Font("¸¼Àº°íµñ", Font.PLAIN, 20);
		txt2.setFont(font2);
		Font font3 = new Font("¸¼Àº°íµñ", Font.PLAIN, 13);
		sizeL.setFont(font3);
		sizeLabel.setFont(font3);
		txt1.setForeground(Color.black);
		txt2.setForeground(Color.black);
		txt3.setForeground(Color.black);
		sizeLabel.setForeground(Color.black);
		sizeL.setForeground(Color.black);

		btn1.addActionListener(addListener(0));
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reload();
			}
		});

		add(btn1);
		add(btn2);
		add(txt1);
		add(txt2);
		add(txt3);
		add(sizeL);
		add(sizeLabel);
	}
	
	public ActionListener addListener(int num){
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JPanel nextPanel = MainGUI.panels[num];
				MyUtill.changePanel(parent, StudyGUI.this, nextPanel);
				reload();
			}
		};
		return listener;
	}
	
	public void reload() {
		list=new EngController().loadEngList();
		size=list.size();
		ran = (int)(Math.random()*size);
		if(list.size()!=0) {
			txt1.setText(list.get(ran).getWord());
			txt2.setText(list.get(ran).getMeaning());
			txt3.setText(list.get(ran).getSentence());
			sizeL.setText("ÃÑ ´Ü¾î °³¼ö");
			sizeLabel.setText(String.valueOf(size));
		}else {
			JPanel nextPanel = MainGUI.panels[0];
			MyUtill.changePanel(parent, StudyGUI.this, nextPanel);
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	
	
}
