package demo.FileManagementTest1;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.*;

import java.awt.*;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class fileManagement extends JFrame implements ActionListener{

	private JMenuBar menuBar;
	//?????
	private JMenu menu_File,menu_Edit,menu_Help,menu_Format;
	//??????????
	private JMenuItem item_new,item_open,item_save,item_exit;
	//????file?????????
	private JMenuItem item_undo,item_cut,item_copy,item_stick,item_delete;
	//????edit?????????
	private JMenuItem item_about;
	//????help?????????
	private JMenuItem item_word_format;
	
	private static JTextArea edit_text_area;
	//private JTextArea edit_text_area;
	
	//??????
	private JScrollPane scroll_bar;
	//???????pane ???????edit_text_area????????????????????????JScrollPane?????pane???????????÷???
	
	private JFileChooser fileChooser = null;
	//JFileChooser???  ?????????????
	
	public static void main(String[] args) {
		fileManagement m1 = new fileManagement();
	}

	public static JTextArea getEdit_text_area() {
	//public JTextArea getEdit_text_area() {
		return edit_text_area;
	}

	
	public fileManagement(){
		initMenuBar();
		initEditArea();
		initListener();
		
		
		
		
		this.setJMenuBar(menuBar);
		this.setSize(800,600);
		this.add(scroll_bar);
		this.setTitle("jAVA自定义文本编辑器");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * *初始化编辑区域
	 * 	 * 用scrollpane装textarea
	 * 	 * 同时对pane设置方向
	 */
	public void initEditArea() {
		edit_text_area = new JTextArea();
		scroll_bar = new JScrollPane(edit_text_area);
		scroll_bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	/**
	 * 初始化菜单栏，对菜单栏添加子菜单menu
	 * 	 * 同时可以给菜单添加二级菜单、菜单项
	 */
	public void initMenuBar() {
		menuBar = new JMenuBar();
		menu_File = new JMenu("文件(F)");
		menu_File.setMnemonic('f');//f+alt打开
		item_new = new JMenuItem("新建");
		item_open = new JMenuItem("打开");
		item_save = new JMenuItem("保存");
		item_exit = new JMenuItem("退出");
		menu_File.add(item_new);
		menu_File.add(item_open);
		menu_File.add(item_save);
		menu_File.add(item_exit);
		//File ???

		menu_Edit = new JMenu("编辑(E)");
		menu_Edit.setMnemonic('e');
		item_undo = new JMenuItem("撤销");
		item_cut = new JMenuItem("剪切");
		item_copy = new JMenuItem("复制");
		item_stick = new JMenuItem("粘贴");
		item_delete = new JMenuItem("删除");
		menu_Edit.add(item_undo);
		menu_Edit.add(item_cut);
		menu_Edit.add(item_copy);
		menu_Edit.add(item_stick);
		menu_Edit.add(item_delete);
		//Edit ???

		menu_Help = new JMenu("帮助(H)");
		menu_Help.setMnemonic('h');
		item_about = new JMenuItem("关于");
		menu_Help.add(item_about);
		//Help 菜单
		
		menu_Format = new JMenu("保存(O)");
		menu_Format.setMnemonic('o');
		item_word_format = new JMenuItem("字体(F)");
		item_word_format.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK,false));//??item??????
		menu_Format.add(item_word_format);
		
		
		menuBar.add(menu_File);
		menuBar.add(menu_Edit);
		menuBar.add(menu_Format);
		menuBar.add(menu_Help);
	}
	
	/**
	 * ??????btn??item?????ü?????
	 */
	public void initListener() {
		item_new.addActionListener(this);
		item_open.addActionListener(this);
		item_save.addActionListener(this);
		item_exit.addActionListener(this);
		item_undo.addActionListener(this);
		item_cut.addActionListener(this);
		item_copy.addActionListener(this);
		item_stick.addActionListener(this);
		item_delete.addActionListener(this);
		item_word_format.addActionListener(this);
		item_about.addActionListener(this);
	}

	/**
	 * ??????menu??????item???????????
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == item_about) {
			new about_Window();
		}else if (e.getSource() == item_word_format) {
			about_Format newFormat = new about_Format();
		}else if (e.getSource() == item_new) {
			new fileManagement(); // ?????? new???????? ????bug???????????????????????????
		}else if (e.getSource() == item_exit) {
			this.dispose();
		}else if (e.getSource() == item_open) {
			openFile();
		}else if (e.getSource() == item_save) {
			saveFile();
		}
	}

	private void saveFile() {
		File file = null;
		int result ;
		fileChooser = new JFileChooser("C:\\");
		fileChooser.setApproveButtonToolTipText("????"); // ?????????????????
		fileChooser.setDialogTitle("???????"); // ????title
		result = fileChooser.showOpenDialog(rootPane); // ????Dialog???View ??????
		
		//--------------------------------------------------------------------------
		if(result == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile(); // ?????????????????file?????·??
		}
		
		//--------------------------------------------------------------------------
		FileOutputStream fileOutputStream = null; // ???io??
		if (file != null) {
			try {
				fileOutputStream = new FileOutputStream(file);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			String content = edit_text_area.getText();

			try {
				fileOutputStream.write(content.getBytes());
			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if (fileOutputStream!=null) {
						fileOutputStream.close();
					}
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		
		try{
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"UTF-8"); // ????????б??????
			BufferedWriter writer = new BufferedWriter(write);
			String content = edit_text_area.getText();
			writer.write(content);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}


	private void openFile() {
		File file = null;
		int result ;
		fileChooser = new JFileChooser("C:\\");
		fileChooser.setApproveButtonToolTipText("???"); // ?????????????????
		fileChooser.setDialogTitle("?????"); // ????title
		result = fileChooser.showOpenDialog(rootPane); // ????Dialog???View ??????
		
		//--------------------------------------------------------------------------
		if(result == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile(); // ?????????????????file?????·??
		}
		
		//--------------------------------------------------------------------------
		//--------------------???????????д???????????????????textarea??--------------------
		/*FileInputStream fileInputStream = null;
		if (file != null) {
			try { //??????????????? ???????????????????????
				fileInputStream = new FileInputStream(file); // ??file??????????????fileInputStream??
			}catch (FileNotFoundException e) {  // ?????? ?????????
				e.printStackTrace(); //
				TipDialog tmpDialog = new TipDialog(this,"???????",true,"????????????????????!");// ???????????????????Щ???????????????????????
				
			}
			
			//??????
			int readbyte ;
			try {
				while ((readbyte = fileInputStream.read())!=-1) { //??ζε??????
					edit_text_area.append(String.valueOf((char)readbyte)); //??editarea ??????????
				}
			}catch (IOException e) { // ??????
				e.printStackTrace();
			}finally {
				try {
					if (fileInputStream != null) { //??fileInputStream ????
						fileInputStream.close();
					}
				}catch (IOException e) { //?????
					e.printStackTrace();
				}
			}
		}*/
		//---------------bug-------------------------------
		//--------------------------------------------------------------------------
		
		if(file.isFile() && file.exists()) {
			BufferedReader reader = null;
			try {
				InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
				reader = new BufferedReader(inputStreamReader);
				
				String readLine = "";
				while ((readLine = reader.readLine()) != null) { // ??BufferedReader????????ж?
					//edit_text_area.append(readLine); ????д????????е??????????????е???????????????append?????????????????з?
					edit_text_area.append(readLine+'\n');  //??edit_text_area ????м?
				}
				
				reader.close(); // ???reader
				
			}catch (IOException e) {
				e.printStackTrace();
				TipDialog tmpDialog = new TipDialog(this,"???????",true,"????????????????????!");
			}
			
			
		}
	}
	
	/**
------
	 */
	class TipDialog extends JDialog{
		
		public TipDialog (JFrame jf,String title ,boolean flag ,String info) {
			super(jf,title,flag);
			JLabel Jlb = new JLabel(info);
			this.add(Jlb);
			this.setSize(200, 150);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		
	}
	

}
