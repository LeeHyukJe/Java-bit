package TextEditor;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;

public class TextToolEx2 extends Frame implements WindowListener {
	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1,lb2;
	
	String[] btnName= {"Undo","짝수줄 삭제"	};
	
	Button[] btn=new Button[btnName.length];
	private final String CR_LF=System.getProperty("line.separator");
	private String preText=""; //TextArea ta의 내용을 저장하기 위한 변수
	private void registeEventHandler(){
		addWindowListener(this);
		
		btn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String PreText=ta.getText();
				StringBuffer sb=new StringBuffer(preText.length());
				Scanner sc=new Scanner(preText);
				for(int i=0;sc.hasNext();i++) {
					preText=sc.nextLine();
					sb.append(preText).append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});
		
		btn[1].addActionListener(new ActionListener() { //삭제하기
			public void actionPerformed(ActionEvent ae) {
				String curText=ta.getText();
				StringBuffer sb=new StringBuffer(curText.length());
				preText=curText;
				Scanner sc=new Scanner(curText);
				for(int i=0;sc.hasNext();i++) {
					String line=sc.nextLine();
					if(i%2==0) {
						sb.append(line).append(CR_LF);
					}
				}
				ta.setText(sb.toString());
			}
		});
	}
	public TextToolEx2(String title) {
		super(title);
		lb1=new Label("param1:",Label.RIGHT);
		lb2=new Label("param2",Label.RIGHT);
		tfParam1=new TextField(15);
		tfParam2=new TextField(15);
		
		ta=new TextArea();
		pNorth=new Panel();
		pSouth=new Panel();
		
		for(int i=0;i<btn.length;i++) {
			btn[i]=new Button(btnName[i]);
		}
		
		pNorth.setLayout(new FlowLayout());
		pNorth.add(lb1);
		pNorth.add(tfParam1);
		pNorth.add(lb2);
		pNorth.add(tfParam2);
		
		pSouth.setLayout(new GridLayout(2,10));
		for(int i=0;i<btn.length;i++) {
			pSouth.add(btn[i]);
		}
		
		add(pNorth,"North");
		add(ta,"Center");
		add(pSouth,"South");
		
		setBounds(100,100,600,300);
		ta.requestFocus();
		registeEventHandler();
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextToolEx2 ex2=new TextToolEx2("TextTool");
		ex2.show();
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

}
