package demo.FileManagementTest1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

//�򵥵��ı��༭������1

public class Editor extends JFrame {
    public JTextPane textPane = new JTextPane(); //�ı�����d���༭����
    public JFileChooser filechooser = new JFileChooser(); //�ļ�ѡ����

    public Editor()
    {
        super("���");

        Action[] actions=			//�˵���ĸ��ֹ���
                {
                        new NewAction(),
                        new OpenAction(),
                        new SaveAction(),
                        new CutAction(),
                        new CopyAction(),
                        new PasteAction(),
                        new AboutAction(),
                        new ExitAction(),
                        new HelpAction()
                };
        setJMenuBar(createJMenuBar(actions));		//����actions�����˵���
        Container container=getContentPane();
        container.add(textPane, BorderLayout.CENTER);

        setSize(1300,1500);
        setVisible(true);
        //	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JMenuBar createJMenuBar(Action[] actions)	//�����˵����ĺ���
    {
        JMenuBar menubar=new JMenuBar();
        JMenu menuFile=new JMenu("�ļ�(F)");
        JMenu menuEdit=new JMenu("�༭(E)");
        JMenu menuAbout=new JMenu("����(H)");
        menuFile.add(new JMenuItem(actions[0]));
        menuFile.add(new JMenuItem(actions[1]));
        menuFile.add(new JMenuItem(actions[2]));
        menuFile.add(new JMenuItem(actions[7]));
        menuEdit.add(new JMenuItem(actions[3]));
        menuEdit.add(new JMenuItem(actions[4]));
        menuEdit.add(new JMenuItem(actions[5]));
        menuAbout.add(new JMenuItem(actions[6]));
        menuAbout.add(new JMenuItem(actions[8]));
        menubar.add(menuFile);
        menubar.add(menuEdit);
        menubar.add(menuAbout);
        return menubar;
    }

    class NewAction extends AbstractAction		//�½�
    {
        public NewAction()
        {
            super("�½�(N)     Ctrl+N");
        }
        public void actionPerformed(ActionEvent e)
        {
            textPane.setDocument(new DefaultStyledDocument());
        }
    }

    class OpenAction extends AbstractAction		//��
    {
        public OpenAction()
        {
            super("��(O)     Ctrl+O");
        }
        public void actionPerformed(ActionEvent e)
        {
            int i=filechooser.showOpenDialog(Editor.this);			//��ʾ���ļ��Ի���
            if(i==JFileChooser.APPROVE_OPTION)			//����Ի����ѡ��
            {
                File f=filechooser.getSelectedFile();	//�õ�ѡ����ļ�
                try
                {
                    InputStream is=new FileInputStream(f);
                    textPane.read(is, "d");
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    class SaveAction extends AbstractAction		//����
    {
        public SaveAction()
        {
            super("����(S)     Ctrl+S");
        }
        public void actionPerformed(ActionEvent e)
        {
            int i=filechooser.showSaveDialog(Editor.this);
            if(i==JFileChooser.APPROVE_OPTION)
            {
                File f=filechooser.getSelectedFile();
                try
                {
                    FileOutputStream out=new FileOutputStream(f);
                    out.write(textPane.getText().getBytes());
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    class ExitAction extends AbstractAction		//�˳�
    {
        public ExitAction()
        {
            super("�˳�(X)");
        }
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }

    class CutAction extends AbstractAction		//����
    {
        public CutAction()
        {
            super("����(T)     Ctrl+X");
        }
        public void actionPerformed(ActionEvent e)
        {
            textPane.cut();
        }
    }

    class CopyAction extends AbstractAction		//����
    {
        public CopyAction()
        {
            super("����(C)     Ctrl+C");
        }
        public void actionPerformed(ActionEvent e)
        {
            textPane.copy();
        }
    }

    class PasteAction extends AbstractAction		//ճ��
    {
        public PasteAction()
        {
            super("ճ��(P)     Ctrl+V");
        }
        public void actionPerformed(ActionEvent e)
        {
            textPane.paste();
        }
    }

    class AboutAction extends AbstractAction
    {
        public AboutAction()
        {
            super("���ڼ��(A)");
        }
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(Editor.this,"ʵ���˼��±���һЩ��������","����",JOptionPane.PLAIN_MESSAGE);
        }
    }

    class HelpAction extends AbstractAction
    {
        public HelpAction()
        {
            super("��ϵ������");
        }
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(Editor.this,"19805062401@163.com","����������",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args)
    {
        new Editor();
    }
}