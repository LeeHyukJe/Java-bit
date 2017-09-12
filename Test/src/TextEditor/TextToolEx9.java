package TextEditor;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
public class TextToolEx9 extends Frame implements WindowListener{
	
	String fileName;
	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;
	MenuBar mb;
	Menu mFile;
	MenuItem miNew, miOpen, miSaveAs,miExit;

	String[] btnName = { "Undo", "¦���ٻ���", "���ڻ���", "trim", "���� ����", "���λ� �߰�", "substring", "substring2","distinct" ,"distinct2"};
	
	

	Button[] btn = new Button[btnName.length];
	private final String CR_LF = System.getProperty("line.separator");
	private String preText = "";

	private void registerEventHandler() {
		addWindowListener(this);

		int n = 0;

		btn[n++].addActionListener(new ActionListener() { // undo
			public void actionPerformed(ActionEvent ae) {
				String PreText = ta.getText();
				StringBuffer sb = new StringBuffer(preText.length());
				Scanner sc = new Scanner(preText);
				for (int i = 0; sc.hasNext(); i++) {
					preText = sc.nextLine();
					sb.append(preText).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() { // �����ϱ�
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc = new Scanner(curText);
				for (int i = 0; sc.hasNext(); i++) {
					String line = sc.nextLine();
					if (i % 2 == 0) {
						sb.append(line).append(CR_LF);
					}
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// ���� ����
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				preText = curText;
				String pline = tfParam1.getText();
				Scanner sc = new Scanner(curText);
				String temp = sc.nextLine();
				for (int i = 0; i < temp.length(); i++) {
					while (sc.hasNext()) {
						for (int j = 0; j < temp.length(); j++) {
							if (pline.charAt(i) == temp.charAt(j)) {
								sb.append(temp.charAt(j));
							}
						}
						temp = sc.nextLine();
					}
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// trim
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc = new Scanner(curText);
				while (sc.hasNextLine()) {
					String temp = sc.nextLine().trim();
					sb.append(temp).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// ���ٻ���
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc = new Scanner(curText);
				while (sc.hasNextLine()) {
					String temp = sc.nextLine();
					for (int i = 0; i < temp.length(); i++) {
						if (temp.charAt(i) != ' ') {
							sb.append(temp.charAt(i));
						}
					}
					sb.append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// ���λ� ���̻� ���̱�
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				String p1 = tfParam1.getText();
				String p2 = tfParam2.getText();
				Scanner sc = new Scanner(curText);
				while (sc.hasNextLine()) {
					String temp = sc.nextLine();
					temp = p1 + temp + p2;
					sb.append(temp).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// ���λ� ���̻� ����
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				String p1 = tfParam1.getText();
				String p2 = tfParam2.getText();
				Scanner sc = new Scanner(curText);
				while (sc.hasNextLine()) {
					String temp = sc.nextLine();
					temp = temp.substring(p1.length(), temp.length() - p2.length());// (aaa)
					sb.append(temp).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() {// substring2
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				String p1 = tfParam1.getText();
				String p2 = tfParam2.getText();
				Scanner sc = new Scanner(curText);
				int p1ind = 0, p2ind = 0;
				String temp = "";
				while (sc.hasNextLine()) {
					temp = sc.nextLine();
					for (int i = 0; i < temp.length(); i++) {
						if (temp.charAt(i) + "" == p1) {
							p1ind = i;
						}
						if (temp.charAt(i) + "" == p2) {
							p2ind = i;
						}
					}
				}
				while (sc.hasNextLine()) {
					String temp2 = sc.nextLine();
					sb.append(temp2).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() {// distinct
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc=new Scanner(curText);
				HashSet set=new HashSet();
				while(sc.hasNext()) {
					String temp=sc.nextLine();
					set.add(temp);
				}
				ArrayList list=new ArrayList(set);
				java.util.Collections.sort(list);
				for(int i=0;i<list.size();i++) {
					sb.append(list.get(i)).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() {// distinct2
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				preText = curText;
				Scanner sc=new Scanner(curText);
				TreeMap tm=new TreeMap();
				int dis=0;
				String temp="";
				while(sc.hasNext()) {
					temp=sc.nextLine();
					if(tm.containsKey(temp)) { //�ߺ��Ǿ� ������
//						tm.replace(temp, dis, dis++);
						dis=(Integer)tm.get(temp);//Ű�� ���� ������
						tm.put(temp, ++dis);
					}
					else {
						tm.put(temp, 1);
//						tm.replace(temp, dis, 1);
					}	
				}
				Set set=tm.entrySet();
				Iterator it=set.iterator();
				int index;
				while(it.hasNext()) {
					Map.Entry e=(Map.Entry)it.next();
					String gubun=e.toString();
					if(tfParam1.getText()!="") {
						gubun=gubun.replace("=", ",");
						System.out.println(gubun);
					}
					else {
						gubun=gubun.replace("=", ",");
					}
					sb.append(gubun).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});
	}

	public TextToolEx9(String title) {
		super(title);
		lb1 = new Label("param1:", Label.RIGHT);
		lb2 = new Label("param2", Label.RIGHT);
		tfParam1 = new TextField(15);
		tfParam2 = new TextField(15);
		
		//�޴��� �޴� ������ ����
		mb=new MenuBar(); 
		mFile=new Menu("File"); 
		miNew=new MenuItem("New");
		miOpen=new MenuItem("Open");
		miSaveAs=new MenuItem("SaveAs");
		miExit=new MenuItem("Exit");
		
		//�޴��� �޴� ������ ���̱�
		mFile.add(miNew); 
		mFile.add(miOpen);
		mFile.add(miSaveAs);
		mFile.addSeparator();
		mFile.add(miExit);
		
		mb.add(mFile); //�޴��ٿ� �޴��� ����
		setMenuBar(mb); //�޴��� ����
		
		//�޴��� �̺�Ʈ �ڵ鷯�� ����Ѵ�.
		MyHandler handler=new MyHandler();
		miNew.addActionListener(handler);
		miOpen.addActionListener(handler);
		miSaveAs.addActionListener(handler);
		miExit.addActionListener(handler);

		ta = new TextArea();
		pNorth = new Panel();
		pSouth = new Panel();

		for (int i = 0; i < btn.length; i++) {
			btn[i] = new Button(btnName[i]);
		}

		pNorth.setLayout(new FlowLayout());
		pNorth.add(lb1);
		pNorth.add(tfParam1);
		pNorth.add(lb2);
		pNorth.add(tfParam2);

		pSouth.setLayout(new GridLayout(2, 10));
		for (int i = 0; i < btn.length; i++) {
			pSouth.add(btn[i]);
		}

		add(pNorth, "North");
		add(ta, "Center");
		add(pSouth, "South");

		setBounds(100, 100, 600, 300);
		ta.requestFocus();
		registerEventHandler();
		setVisible(true);
	}
	
	//������ ������ �о TextArea�� �����ִ� �޼ҵ�
	void fileOpen(String fileName) {
		FileReader fr;
		BufferedReader br;
		StringWriter sw;
		
		try {
			fr=new FileReader(fileName);
			br=new BufferedReader(fr);
			sw=new StringWriter();
			
			int ch=0;
			while((ch=br.read())!=-1) {
				sw.write(ch);
			}
			br.close();
			ta.setText(sw.toString());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//TextArea�� ������ ������ ���Ͽ� �����ϴ� �޼ҵ�
	void saveAs(String fileName) {
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw=new FileWriter(fileName);
			bw=new BufferedWriter(fw);
			bw.write(ta.getText());
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextToolEx9 ex9 = new TextToolEx9("TextTool");
		ex9.show();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	
	class MyHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command=e.getActionCommand();
			if(command.equals("New")) {
				ta.setText("");
			}
			else if(command.equals("Open")) {
				FileDialog fileOpen=new FileDialog(TextToolEx9.this,"���Ͽ���");
				fileOpen.setVisible(true);
				fileName=fileOpen.getDirectory()+fileOpen.getFile();
				System.out.println(fileName);
				fileOpen(fileName);
			}
			else if(command.equals("SaveAs")) {
				FileDialog fileSave=new FileDialog(TextToolEx9.this,"��������",FileDialog.SAVE);
				fileSave.setVisible(true);
				fileName=fileSave.getDirectory()+fileSave.getFile();
				System.out.println(fileName);
				saveAs(fileName);
			}
			else if(command.equals("Exit")){
				System.exit(0);
			}
		}
		
	}
}


