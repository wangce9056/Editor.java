package demo.FileManagementTest1;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

public class about_Window extends JFrame{

	private JButton btn_ok;
	private JLabel about_label;
	private JLabel about_label2;
	private JLabel about_label3;
	private JPanel panel ;
	private BoxLayout boxlayout;
	
	public about_Window() {
		panel = new JPanel();
		boxlayout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		
		btn_ok = new JButton("OK");
		btn_ok.setAlignmentX(CENTER_ALIGNMENT);
		about_label = new JLabel("�����ü��±������Ұ� ! ");
		about_label2 = new JLabel("�����������������Ұ�! ");
		about_label3 = new JLabel(" made by JimTo ");
		about_label.setAlignmentX(CENTER_ALIGNMENT);
		about_label2.setAlignmentX(CENTER_ALIGNMENT);
		about_label3.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(about_label);
		panel.add(about_label2);
		panel.add(about_label3);
		panel.add(btn_ok);
		
		
		this.add(panel);
		this.setSize(300,200);
		this.setTitle("����");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		btn_ok.addActionListener(e->{
			this.dispose();
		});
	}

}
