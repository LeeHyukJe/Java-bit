// MainView.java : Java Chatting Client 의 핵심부분
// read keyboard --> write to network (Thread 로 처리)
// read network --> write to textArea

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
	  
public class MainView extends JFrame {
	
	

	private JPanel contentPane;
	private JTextField textField; // 보낼 메세지 쓰는곳

	private String id;
	private String ip;
	private int port;
	
	JButton sendBtn; // 전송버튼
	//JTextArea textArea; // 수신된 메세지를 나타낼 변수
	JTextPane textArea; // 이모티콘이나 이미지 보여주기 위해서는 JtextPane을 사용한다.
	public void append_Message(String str) {		
		append_Icon(icon1);		
		int len = textArea.getDocument().getLength(); // same value as
        textArea.setCaretPosition(len); // place caret at the end (with no selection)
 		textArea.replaceSelection(str); // there is no selection, so inserts at caret
	}
	
	ImageIcon icon1 = new ImageIcon("src/icon1.jpg");
	public void append_Icon(ImageIcon icon) {
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len); // place caret at the end (with no selection)
		textArea.insertIcon(icon);
	}
	
	private Socket socket; // 연결소켓
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	public MainView(String id, String ip, int port)// 생성자
	{
		this.id = id;
		this.ip = ip;
		this.port = port;
		init();
		start();
		//textArea.append("매개 변수로 넘어온 값 : " + id + " " + ip + " " + port + "\n");
		append_Message("매개 변수로 넘어온 값 : " + id + " " + ip + " " + port + "\n");
		network();
	}

	public void network() {
		// 서버에 접속
		try {
			socket = new Socket(ip, port);
			if (socket != null) // socket이 null값이 아닐때 즉! 연결되었을때
			{
				Connection(); // 연결 메소드를 호출
			}
		} catch (UnknownHostException e) {

		} catch (IOException e) {
			//textArea.append("소켓 접속 에러!!\n");
			append_Message("소켓 접속 에러!!\n");
		}

	}

	public void Connection() { // 실직 적인 메소드 연결부분
		try { // 스트림 설정
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
		} catch (IOException e) {
			//textArea.append("스트림 설정 에러!!\n");
			append_Message("스트림 설정 에러!!\n");
		}
		send_Message(id); // 정상적으로 연결되면 나의 id를 전송		
		Thread th = new Thread(new Runnable() { // 스레드를 돌려서 서버로부터 메세지를 수신
			@SuppressWarnings("null")
			@Override
			public void run() {
				while (true) {
					try {
						byte[] b = new byte[128];
						dis.read(b);
						String msg = new String(b);
						msg = msg.trim();
						//textArea.append(msg + "\n");
						//textArea.setCaretPosition(textArea.getText().length());
						append_Message(msg + "\n");
						
					} catch (IOException e) {
						//textArea.append("메세지 수신 에러!!\n");
						append_Message("메세지 수신 에러!!\n");
						// 서버와 소켓 통신에 문제가 생겼을 경우 소켓을 닫는다
						try {
							os.close();
							is.close();
							dos.close();
							dis.close();
							socket.close();
							break; // 에러 발생하면 while문 종료
						} catch (IOException e1) {
						}
					}
				} // while문 끝
			}// run메소드 끝
		});
		th.start();
	}

	public void send_Message(String str) { // 서버로 메세지를 보내는 메소드
		try {
			byte[] bb;
			bb = str.getBytes();
			dos.write(bb); //.writeUTF(str);
		} catch (IOException e) {
			//textArea.append("메세지 송신 에러!!\n");
			append_Message("메세지 송신 에러!!\n");
		}
	}

	public void init() { // 화면구성 메소드
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 1000, 2880, 3920);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 272, 302);
		contentPane.add(scrollPane);
		//textArea = new JTextArea();
		textArea = new JTextPane();
		scrollPane.setViewportView(textArea);
		//textArea.setForeground(new Color(255,0,0));
		textArea.setDisabledTextColor(new Color(0,0,0));
		textField = new JTextField();
		textField.setBounds(0, 312, 155, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		sendBtn = new JButton("전   송");
		sendBtn.setBounds(161, 312, 111, 42);
		contentPane.add(sendBtn);
		textArea.setEnabled(false); // 사용자가 수정못하게 막는다
		setVisible(true);
	}

	public void start() { // 액션이벤트 지정 메소드
		Myaction action = new Myaction();
		sendBtn.addActionListener(action); // 내부클래스로 액션 리스너를 상속받은 클래스로
		textField.addActionListener(action);
	}

	class Myaction implements ActionListener // 내부클래스로 액션 이벤트 처리 클래스
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == sendBtn || e.getSource() == textField) 
			{
				String msg = null;
				msg = String.format("[%s] %s\n", id, textField.getText());
				send_Message(msg);
				textField.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
				textField.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다
				
			}

		}

	}

}