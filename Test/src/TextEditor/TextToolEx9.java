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

	String[] btnName = { "Undo", "짝수줄삭제", "문자삭제", "trim", "빈줄 삭제", "접두사 추가", "substring", "substring2","distinct" ,"distinct2"};
	
	

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

		btn[n++].addActionListener(new ActionListener() { // 삭제하기
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

		btn[n++].addActionListener(new ActionListener() {// 문자 삭제
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

		btn[n++].addActionListener(new ActionListener() {// 빈줄삭제
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

		btn[n++].addActionListener(new ActionListener() {// 접두사 접미사 붙이기
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

		btn[n++].addActionListener(new ActionListener() {// 접두사 접미사 삭제
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
					if(tm.containsKey(temp)) { //중복되어 있으면
//						tm.replace(temp, dis, dis++);
						dis=(Integer)tm.get(temp);//키의 값을 가져옴
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
		
		//메뉴와 메뉴 아이템 생성
		mb=new MenuBar(); 
		mFile=new Menu("File"); 
		miNew=new MenuItem("New");
		miOpen=new MenuItem("Open");
		miSaveAs=new MenuItem("SaveAs");
		miExit=new MenuItem("Exit");
		
		//메뉴에 메뉴 아이템 붙이기
		mFile.add(miNew); 
		mFile.add(miOpen);
		mFile.add(miSaveAs);
		mFile.addSeparator();
		mFile.add(miExit);
		
		mb.add(mFile); //메뉴바에 메뉴를 붙임
		setMenuBar(mb); //메뉴바 생성
		
		//메뉴에 이벤트 핸들러를 등록한다.
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
	
	//선택한 파일을 읽어서 TextArea에 보여주는 메소드
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
	
	//TextArea의 내용을 지정된 파일에 저장하는 메소드
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
				FileDialog fileOpen=new FileDialog(TextToolEx9.this,"파일열기");
				fileOpen.setVisible(true);
				fileName=fileOpen.getDirectory()+fileOpen.getFile();
				System.out.println(fileName);
				fileOpen(fileName);
			}
			else if(command.equals("SaveAs")) {
				FileDialog fileSave=new FileDialog(TextToolEx9.this,"파일저장",FileDialog.SAVE);
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


