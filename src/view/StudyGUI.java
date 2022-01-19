package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
// 1°³¾¿
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
	
	Thread p_display, t_display;
	JLabel w1, w2, w3;
	int hh, mm, ss, t=0;
	
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
		sizeLabel.setText(String.valueOf(size));
		
		
		JPanel p = new JPanel(new BorderLayout());
		JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel wp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JLabel c1 = new JLabel(" : ");	
		JLabel c2 = new JLabel(" : ");
		w1 = new JLabel("00");
		w2 = new JLabel("00");
		w3 = new JLabel("00");
		
		wp.add(w1);
		wp.add(c1);
		wp.add(w2);
		wp.add(c2);
		wp.add(w3);
		
		p.add(wp, BorderLayout.CENTER);
		p.add(bp, BorderLayout.SOUTH);
		add(p);
		
		
		
		reload();

		btn1.setBounds(230, 0, 100, 45);
		btn2.setBounds(135, 400, 60, 45);
		txt1.setBounds(73, 110, 180, 45); 
		txt2.setBounds(73, 260, 180, 45); 
		txt3.setBounds(20, 360, 300, 45);
		sizeL.setBounds(1, 5, 90, 20);
		sizeLabel.setBounds(100, 5, 20, 20);
		w1.setBounds(10, 30, 20, 20);
		w2.setBounds(50, 30, 20, 20);
		w3.setBounds(90, 30, 20, 20);
		
		c1.setBounds(30, 30, 20, 20);
		c2.setBounds(70, 30, 20, 20);
		
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.setHorizontalAlignment(SwingConstants.CENTER);
		txt3.setHorizontalAlignment(SwingConstants.CENTER);
		sizeL.setHorizontalAlignment(SwingConstants.CENTER);
		sizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		Font font1 = new Font("¸¼Àº°íµñ", Font.BOLD, 27);
		txt1.setFont(font1);
		Font font2 = new Font("¸¼Àº°íµñ", Font.PLAIN, 20);
		txt2.setFont(font2);
		Font font3 = new Font("¸¼Àº°íµñ", Font.PLAIN, 15);
		sizeL.setFont(font3);
		sizeLabel.setFont(font3);
		txt1.setForeground(Color.black);
		txt2.setForeground(Color.black);
		txt3.setForeground(Color.black);
		sizeLabel.setForeground(Color.black);
		sizeL.setForeground(Color.black);
		
		w1.setFont(new Font("courier",Font.BOLD,15));
		w2.setFont(new Font("courier",Font.BOLD,15));
		w3.setFont(new Font("courier",Font.BOLD,15));

		c1.setFont(new Font("courier",Font.BOLD,15));
		c2.setFont(new Font("courier",Font.BOLD,15));
		
		btn1.addActionListener(addListener(0));
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reload();
			}
		});
		
		p_display = new Thread(new Runnable() {
			public void run() {
				hh = Integer.parseInt(w1.getText());
				mm = Integer.parseInt(w2.getText());
				ss = Integer.parseInt(w3.getText());
				
				while (p_display == Thread.currentThread()) {
					hh = t % (1000*60) / 100 / 60 / 60;
					mm =  t % (1000*60) / 100 / 60 ; //ºÐ
					ss = t % (1000*60) / 100 % 60 ; //ÃÊ
					
					try {
						Thread.sleep(10);
						w1.setText(String.format("%02d", hh));
						w2.setText(String.format("%02d", mm));
						w3.setText(String.format("%02d", ss));
						t++;
						}catch (InterruptedException e1) {}
					}
				}
			});
		p_display.start();
		add(btn1);
		add(btn2);
		add(txt1);
		add(txt2);
		add(txt3);
		add(sizeL);
		add(sizeLabel);
		add(w1);
		add(w2);
		add(w3);
		add(c1);
		add(c2);
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
			sizeLabel.setText(String.valueOf(size));
			txt1.setText(list.get(ran).getWord());
			txt2.setText(list.get(ran).getMeaning());
			txt3.setText(list.get(ran).getSentence());
			sizeL.setText("ÃÑ ´Ü¾î °³¼ö");
			
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
