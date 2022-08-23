package demo.FileManagementTest1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * ��ʵ������ڿ��Դ�һ������д��
 * ����һ�����һ�������д�����Ա���Ѷȵͺܶ�
 * ������Ϊ��һʱ�俼�ǵ�ʱ��û���뵽�����԰����еĶ������д��һ���ˣ�����ǲ����ϱ�������
 * �Ժ���Ҫע��
 * @author Administrator
 *
 */
public class about_Format extends JFrame implements ItemListener,ActionListener{

	private JComboBox choose_word_style;
	private JComboBox choose_word_big;
	private JComboBox choose_word_pattern;
	private JComboBox choose_word_color;
	
	private JButton btn_ok;
	private JButton btn_cancel;
	
	private String[] styles = {"����","����","����","΢���ź�","����"};
	private String[] colors = {"��ɫ","��ɫ","��ɫ","��ɫ","��ɫ","��ɫ"};
	private String[] word_big = {"2","4","8","16","24","32","64","72"};
	private String[] pattern = {"����","��б","����"};
	
	private JTextField showText ;
	
	private JPanel paneNorth;
	private JPanel paneCenter;
	private JPanel paneSouth;
	
	// ��һ��font��װѡ�еĵ����ԣ�ÿѡ��һ�Σ��Զ�Ӧ�������޸�
	//Ȼ�󼯳�һ��font������޸�
	//��selectedFont ����Ĭ������
	private Font selectedFont = new Font("����",Font.PLAIN, 32);
	private String selectedStyle = "����";
	private int selectedBig = 32;
	private int selectedPattern = Font.PLAIN;
	private Color selectedColor = Color.BLACK;
	

	public Font getSelectedFont() {
		return selectedFont;
	}

	public String getSelectedStyle() {
		return selectedStyle;
	}

	public int getSelectedBig() {
		return selectedBig;
	}

	public int getSelectedPattern() {
		return selectedPattern;
	}
	/**
	 * ������ڵĹ�������
	 */
  public about_Format() {
	  initBox();
	  initText();
	  initButton();
	  initLocation();
	  initListener();
	  addBtnListener();
	  
	  this.setSize(550,200);
	  this.setTitle("���ָ�ʽ");
	  this.setVisible(true);
	  this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * ����btn�ļ����¼��ص�
   * @param arg0
   */
  @Override
  public void actionPerformed(ActionEvent e) {
	  if (e.getSource() == btn_cancel) {
		  this.dispose();//���ٵ�ǰ����
	  }else if (e.getSource() == btn_ok) { // ���ø������ʵ�����õ�textarea������setFont
		  fileManagement.getEdit_text_area().setFont(selectedFont); // �����Edit_text_area����Ϊ��̬����static ����Ҳһ��  �������ܵ���
		  fileManagement.getEdit_text_area().setForeground(selectedColor); // ������ɫ
		  // �ڸ������ڱ��뽫Edit_text_area����Ϊstatic��������̬������
		  // ��̬�������ص��ǣ��Ѿ�����һ��ʵ������test1��new������ ���еľ�̬����Ҳ�����ڴ��д��ڣ�һֱ������ʵ��������
		  // ��ʱ�������ǿ��Զ�����з��� 
		  
		  /*test1 t1 = new test1();
		  t1.getEdit_text_area().setFont(selectedFont);*/
		  /**
		   * ������������ǲ��еģ���Ϊ��ͨ��ʵ����test1 ����������һ���µ�Font�Ĵ��ڣ���������Ҫ��Ч�������
		   * ������Ҫ������ԭ�������ڽ�������ı��ʽ
		   */
		  this.dispose();
	  }
  }
  
  /**
   * ��ComboBox��Ӽ�����
   */
  private void initListener() {
	  choose_word_style.addItemListener(this);
	  choose_word_big.addItemListener(this);
	  choose_word_pattern.addItemListener(this);
	  choose_word_color.addItemListener(this);
  }
  
  /**
   * ��ʼ��ok ��cancel ������ť
   */
  private void initButton() {
	  btn_ok = new JButton("OK");
	  btn_cancel = new JButton("CANCEL");
	}
  
  /**
   * ������btn��Ӽ�����
   */
  public void addBtnListener() {
	  btn_ok.addActionListener(this);
	  btn_cancel.addActionListener(this);
  }

  /**
   * ��ʼ������
   * ��ÿ���ؼ�����һ���ò�������this������
   */
  public void initLocation() {
	  paneNorth = new JPanel();
	  paneNorth.add(new JLabel("����:"));
	  paneNorth.add(choose_word_style);
	  paneNorth.add(new JLabel("�ֺ�:"));
	  paneNorth.add(choose_word_big);
	  paneNorth.add(new JLabel("����:"));
	  paneNorth.add(choose_word_pattern);
	  paneNorth.add(new JLabel("��ɫ:"));
	  paneNorth.add(choose_word_color);
	  this.add(paneNorth,BorderLayout.NORTH);
	  
	  paneCenter = new JPanel();
	  paneCenter.add(showText);
	  this.add(paneCenter, BorderLayout.CENTER);
	  
	  paneSouth = new JPanel();
	  paneSouth.add(btn_ok);
	  paneSouth.add(btn_cancel);
	  this.add(paneSouth, BorderLayout.SOUTH);
	  
	  
  }
  
  	/**
  	 * ��ʼ��չʾ��������
  	 */
  	public void initText() {
	  showText = new JTextField("����չʾ");
	  showText.setFont(selectedFont);
	  showText.setEditable(false);
	  showText.setSize(100,160);
	  //showText.setForeground(Color.red);
  }
  
  	/**
  	 * ��ʼ������comboBox 
  	 * ����Ӧ��ѡ�����
  	 */
  public void initBox() {
	  choose_word_style = new JComboBox(styles);
	  choose_word_big = new JComboBox(word_big);
	  choose_word_pattern = new JComboBox(pattern);
	  choose_word_color = new JComboBox(colors);
  }
  
  
  public void renewFont() {
	  selectedFont = new Font(selectedStyle,selectedPattern,selectedBig);
	  showText.setFont(selectedFont);
	  showText.setForeground(selectedColor);
  }
  
  /**
   * ʱ������ص�����
   * ��ÿ��item�����¼���Ӧ
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
	  if (e.getItem() == "����") {
		  selectedStyle = "����";
		  renewFont();
	  }else if (e.getItem() == "����") {
		  selectedStyle = "����";
		  renewFont();
	  }else if (e.getItem() == "����") {
		  selectedStyle = "����";
		  renewFont();
	  }else if (e.getItem() == "΢���ź�") {
		  selectedStyle = "΢���ź�";
		  renewFont();
	  }else if (e.getItem() == "����") {
		  selectedStyle = "����";
		  renewFont();
	  }else if (e.getItem() == "����") {
		  selectedPattern = Font.PLAIN;
		  renewFont();
	  }else if (e.getItem() == "��б") {
		  selectedPattern = Font.ITALIC;
		  renewFont();
	  }else if (e.getItem() == "����") {
		  selectedPattern = Font.BOLD;
		  renewFont();
	  }else if (e.getItem() == "2") {
		  selectedBig = 2;
		  renewFont();
	  }else if (e.getItem() == "4") {
		  selectedBig = 4;
		  renewFont();
	  }else if (e.getItem() == "8") {
		  selectedBig = 8;
		  renewFont();
	  }else if (e.getItem() == "16") {
		  selectedBig = 16;
		  renewFont();
	  }else if (e.getItem() == "24") {
		  selectedBig = 24;
		  renewFont();
	  }else if (e.getItem() == "32") {
		  selectedBig = 32;
		  renewFont();
	  }else if (e.getItem() == "64") {
		  selectedBig = 64;
		  renewFont();
	  }else if (e.getItem() == "72") {
		  selectedBig = 72;
		  renewFont();
	  }else if (e.getItem() == "��ɫ") {
		  selectedColor = Color.red;
		  renewFont();
	  }else if (e.getItem() == "��ɫ") {
		  selectedColor = Color.black;
		  renewFont();
	  }else if (e.getItem() == "��ɫ") {
		  selectedColor = Color.blue;
		  renewFont();
	  }else if (e.getItem() == "��ɫ") {
		  selectedColor = Color.yellow;
		  renewFont();
	  }else if (e.getItem() == "��ɫ") {
		  selectedColor = Color.green;
		  renewFont();
	  }else if (e.getItem() == "��ɫ") {
		  selectedColor = Color.WHITE;
		  renewFont();
	  }
  }
  

}
