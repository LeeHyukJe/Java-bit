package TextEditor;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TextToolEx3 extends Frame implements WindowListener {

	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;

	String btnName[] = { "Undo", "짝수줄 제거", "문자 삭제" };

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

		btn[n++].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				preText = curText;
				String pline = tfParam1.getText();
				Scanner sc = new Scanner(curText);
				String temp ="";
				while(sc.hasNext()) {
					temp=sc.nextLine();
					for(int i=0;i<pline.length();i++) {
						for(int j=0;j<temp.length();j++) {
							if(pline.charAt(i)==temp.charAt(j)) {
								sb.append(temp.charAt(j));
							}
						}
						sb.append(CR_LF);
					}
				}
				ta.setText(sb.toString());
			}
		});
	}

	public TextToolEx3(String title) {
		super(title);
		lb1 = new Label("param1:", Label.RIGHT);
		lb2 = new Label("param2", Label.RIGHT);
		tfParam1 = new TextField(15);
		tfParam2 = new TextField(15);

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextToolEx3 ex3 = new TextToolEx3("TextTool");
		ex3.show();
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
