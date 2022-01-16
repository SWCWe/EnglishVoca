package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.EngController;
import util.MyUtill;
import vo.Eng;

public class TablePanel extends JPanel {
	private JFrame parent;
	private BufferedImage image;
	private List<Eng> list = MainGUI.list;
	private JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private DefaultTableModel model;

	public TablePanel(JFrame parent) {
		this.parent = parent;
		
		try {
			image = ImageIO.read(new File("images/mainImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(null);
		
		JButton btn1 = new JButton("�߰�");
		JButton btn2 = new JButton("����");
		JButton btn3 = new JButton("�ڷ�");
		JButton btn4 = new JButton("���ΰ�ħ");

		btn1.setBounds(145, 0, 60, 45);
		btn2.setBounds(205, 0, 60, 45);
		btn3.setBounds(265, 0, 60, 45);
		btn4.setBounds(0, 0, 145, 45);

		btn1.addActionListener(addListener(4));
		btn2.addActionListener(addListener(5));
		btn3.addActionListener(addListener(0));
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reload();
				System.out.println("���ΰ�ħ");
				System.out.println("size : "+list.size());
				System.out.println("list : "+list);

				JPanel nextPanel = MainGUI.panels[1];
				MyUtill.changePanel(parent, TablePanel.this, nextPanel);
			}
		});

		reload();

		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);

	}

	public ActionListener addListener(int num) {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel nextPanel = MainGUI.panels[num];
				MyUtill.changePanel(parent, TablePanel.this, nextPanel);
			}
		};
		return listener;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	public void reload() {
		remove(scrollPane);
		list = new EngController().loadEngList();
		Object[] columnNames = { "�ܾ�", "��", "�޸�" };

		// �� ���� �Է�
		Object[][] rowData = new Object[list.size()][columnNames.length];
		for (int i = 0; i < list.size(); i++) {
			Eng vc = list.get(i);
			rowData[i][0] = vc.getWord();
			rowData[i][1] = vc.getMeaning();
			rowData[i][2] = vc.getSentence();
		}
		model = new DefaultTableModel(rowData, columnNames);
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				
				int rowLen = model.getRowCount();
				int colLen = model.getColumnCount();
				List<Eng> newList = new ArrayList<Eng>();

				for (int i = 0; i < rowLen; i++) {
					Eng newEng = new Eng();
					for (int j = 0; j < colLen; j++) {
						Object data = model.getValueAt(i, j);
						// 0�� = �ܾ�, 1�� = ��, 2�� = �޸� ��
						switch (j) {
						case 0:
							newEng.setWord((String) data);
							break;
						case 1:
							newEng.setMeaning((String) data);
							break;
						case 2:
							newEng.setSentence((String) data);
							break;

						}
					}
					newList.add(newEng);
				}
				new EngController().modifyList(newList);
				list = new EngController().loadEngList();
				System.out.println("Table ����");
				System.out.println("list size : "+list.size());
				System.out.println("list : "+list);
			}
		});
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		table.setPreferredScrollableViewportSize(new Dimension(800, 300));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 45, 330, 425);
		add(scrollPane);
	}

}